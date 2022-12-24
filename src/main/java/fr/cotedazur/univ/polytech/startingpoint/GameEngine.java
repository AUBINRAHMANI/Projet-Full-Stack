package fr.cotedazur.univ.polytech.startingpoint;

import fr.cotedazur.univ.polytech.startingpoint.objective.*;
import java.util.ArrayList;

public class GameEngine {


    private Deck<Objective> objectiveDeck_;
    private Deck<Plot>      plotDeck_;
    private Map             map_;


    public GameEngine(Deck<Objective> objectiveDeck, Deck<Plot> plotDeck, Map map) {
        objectiveDeck_              = objectiveDeck;
        plotDeck_                   = plotDeck;
        map_                        = map;
    }

    public Objective pickObjective() {
        return objectiveDeck_.getNextCard();
    }

    public Plot pickPlot() {
        return plotDeck_.getNextCard();
    }

    public boolean askToPutPlot( Plot plot ){
        return  map_.putPlot(plot);
    }

    public Map getMap(){
        return map_;
    }

    public boolean haveNeighbours( Position position){
        return map_.haveNeighbours(position);
    }

    public boolean moveGardener(Position position){
        return false;
    }

    public boolean movePanda(Position position){
        return false;
    }

    public boolean computeObjectivePlot(ArrayList<Plot> pattern){
        return false;
    }
    public boolean computeObjectiveGardener(ArrayList<Plot> bambouPlots, boolean improvement){
        return false;
    }
    /*
    public boolean computeObjectivePanda(ArrayList<Bambou> bambous){
        return false;
    }

     */

}
