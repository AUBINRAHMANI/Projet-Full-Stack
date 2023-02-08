package fr.cotedazur.univ.polytech.startingpoint.game;

import fr.cotedazur.univ.polytech.startingpoint.*;
import fr.cotedazur.univ.polytech.startingpoint.StatistiqueManager.*;
import fr.cotedazur.univ.polytech.startingpoint.Map;
import fr.cotedazur.univ.polytech.startingpoint.action.*;
import fr.cotedazur.univ.polytech.startingpoint.bot.*;
import fr.cotedazur.univ.polytech.startingpoint.debugInterface.MapInterface;
import fr.cotedazur.univ.polytech.startingpoint.logger.Loggeable;
import fr.cotedazur.univ.polytech.startingpoint.objective.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;


public class Game implements DeckSignal, Referee, Loggeable {

    final static int MAX_NB_ROUND = 100;
    final static int NB_OBJECTIVE_TO_FINISH = 9;
    final static int NB_ACTIONS_PER_ROUND = 2;
    GameEngine gameEngine_;
    List<BotProfil> botProfiles;
    MapInterface _mapInterface;
    int nbActions;
    List<Action> previousActions;

    public StatistiqueManager statistiqueManager;

    private int timeOutCounter;




    public Game(){
        this(null, null, false);
    }
    public Game(StatistiqueManager statistiqueManager, List<BotProfil> botProfiles , boolean debug){
        timeOutCounter=0;
        previousActions                 = new ArrayList<>();
        Deck<Objective> objectiveDeck   = generateObjectiveDrawPile();
        Deck<Plot> plotDeck             = generatePlotDrawPile();
        gameEngine_                     = new GameEngine( objectiveDeck, plotDeck, new Map());
        this.botProfiles = botProfiles;
        this.statistiqueManager= statistiqueManager;

        for(BotProfil botProfil : this.botProfiles){
            botProfil.getBot().setEnvirronement(this, gameEngine_.getMap());
        }

        if(debug){
            _mapInterface = new MapInterface();
            _mapInterface.drawMap(gameEngine_.getMap(), gameEngine_.getGardenerPosition(), gameEngine_.getPandaPosition());
        }
        else {
            _mapInterface = null;
        }
    }



    public boolean start(){
        do {
            this.timeOutCounter++;
            statistiqueManager.addTours();
            for(BotProfil botProfil : botProfiles){
                nbActions = NB_ACTIONS_PER_ROUND;
                WeatherType weather = gameEngine_.drawWeather();
                LOGGER .finest("Tour de " + botProfil.getBotName() + " : ");
                this.applyChangesDueToWeather(botProfil, weather);
                doActions(botProfil, nbActions);
                if(_mapInterface != null) {
                    _mapInterface.drawMap(gameEngine_.getMap(), gameEngine_.getGardenerPosition(), gameEngine_.getPandaPosition());
                }
            }
            LOGGER.finest( "Nombre de tours :" + this.timeOutCounter);
        }while (!checkFinishingCondition());
        BotProfil winner = checkWinner();

        statistiqueManager.addNumberOfGame();


        printWinner(winner);
        return true;
    }

    private void saveAction(Action action) {
        previousActions.add(action);
        Collections.rotate(previousActions, 1);
        if(previousActions.size()>8){
            previousActions.remove(previousActions.size()-1);
        }
    }

    public List<Action> getPreviousActions(){
        return previousActions;
    }


    public void doActions(BotProfil botProfil, int nbActions){
        List<ActionType> banActionTypes = new ArrayList<>();
        for(int i = 0; i < nbActions; i++) {
            Action action = botProfil.getBot().play(banActionTypes, gameEngine_.getWeatherType());
            LOGGER.finer("Action : " + action);
            if (action!=null && ( banActionTypes.contains(action.toType())) ==false){
                action.play(this, gameEngine_);
                banActionTypes.add(action.toType());
                action.verifyObjectiveAfterAction(this);
                saveAction(action);
            }
        }
    }

