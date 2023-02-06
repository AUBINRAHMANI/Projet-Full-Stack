package fr.cotedazur.univ.polytech.startingpoint.game;

import fr.cotedazur.univ.polytech.startingpoint.*;
import fr.cotedazur.univ.polytech.startingpoint.action.*;
import fr.cotedazur.univ.polytech.startingpoint.bot.Bot;
import fr.cotedazur.univ.polytech.startingpoint.debugInterface.MapInterface;
import fr.cotedazur.univ.polytech.startingpoint.objective.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class Game implements DeckSignal, Referee {

    static int MAX_NB_ROUND = 40;
    final int NB_OBJECTIVE_TO_FINISH = 8;
    GameEngine gameEngine_;
    ArrayList<BotProfil> botProfils_;
    MapInterface _mapInterface;
    private int nbActions = 2;

    private int nombreObjectifNull = 0;


    public Game(boolean debug){
        botProfils_                     = new ArrayList<>();
        Deck<Objective> objectiveDeck   = generateObjectiveDrawPile();
        Deck<Plot> plotDeck             = generatePlotDrawPile();
        gameEngine_                     = new GameEngine( objectiveDeck, plotDeck, new Map());
        botProfils_.add(new BotProfil(new Bot(this, gameEngine_.getMap(),"Ronaldo")));
        botProfils_.add(new BotProfil(new Bot(this, gameEngine_.getMap(), "Messi")));
        if(debug){
            _mapInterface = new MapInterface();
            _mapInterface.drawMap(gameEngine_.getMap(), gameEngine_.getGardenerPosition(), gameEngine_.getPandaPosition());
        }
        else {
            _mapInterface = null;
        }
    }
    public Game(){
        this(false);
    }

    public boolean start(){
        do {
            this.nombreObjectifNull++;
            for(BotProfil botProfil : botProfils_){
                System.out.println();
                gameEngine_.drawWeather();
                System.out.println("Tour de " + botProfil.getBot().getBotName() + " : ");
                this.applyChangesDueToWeather(botProfil);
                if(_mapInterface != null) {
                    _mapInterface.drawMap(gameEngine_.getMap(), gameEngine_.getGardenerPosition(), gameEngine_.getPandaPosition());
                }
            }
            System.out.println("Nombre de tours :" + this.nombreObjectifNull);
        }while (!checkFinishingCondition());
        BotProfil winner = checkWinner();
        printWinner(winner);
        return true;
    }

    private void doOneAction(Action action){
        System.out.println("Il joue l'action " + action);
        action.play(this, gameEngine_);
        action.verifyObjectiveAfterAction(this);
    }

    public boolean doActions(BotProfil botProfil, int nbActions){
        ArrayList<Action> banActions = new ArrayList<>();
        boolean canPlay;

        Action action = botProfil.getBot().play(0);
        doOneAction(action);
        banActions.add(action);
        for(int i = 1; i < nbActions; i++) {
            for (int j = 0; j < botProfil.getObjectives().size(); j++) {
                action = botProfil.getBot().play(j);
                canPlay = true;
                for (Action banAction : banActions) {
                    if (action.equals(banAction)) {
                        canPlay = false;
                    }
                }
                if (canPlay) {
                    doOneAction(action);
                    banActions.add(action);
                }
            }
            if (botProfil.getObjectives().size() <= 5) {
                action = new PickObjectiveAction(botProfil.getBot());
                doOneAction(action);
                banActions.add(action);
            } else {
                System.out.println("Le bot ne peut pas jouer, il passe son tour");
            }
        }

        return false;
    }
        public boolean rainAction(Position position) {
        if(gameEngine_.getMap().findPlot(position).isIrrigated()){
            return gameEngine_.getMap().findPlot(position).growBambou();
        }
        return false;
    }
    public void applyChangesDueToWeather(BotProfil botProfil){
        switch (gameEngine_.getWeatherType()){
            case SUN :
                int nbActionSun = this.nbActions + 1;
                doActions(botProfil, nbActionSun);
                break;
            case RAIN :
                doActions(botProfil, nbActions);
                break;
            case THUNDER :
                doActions(botProfil, nbActions);
                break;
            case WIND :
                doActions(botProfil, nbActions);
                break;
            case CLOUD :
                doActions(botProfil, nbActions);
                break;
            case QUESTIONMARK:
                doActions(botProfil, nbActions);
                break;
        }
    }

    public boolean checkFinishingCondition(){
        for(BotProfil botProfil : botProfils_){
            if(botProfil.getNbCompletedObjective() == NB_OBJECTIVE_TO_FINISH)return true;
            else if(this.nombreObjectifNull>MAX_NB_ROUND) return true;
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

    public boolean pickObjective(Bot bot){
        Objective objective=  gameEngine_.pickObjective();

        for(BotProfil botProfil : botProfils_){
            if(bot == botProfil.getBot()){
                botProfil.addObjective(objective);
                System.out.println(bot.getBotName() +" a pris un objectif :" + objective);
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
        for(BotProfil botProfil : botProfils_ ){
            for(Objective objective : botProfil.getObjectives()){
                if(objective.verifyPlotObj(gameEngine_, lastPlacedPlot)){
                    String botName = botProfil.getBot().getBotName();
                    validatedObjective.add(objective);
                    botProfil.setObjectiveCompleted(objective);
                    System.out.println( "L'objectif suivant a été validé : " + objective );
                    System.out.println(botName + " gagne " + objective.getPoint() + " points");
                    System.out.println("Le score de "+ botName +" = " + botProfil.getPoints() + " points");
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
        for(BotProfil botProfil : botProfils_ ){
            for(Objective objective : botProfil.getObjectives()){
                if(objective.verifyGardenerObj(gameEngine_)){
                    String botName = botProfil.getBot().getBotName();
                    validatedObjective.add(objective);
                    botProfil.setObjectiveCompleted(objective);
                    System.out.println( "L'objectif suivant a été validé : " + objective );
                    System.out.println(botName + " gagne " + objective.getPoint() + " points");
                    System.out.println("Le score de "+ botName +" = " + botProfil.getPoints() + " points");
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
        for(BotProfil botProfil : botProfils_ ){
            for(Objective objective : botProfil.getObjectives()){
                if(objective.verifyPandaObj(gameEngine_, botProfil)){
                    String botName = botProfil.getBot().getBotName();
                    validatedObjective.add(objective);
                    botProfil.setObjectiveCompleted(objective);
                    System.out.println( "L'objectif suivant a été validé : " + objective );
                    System.out.println(botName + " gagne " + objective.getPoint() + " points");
                    System.out.println("Le score de "+ botName +" = " + botProfil.getPoints() + " points");
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
            winner = botProfils_.get(0);
            for (BotProfil botProfil : botProfils_) {
                if (botProfil.getPoints() > winner.getPoints()) {
                    winner = botProfil;
                }
            }
        }
        return winner;
    }

    public List<Objective> getMyObjectives(Bot bot){
        for(BotProfil botProfil : botProfils_){
            if(bot == botProfil.getBot()){
                return botProfil.getObjectives();
            }
        }
        return null;
    }

    public void printWinner(BotProfil botProfil) {
        if (botProfil==null) {
            System.out.println("Match nul ! Aucun bot n'a su completer un objectif pendant 100 tours");
        } else {
            System.out.println(botProfil.getBot().getBotName() + " gagne avec : " + botProfil.getPoints() + " points");
        }
    }

    public List<Bambou> getMyBambous(Bot bot) {
        for(BotProfil botProfil : botProfils_){
            if(botProfil.getBot()==bot)return botProfil.getBambous();
        }
        return null;
    }

    public void addBamboutToBot(Bot bot, Bambou bambou) {
        for(BotProfil botProfil : botProfils_){
            if(botProfil.getBot()==bot){
                botProfil.addBanbou( bambou );
            }
        }
    }
}
