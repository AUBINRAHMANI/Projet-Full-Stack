package fr.cotedazur.univ.polytech.startingpoint;

import fr.cotedazur.univ.polytech.startingpoint.Action.*;
import fr.cotedazur.univ.polytech.startingpoint.debugInterface.MapInterface;
import fr.cotedazur.univ.polytech.startingpoint.objective.*;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Currency;
import java.util.Random;

public class Game {

    final int NB_OBJECTIVE_TO_FINISH = 1;
    GameEngine gameEngine_;
    ArrayList<BotProfil> botProfils_;
    private Position positionPlacedDuringRound;

    public Game(boolean debug){
        botProfils_                     = new ArrayList<>();
        positionPlacedDuringRound       = null;
        Deck<Objective> objectiveDeck   = generateObjectiveDrawPile();
        Deck<Plot> plotDeck             = generatePlotDrawPile();

        if(debug){
            _mapInterface   = new MapInterface();
            Map map                     = new Map(_mapInterface);
            gameEngine_                 = new GameEngine( objectiveDeck, plotDeck, map);
        }
        else {
            Map map= new Map();
            gameEngine_                 = new GameEngine( objectiveDeck, plotDeck, map);
        }

        gameEngine_                     = new GameEngine( objectiveDeck, plotDeck, map);
        botProfils_.add(new BotProfil(new Bot()));
        botProfils_.get(0).addObjective(gameEngine_.pickObjective());
    }

    public void start(){
        do {
            for(BotProfil botProfil : botProfils_){
                if(_mapInterface != null) while (_mapInterface.next()==false);
                Action action = botProfil.getBot_().play(this, gameEngine_.getMap());
                action.play(gameEngine_);
                action.verifyObjectiveAfterAction(this);
            }
        }while (checkFinishingCondition());
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

        for (int i=0 ; i<20 ; ++i){
            objectiveDeck.addCard(new ObjectivePlots(rand.nextInt(4)+1, new Pattern()));
        }
        for (int i=0 ; i<20 ; ++i){
            int nbBambous = rand.nextInt(2)+3;
            if(nbBambous == 3){
                objectiveDeck.addCard(new ObjectiveGardener(rand.nextInt(4)+1, nbBambous, PlotType.values()[rand.nextInt(upperRandForPlotType)+1], false,rand.nextInt(3)+2));
            }
            else {
                objectiveDeck.addCard(new ObjectiveGardener(rand.nextInt(4)+1, nbBambous, PlotType.values()[rand.nextInt(upperRandForPlotType)+1], false,1));
            }

        }
        for (int i=0 ; i<20 ; ++i){
            ArrayList<Bambou> bambous = new ArrayList<>();
            for(int j=0 ; j<(rand.nextInt(2)+2) ; ++j){
                bambous.add(new Bambou(PlotType.values()[rand.nextInt(upperRandForPlotType)+1]));
            }
            objectiveDeck.addCard(new ObjectivePanda(rand.nextInt(4)+1, bambous));
        }
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

    public boolean askToSetPLot(Plot plot){
        return gameEngine_.askToPutPlot(plot);
    }

    public Objective pickObjective(Bot bot){
        Objective objective=  gameEngine_.pickObjective();

        for(BotProfil botProfil : botProfils_){
            if(bot == botProfil.getBot_()){
                botProfil.addObjective(objective);
                return objective;
            }
        }
        return null;
    }

    public Plot pickPlot(){
        return gameEngine_.pickPlot();
    }

    public void computeCompletedObjective(Bot bot){
        for(BotProfil botProfil : botProfils_){
            if(botProfil.getBot_() == bot){
                for(Objective objective : botProfil.getObjectives_()){
                    switch (objective.getType()){
                        case PLOT ->{
                            if(isObjectivePlotCompleted((ObjectivePlot) objective)){
                                botProfil.setObjectiveCompleted(objective);
                            }
                        }
                    }
                }
            }
        }
    }

    public boolean isObjectivePlotCompleted(ObjectivePlot objectivePlot){
        if(gameEngine_.haveNeighbours(positionPlacedDuringRound))return true;
        return false;
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

    public void printWinner(BotProfil botProfil){
        System.out.println("Le Bot gagne avec : "+botProfil.getPoints_() +"points");
    }
}