    public void applyChangesDueToWeather(BotProfil botProfil, WeatherType weather){
        switch (weather){
            case SUN :
                int nbActionSun = this.nbActions + 1;
            case RAIN :
            case THUNDER :
            case WIND :
            case CLOUD :
            case QUESTIONMARK:
        }
    }

    public boolean checkFinishingCondition(){
        for(BotProfil botProfil : botProfiles){
            if(botProfil.getNbCompletedObjective() >= NB_OBJECTIVE_TO_FINISH)return true;
            else if(this.timeOutCounter >= MAX_NB_ROUND) return true;
        }
        return false;
    }

    @Override
    public void emptyDeck() {
        gameEngine_.regenerateDecks(generateObjectiveDrawPile(), generatePlotDrawPile());
    }

    private Deck<Objective> generateObjectiveDrawPile(){
        Deck<Objective> objectiveDeck = new Deck<>(this);
        Random rand = new Random();
        int upperRandForPlotType = 3;

        for (int i=0 ; i<20 ; ++i){
            objectiveDeck.addCard(new ObjectivePlots(rand.nextInt(4)+1, new Pattern()));
        }
        for (int i=0 ; i<20 ; ++i){
            //int nbBambous = rand.nextInt(2)+3;
            int nbBambous = 4;
            if(nbBambous == 3){
                objectiveDeck.addCard(new ObjectiveGardener(rand.nextInt(4)+1, nbBambous, PlotType.values()[rand.nextInt(upperRandForPlotType)+1], false,rand.nextInt(3)+2));
            }
            else {
                objectiveDeck.addCard(new ObjectiveGardener(rand.nextInt(4)+1, nbBambous, PlotType.values()[rand.nextInt(upperRandForPlotType)+1], false,1));
            }

        }

        for (int i=0 ; i<20 ; ++i) {
            ArrayList<Bambou> bambous = new ArrayList<>();
            for (int j = 0; j < (rand.nextInt(2) + 2); ++j) {
                bambous.add(new Bambou(PlotType.values()[rand.nextInt(upperRandForPlotType) + 1]));
            }
            objectiveDeck.addCard(new ObjectivePanda(rand.nextInt(4) + 1, bambous));
        }
        objectiveDeck.shuffle();
        return objectiveDeck;
    }

    private Deck<Plot> generatePlotDrawPile(){
        Deck<Plot> plotDeck = new Deck<>(this);
        Random rand = new Random();
        int upperRandForPlotType = 3;

        for(int i=0 ; i<60 ; ++i){
            plotDeck.addCard(new Plot(PlotType.values()[rand.nextInt(upperRandForPlotType)+1]));
        }
        plotDeck.shuffle();
        return plotDeck;
    }

    public boolean pickObjective(fr.cotedazur.univ.polytech.startingpoint.bot.Playable bot){
        Objective objective=  gameEngine_.pickObjective();

        for(BotProfil botProfil : botProfiles){
            if(bot == botProfil.getBot()){
                botProfil.addObjective(objective);
                LOGGER.finer( botProfil.getBotName() +" a prix un objectif :" + objective);
                return true;
            }
        }
        return false;
    }

    public List<Plot> pickPlot(){
        return gameEngine_.pickPlot();
    }

    public boolean computeObjectivesPlot(Plot lastPlacedPlot){
        boolean result = false;
        ArrayList<Objective> validatedObjective = new ArrayList<>();
        for(BotProfil botProfil : botProfiles){
            for(Objective objective : botProfil.getObjectives()){
                if(objective.verifyPlotObj(gameEngine_, lastPlacedPlot)){
                    String botName = botProfil.getBotName();
                    validatedObjective.add(objective);
                    botProfil.setObjectiveCompleted(objective);
                    timeOutCounter=0;
                    LOGGER.finer( "L'objectif suivant a été validé : " + objective );
                    LOGGER.finer( botName + " gagne " + objective.getPoint() + " points");
                    LOGGER.finer( "Le score de "+ botName +" = " + botProfil.getPoints() + " points");
                }
            }
            botProfil.getObjectives().removeAll(validatedObjective);
            if(validatedObjective.isEmpty()==false)result = true;
        }
        return result;
    }

