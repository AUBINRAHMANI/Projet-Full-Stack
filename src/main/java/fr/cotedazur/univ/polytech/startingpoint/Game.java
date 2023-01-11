package fr.cotedazur.univ.polytech.startingpoint;

import fr.cotedazur.univ.polytech.startingpoint.Action.*;
import fr.cotedazur.univ.polytech.startingpoint.debugInterface.MapInterface;
import fr.cotedazur.univ.polytech.startingpoint.objective.*;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Currency;
import java.util.Random;


public class Game {

    final int NB_OBJECTIVE_TO_FINISH = 2;
    GameEngine gameEngine_;
    ArrayList<BotProfil> botProfils_;
    MapInterface _mapInterface;


    public Game(boolean debug){
        botProfils_                     = new ArrayList<>();
        Deck<Objective> objectiveDeck   = generateObjectiveDrawPile();
        Deck<Plot> plotDeck             = generatePlotDrawPile();
        gameEngine_                 = new GameEngine( objectiveDeck, plotDeck, new Map());
        botProfils_.add(new BotProfil(new Bot(this, gameEngine_.getMap())));

        _mapInterface = new MapInterface();
        _mapInterface.drawMap(gameEngine_.getMap(), gameEngine_.getGardenerPosition(), gameEngine_.getPandaPosition());
    }
    public Game(){
        this(false);
    }

    public void start(){
        do {
            for(BotProfil botProfil : botProfils_){
                if(_mapInterface != null) while (_mapInterface.next()==false);
                _mapInterface.drawMap(gameEngine_.getMap(), gameEngine_.getGardenerPosition(), gameEngine_.getPandaPosition());
                Action action = botProfil.getBot_().play(this, gameEngine_.getMap());
                action.play(this, gameEngine_);
                action.verifyObjectiveAfterAction(this);
            }
        }while (!checkFinishingCondition());
        BotProfil winner = checkWinner();
        printWinner(winner);
    }

    public boolean checkFinishingCondition(){
        for(BotProfil botProfil : botProfils_){
            if(botProfil.getNbCompletedObjective_() == NB_OBJECTIVE_TO_FINISH)return true;
        }
        return false;
    }

    private Deck<Objective> generateObjectiveDrawPile(){
        Deck<Objective> objectiveDeck = new Deck<>();
        Random rand = new Random();
        int upperRandForPlotType = 3;

        /*for (int i=0 ; i<20 ; ++i){
            objectiveDeck.addCard(new ObjectivePlots(rand.nextInt(4)+1, new Pattern()));
        }*/
        for (int i=0 ; i<20 ; ++i){
            int nbBambous = rand.nextInt(2)+3;
            if(nbBambous == 3){
                objectiveDeck.addCard(new ObjectiveGardener(rand.nextInt(4)+1, nbBambous, PlotType.values()[rand.nextInt(upperRandForPlotType)+1], false,rand.nextInt(3)+2));
            }
            else {
                objectiveDeck.addCard(new ObjectiveGardener(rand.nextInt(4)+1, nbBambous, PlotType.values()[rand.nextInt(upperRandForPlotType)+1], false,1));
            }

        }
        /*for (int i=0 ; i<20 ; ++i){
            ArrayList<Bambou> bambous = new ArrayList<>();
            for(int j=0 ; j<(rand.nextInt(2)+2) ; ++j){
                bambous.add(new Bambou(PlotType.values()[rand.nextInt(upperRandForPlotType)+1]));
            }
            objectiveDeck.addCard(new ObjectivePanda(rand.nextInt(4)+1, bambous));
        }*/
        objectiveDeck.shuffle();
        return objectiveDeck;
    }

    private Deck<Plot> generatePlotDrawPile(){
        Deck<Plot> plotDeck = new Deck<>();
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
            if(bot == botProfil.getBot_()){
                botProfil.addObjective(objective);
                System.out.println("Le bot a prix un objectif :" + objective);
                return true;
            }
        }
        return false;
    }

    public Plot pickPlot(){
        return gameEngine_.pickPlot();
    }

    public boolean computeObjectivesPlot(Plot lastPlacedPlot){
        ArrayList<Objective> validatedObjective = new ArrayList<>();
        for(BotProfil botProfil : botProfils_ ){
            for(Objective objective : botProfil.getObjectives_()){
                if(objective.verifyPlotObj(gameEngine_, lastPlacedPlot)){
                    System.out.println( "L'objectif suivant a été validé : " + objective );
                    System.out.println("Le bot gagne " + objective.getPoint() + " points");
                    botProfil.addPoints_(objective.getPoint());
                    validatedObjective.add(objective);
                }
            }
            botProfil.getObjectives_().removeAll(validatedObjective);
        }
        return true;
    }

    public boolean computeObjectivesGardener(){
        ArrayList<Objective> validatedObjective = new ArrayList<>();
        for(BotProfil botProfil : botProfils_ ){
            for(Objective objective : botProfil.getObjectives_()){
                if(!objective.verifyGardenerObj(gameEngine_)){
                    botProfil.addPoints_(objective.getPoint());
                    validatedObjective.add(objective);
                }
            }
            botProfil.getObjectives_().removeAll(validatedObjective);
        }
        return true;
    }

    public Position getGardenerPosition(){
        return gameEngine_.getGardenerPosition();
    }

    public boolean computeObjectivesPanda(){
        ArrayList<Objective> validatedObjective = new ArrayList<>();
        for(BotProfil botProfil : botProfils_ ){
            for(Objective objective : botProfil.getObjectives_()){
                if(!objective.verifyPandaObj(gameEngine_, botProfil)){
                    botProfil.addPoints_(objective.getPoint());
                    validatedObjective.add(objective);
                }
            }
            botProfil.getObjectives_().removeAll(validatedObjective);
        }
        return true;
    }

    public BotProfil checkWinner(){
        BotProfil winner = botProfils_.get(0);
        for(BotProfil botProfil : botProfils_){
            if(botProfil.getPoints_() > winner.getPoints_()){
                winner  = botProfil;
            }
        }
        return winner;
    }

    ArrayList<Objective> getMyObjectives(Bot bot){
        for(BotProfil botProfil : botProfils_){
            if(bot == botProfil.getBot_()){
                return botProfil.getObjectives_();
            }
        }
        return null;
    }

    public void printWinner(BotProfil botProfil){
        System.out.println("Le Bot gagne avec : "+botProfil.getPoints_() +" points");
    }
}
