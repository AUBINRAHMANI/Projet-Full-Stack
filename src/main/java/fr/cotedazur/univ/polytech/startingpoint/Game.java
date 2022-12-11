package fr.cotedazur.univ.polytech.startingpoint;

import java.util.ArrayList;

public class Game {

    final int NB_OBJECTIVE_TO_FINISH = 1;
    GameEngine gameEngine_;
    ArrayList<BotProfil> botProfils_;
    private Position positionPlacedDuringRound;

    public Game(){
        botProfils_                     = new ArrayList<>();
        positionPlacedDuringRound       = null;
        Deck<Objective> objectiveDeck   = generateObjectiveDrawPile();
        Deck<Plot> plotDeck             = generatePlotDrawPile();
        Map map                         = new Map();

        gameEngine_                     = new GameEngine( objectiveDeck, plotDeck, map);
        botProfils_.add(new BotProfil(new Bot()));
    }

    public void start(){
        do {
            for(BotProfil botProfil : botProfils_){
                botProfil.getBot_().play();
                computeCompletedObjective(botProfil.getBot_());
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

    public Deck<Objective> generateObjectiveDrawPile(){
        Deck<Objective> objectiveDeck = new Deck<>();
        objectiveDeck.addCard(new Objective(1, ObjectiveType.PLOT));
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

    public boolean askToSetPLot(Plot plot){
        return gameEngine_.askToSetPlot(plot);
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
        if(GameEngine.haveNeighbours(positionPlacedDuringRound))return true;
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