    public boolean computeObjectivesGardener(){
        boolean result =false;
        ArrayList<Objective> validatedObjective = new ArrayList<>();
        for(BotProfil botProfil : botProfiles){
            for(Objective objective : botProfil.getObjectives()){
                if(objective.verifyGardenerObj(gameEngine_)){
                    String botName = botProfil.getBotName();
                    validatedObjective.add(objective);
                    botProfil.setObjectiveCompleted(objective);
                    LOGGER.finer( "L'objectif suivant a été validé : " + objective );
                    LOGGER.finer( botName + " gagne " + objective.getPoint() + " points");
                    LOGGER.finer( "Le score de "+ botName +" = " + botProfil.getPoints() + " points");
                }
            }
            botProfil.getObjectives().removeAll(validatedObjective);
            if(validatedObjective.isEmpty()==false) result= true;
        }
        return result;
    }

    public Position getGardenerPosition(){
        return gameEngine_.getGardenerPosition();
    }
    public Position getPandaPosition() {
        return gameEngine_.getPandaPosition();
    }

    public boolean computeObjectivesPanda(){
        boolean result = false;
        ArrayList<Objective> validatedObjective = new ArrayList<>();
        for(BotProfil botProfil : botProfiles){
            for(Objective objective : botProfil.getObjectives()){
                if(objective.verifyPandaObj(gameEngine_, botProfil)){
                    String botName = botProfil.getBotName();
                    validatedObjective.add(objective);
                    botProfil.setObjectiveCompleted(objective);
                    LOGGER.finer( "L'objectif suivant a été validé : " + objective );
                    LOGGER.finer(botName + " gagne " + objective.getPoint() + " points");
                    LOGGER.finer("Le score de "+ botName +" = " + botProfil.getPoints() + " points");
                }
            }
            botProfil.getObjectives().removeAll(validatedObjective);
            if(!validatedObjective.isEmpty()) result=true;
        }
        return result;
    }

    public BotProfil checkWinner() {
        BotProfil winner = null;
        if (this.timeOutCounter < MAX_NB_ROUND) {
            winner = botProfiles.get(0);
            for (BotProfil botProfil : botProfiles) {
                if (botProfil.getPoints() > winner.getPoints()) {
                    winner = botProfil;
                }
            }
            for(BotProfil botProfil : botProfiles) {
                if (winner != botProfil) {
                    statistiqueManager.addLoser(botProfil.getBot());
                }
            }
            statistiqueManager.addWinner(winner.getBot());
        }

        return winner;
    }

    public List<Objective> getMyObjectives(Playable bot){
        for(BotProfil botProfil : botProfiles){
            if(bot == botProfil.getBot()){
                return botProfil.getObjectives();
            }
        }
        return null;
    }

    public void printWinner(BotProfil botProfil) {
        if(botProfil==null){
            LOGGER.fine("Match nul ! Aucun bot n'a su completer un objectif pendant "+ MAX_NB_ROUND +" tours");
            statistiqueManager.addMatchNul();

        } else {
            LOGGER.fine(botProfil.getBotName() + " gagne avec : " + botProfil.getPoints() + " points");
        }
    }

    public List<Bambou> getMyBambous(Playable bot) {
        for(BotProfil botProfil : botProfiles){
            if(botProfil.getBot()==bot)return botProfil.getBambous();
        }
        return null;
    }

    public void addBamboutToBot(Playable bot, Bambou bambou) {
        for(BotProfil botProfil : botProfiles){
            if(botProfil.getBot()==bot){
                botProfil.addBanbou( bambou );
            }
        }
    }

    @Override
    public int getNumberOfPlayers() {
        return botProfiles.size();
    }
}
