package fr.cotedazur.univ.polytech.startingpoint;

public class GameEngine {

    public GameEngine(Deck<Objective> objectiveDeck, Deck<Plot> plotDeck,Map map){

    }

    public Objective pickObjective() {
        return null;
    }

    public Plot pickPlot() {
        return new Plot(PlotType.GREEN);
    }

    public boolean askToSetPlot( Position position ){
        return false;
    }

    public Map GetMap(){
        return null;
    }
}
