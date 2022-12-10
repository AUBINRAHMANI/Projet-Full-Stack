package fr.cotedazur.univ.polytech.startingpoint;

public class Game {

    GameEngine gameEngine;
    Bot bot;

    public Game(){
        Deck<Objective> objectiveDeck   = generateObjectiveDrawPile();
        Deck<Plot> plotDeck             = generatePlotDrawPile();
        Map map                         = new Map();

        gameEngine = new GameEngine( objectiveDeck, plotDeck, map);
        bot = new Bot();
    }

    public Deck<Objective> generateObjectiveDrawPile(){
        return null;
    }

    public Deck<Plot> generatePlotDrawPile(){
        return null;
    }

    public boolean setPLot(){
        return false;
    }

    public Objective pickObjective(){
        return null;
    }

    public void computeCompletedObjective(){

    }

    public boolean checkFinishCondition(){
        return false;
    }

    public int checkWinner(){
        return -1;
    }

    public void printWinner(){

    }
}
