package fr.cotedazur.univ.polytech.startingpoint;

public class GameEngine {

    public GameEngine() {

    }

    public Objective pickObjective() {
        return new Objective();
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
