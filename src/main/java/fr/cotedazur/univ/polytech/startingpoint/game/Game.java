package fr.cotedazur.univ.polytech.startingpoint.game;

import fr.cotedazur.univ.polytech.startingpoint.*;
import fr.cotedazur.univ.polytech.startingpoint.Map;
import fr.cotedazur.univ.polytech.startingpoint.action.*;
import fr.cotedazur.univ.polytech.startingpoint.bot.*;
import fr.cotedazur.univ.polytech.startingpoint.debugInterface.MapInterface;
import fr.cotedazur.univ.polytech.startingpoint.logger.Loggeable;
import fr.cotedazur.univ.polytech.startingpoint.objective.*;

import java.util.*;


public class Game implements DeckSignal, Referee, Loggeable {

    static final int MAX_NB_ROUND = 100;
    static final int NB_OBJECTIVE_TO_FINISH = 9;
    static final int NB_ACTIONS_PER_ROUND = 2;
    Random random;
    GameEngine gameEngine;
    ArrayList<BotProfil> botProfils;
    MapInterface mapInterface;
    int nbActions;
    List<Action> previousActions;


    private int nombreObjectifNull = 0;


    public Game( boolean debug){
        this.random = new Random();
        botProfils = new ArrayList<>();
        Deck<Objective> objectiveDeck   = generateObjectiveDrawPile();
        Deck<Plot> plotDeck             = generatePlotDrawPile();
        gameEngine = new GameEngine( objectiveDeck, plotDeck, new Map());
        botProfils.add(new BotProfil(new BotMbappe(this, gameEngine.getMap(),"Mbappe")));
        botProfils.add(new BotProfil(new BotSprint(this, gameEngine.getMap(), "BotSprint")));
        this.nbActions = NB_ACTIONS_PER_ROUND;
        this.previousActions = new ArrayList<>();

        if(debug){
            mapInterface = new MapInterface();
            mapInterface.drawMap(gameEngine.getMap(), gameEngine.getGardenerPosition(), gameEngine.getPandaPosition());
        }
        else {
            mapInterface = null;
        }
    }
    public Game(){
        this(false);
    }

