package fr.cotedazur.univ.polytech.startingpoint;

import fr.cotedazur.univ.polytech.startingpoint.Action.*;
import fr.cotedazur.univ.polytech.startingpoint.debugInterface.MapInterface;
import fr.cotedazur.univ.polytech.startingpoint.objective.*;

import java.util.ArrayList;
import java.util.Currency;

public class Game {

    final int NB_OBJECTIVE_TO_FINISH = 2;
    GameEngine gameEngine_;
    ArrayList<BotProfil> botProfils_;
    MapInterface _mapInterface;

    BotProfil currentPlayer;

    public Game(boolean debug){
        botProfils_                     = new ArrayList<>();
        Deck<Objective> objectiveDeck   = generateObjectiveDrawPile();
        Deck<Plot> plotDeck             = generatePlotDrawPile();
        currentPlayer = null;

        if(debug){
            _mapInterface   = new MapInterface();
            Map map                     = new Map(_mapInterface);
            gameEngine_                 = new GameEngine( objectiveDeck, plotDeck, map);
        }
        else {
            Map map= new Map();
            gameEngine_                 = new GameEngine( objectiveDeck, plotDeck, map);
        }

        botProfils_.add(new BotProfil(new Bot()));
    }
    public Game(){
        this(false);
    }

    public void start(){
        Action action;
        do {
            botProfils_.get(0).addObjective(gameEngine_.pickObjective());
            for(BotProfil botProfil : botProfils_){
                currentPlayer = botProfil;
                while (_mapInterface.next()==false);
                action = botProfil.getBot_().play(this, gameEngine_.getMap());
                action.play(gameEngine_);
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

    public Deck<Objective> generateObjectiveDrawPile(){
        Deck<Objective> objectiveDeck = new Deck<>();
        objectiveDeck.addCard(new ObjectivePlots(1, null));
        objectiveDeck.addCard(new ObjectivePlots(1, null));
        objectiveDeck.shuffle();
        return objectiveDeck;
    }

    public Deck<Plot> generatePlotDrawPile(){
        Position position = new Position(1,0);
        Position position2 = new Position(1,1);
        Deck<Plot> plotDeck = new Deck<>();
        plotDeck.addCard(new Plot(PlotType.GREEN,position));
        plotDeck.addCard(new Plot(PlotType.GREEN,position2));
        plotDeck.shuffle();
        return plotDeck;
    }

    public boolean askToPutPLot(Plot plot){
        if(gameEngine_.askToPutPlot(plot)){
            currentPlayer.setPositionPlacedDuringRound_(plot.getPosition());
            return true;
        }
        return false;
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

    public Plot pickPlot(Bot bot){
        return gameEngine_.pickPlot();
    }


    public boolean computeObjectivesPlot(){
        for(BotProfil botProfil : botProfils_ ){
            for(Objective objective : botProfil.getObjectives_()){
                if(!objective.verifyPlotObj(gameEngine_)){
                    return false;
                }
            }
        }
        return true;
    }


    public boolean computeObjectivesGardener(){
        for(BotProfil botProfil : botProfils_ ){
            for(Objective objective : botProfil.getObjectives_()){
                if(!objective.verifyGardenerObj(gameEngine_)){
                    return false;
                }
            }
        }
        return true;
    }

    public boolean computeObjectivesPanda(){
        for(BotProfil botProfil : botProfils_ ){
            for(Objective objective : botProfil.getObjectives_()){
                if(!objective.verifyPandaObj(gameEngine_)){
                    return false;
                }
            }
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

    public void printWinner(BotProfil botProfil){
        System.out.println("Le Bot gagne avec : "+botProfil.getPoints_() +" points");
    }
}
