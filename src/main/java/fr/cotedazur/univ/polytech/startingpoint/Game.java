package fr.cotedazur.univ.polytech.startingpoint;

import fr.cotedazur.univ.polytech.startingpoint.debugInterface.MapInterface;

import java.security.Principal;
import java.util.ArrayList;
import java.util.TreeMap;

public class Game {

    final int NB_OBJECTIVE_TO_FINISH = 2;
    GameEngine gameEngine_;
    ArrayList<BotProfil> botProfils_;
    MapInterface _mapInterface;
    private Position positionPlacedDuringRound_;

    public Game(boolean debug){
        botProfils_                     = new ArrayList<>();
        positionPlacedDuringRound_       = null;
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

        botProfils_.add(new BotProfil(new Bot()));
    }
    public Game(){
        this(false);
    }

    public void start(){
        do {
            botProfils_.get(0).addObjective(gameEngine_.pickObjective());
            for(BotProfil botProfil : botProfils_){
                while (_mapInterface.next()==false);
                botProfil.getBot_().play(this, gameEngine_.getMap());
                computeCompletedObjective(botProfil.getBot_());
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
        objectiveDeck.addCard(new ObjectivePlot(1, ObjectiveType.PLOT, 2));
        objectiveDeck.addCard(new ObjectivePlot(1, ObjectiveType.PLOT, 2));
        objectiveDeck.shuffle();
        return objectiveDeck;
    }

    public Deck<Plot> generatePlotDrawPile(){
        Deck<Plot> plotDeck = new Deck<>();
        plotDeck.addCard(new Plot(PlotType.GREEN));
        plotDeck.addCard(new Plot(PlotType.GREEN));
        plotDeck.shuffle();
        return plotDeck;
    }

    public boolean askToPutPLot(Plot plot){
        if(gameEngine_.askToPutPlot(plot)){
            positionPlacedDuringRound_ = plot.getPosition();
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

    public void computeCompletedObjective(Bot bot){
        for(BotProfil botProfil : botProfils_){
            if(botProfil.getBot_() == bot && botProfil.getObjectives_().size()>0){
                ArrayList<Objective> objectivesCopy = (ArrayList<Objective>) botProfil.getObjectives_().clone();
                for(Objective objective : objectivesCopy){
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
        if(positionPlacedDuringRound_ != null) {
            if (gameEngine_.haveNeighbours(positionPlacedDuringRound_)) return true;
        }
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
        System.out.println("Le Bot gagne avec : "+botProfil.getPoints_() +" points");
    }
}