    public boolean start(){
        do {
            this.nombreObjectifNull++;
            for(BotProfil botProfil : botProfils){
                nbActions = NB_ACTIONS_PER_ROUND;
                WeatherType weather = gameEngine.drawWeather();
                LOGGER .finest("Tour de " + botProfil.getBot().getBotName() + " : ");
                this.applyChangesDueToWeather( weather);
                doActions(botProfil, nbActions, weather);
                if(mapInterface != null) {
                    mapInterface.drawMap(gameEngine.getMap(), gameEngine.getGardenerPosition(), gameEngine.getPandaPosition());
                }
            }
            LOGGER.finest( () ->"Nombre de tours :" + this.nombreObjectifNull);
        }while (!checkFinishingCondition());
        BotProfil winner = checkWinner();
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


    public void doActions(BotProfil botProfil, int nbActions, WeatherType weather){
        List<ActionType> banActionTypes = new ArrayList<>();
        for(int i = 0; i < nbActions; i++) {
            Action action = botProfil.getBot().play(banActionTypes, weather);
            LOGGER.finer(()-> "Action : " + action);
            if (action!=null && ( banActionTypes.contains(action.toType())) ==false){
                action.play(this, gameEngine);
                banActionTypes.add(action.toType());
                action.verifyObjectiveAfterAction(this);
                saveAction(action);
            }
        }
    }

    public void applyChangesDueToWeather(WeatherType weather){
        if(weather==WeatherType.SUN){
            ++this.nbActions;
        }
    }

    public boolean checkFinishingCondition(){
        for(BotProfil botProfil : botProfils){
            if(botProfil.getNbCompletedObjective() >= NB_OBJECTIVE_TO_FINISH && this.nombreObjectifNull >= MAX_NB_ROUND){
                return true;
            }
        }
        return false;
    }

    @Override
    public void emptyDeck() {
        gameEngine.regenerateDecks(generateObjectiveDrawPile(), generatePlotDrawPile());
    }

    private Deck<Objective> generateObjectiveDrawPile(){
        Deck<Objective> objectiveDeck = new Deck<>(this);
        int upperRandForPlotType = 3;

        for (int i=0 ; i<20 ; ++i){
            objectiveDeck.addCard(new ObjectivePlots(random.nextInt(4)+1, new Pattern()));
        }
        for (int i=0 ; i<20 ; ++i){
            int nbBambous = 4;
            if(nbBambous == 3){
                objectiveDeck.addCard(new ObjectiveGardener(random.nextInt(4)+1, nbBambous, PlotType.values()[random.nextInt(upperRandForPlotType)+1], false,random.nextInt(3)+2));
            }
            else {
                objectiveDeck.addCard(new ObjectiveGardener(random.nextInt(4)+1, nbBambous, PlotType.values()[random.nextInt(upperRandForPlotType)+1], false,1));
            }

        }

        for (int i=0 ; i<20 ; ++i) {
            ArrayList<Bambou> bambous = new ArrayList<>();
            for (int j = 0; j < (random.nextInt(2) + 2); ++j) {
                bambous.add(new Bambou(PlotType.values()[random.nextInt(upperRandForPlotType) + 1]));
            }
            objectiveDeck.addCard(new ObjectivePanda(random.nextInt(4) + 1, bambous));
        }
        objectiveDeck.shuffle();
        return objectiveDeck;
    }

    private Deck<Plot> generatePlotDrawPile(){
        Deck<Plot> plotDeck = new Deck<>(this);
        int upperRandForPlotType = 3;

        for(int i=0 ; i<60 ; ++i){
            plotDeck.addCard(new Plot(PlotType.values()[random.nextInt(upperRandForPlotType)+1]));
        }
        plotDeck.shuffle();
        return plotDeck;
    }

    public boolean pickObjective(fr.cotedazur.univ.polytech.startingpoint.bot.Playable bot){
        Objective objective=  gameEngine.pickObjective();

        for(BotProfil botProfil : botProfils){
            if(bot == botProfil.getBot()){
                botProfil.addObjective(objective);
                LOGGER.finer( ()-> bot.getBotName() +" a prix un objectif :" + objective);
                return true;
            }
        }
        return false;
    }

    public List<Plot> pickPlot(){
        return gameEngine.pickPlot();
    }

    public boolean computeObjectivesPlot(Plot lastPlacedPlot){
        boolean result = false;
        ArrayList<Objective> validatedObjective = new ArrayList<>();
        for(BotProfil botProfil : botProfils){
            for(Objective objective : botProfil.getObjectives()){
                if(objective.verifyPlotObj(gameEngine, lastPlacedPlot)){
                    String botName = botProfil.getBot().getBotName();
                    validatedObjective.add(objective);
                    botProfil.setObjectiveCompleted(objective);
                    logValidatedObjective(objective, botName, botProfil);
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
        for(BotProfil botProfil : botProfils){
            for(Objective objective : botProfil.getObjectives()){
                if(objective.verifyGardenerObj(gameEngine)){
                    String botName = botProfil.getBot().getBotName();
                    validatedObjective.add(objective);
                    botProfil.setObjectiveCompleted(objective);
                    logValidatedObjective(objective, botName, botProfil);
                }
            }
            botProfil.getObjectives().removeAll(validatedObjective);
            if(validatedObjective.isEmpty()==false) result= true;
        }
        return result;
    }

    public Position getGardenerPosition(){
        return gameEngine.getGardenerPosition();
    }
    public Position getPandaPosition() {
        return gameEngine.getPandaPosition();
    }

    public boolean computeObjectivesPanda(){
        boolean result = false;
        ArrayList<Objective> validatedObjective = new ArrayList<>();
        for(BotProfil botProfil : botProfils){
            for(Objective objective : botProfil.getObjectives()){
                if(objective.verifyPandaObj(gameEngine, botProfil)){
                    String botName = botProfil.getBot().getBotName();
                    validatedObjective.add(objective);
                    botProfil.setObjectiveCompleted(objective);
                    logValidatedObjective(objective, botName, botProfil);
                }
            }
            botProfil.getObjectives().removeAll(validatedObjective);
            if(!validatedObjective.isEmpty()) result=true;
        }
        return result;
    }

    public BotProfil checkWinner() {
        BotProfil winner = null;
        if (this.nombreObjectifNull < MAX_NB_ROUND) {
            winner = botProfils.get(0);
            for (BotProfil botProfil : botProfils) {
                if (botProfil.getPoints() > winner.getPoints()) {
                    winner = botProfil;
                }
            }
        }
        return winner;
    }

    public List<Objective> getMyObjectives(Playable bot){
        for(BotProfil botProfil : botProfils){
            if(bot == botProfil.getBot()){
                return botProfil.getObjectives();
            }
        }
        return Arrays.asList();
    }

    public void printWinner(BotProfil botProfil) {
        if (botProfil==null) {
            LOGGER.fine("Match nul ! Aucun bot n'a su completer un objectif pendant "+ MAX_NB_ROUND +" tours");
        } else {
            LOGGER.fine(botProfil.getBot().getBotName() + " gagne avec : " + botProfil.getPoints() + " points");
        }
    }

    public List<Bambou> getMyBambous(Playable bot) {
        for(BotProfil botProfil : botProfils){
            if(botProfil.getBot()==bot)return botProfil.getBambous();
        }
        return Arrays.asList();
    }

    public void addBamboutToBot(Playable bot, Bambou bambou) {
        for(BotProfil botProfil : botProfils){
            if(botProfil.getBot()==bot){
                botProfil.addBanbou( bambou );
            }
        }
    }

    @Override
    public int getNumberOfPlayers() {
        return botProfils.size();
    }

    private void logValidatedObjective(Objective objective, String botName, BotProfil botProfil){
        LOGGER.finer( ()-> "L'objectif suivant a été validé : " + objective );
        LOGGER.finer( ()-> botName + " gagne " + objective.getPoint() + " points");
        LOGGER.finer( ()-> "Le score de "+ botName +" = " + botProfil.getPoints() + " points");
    }
}
