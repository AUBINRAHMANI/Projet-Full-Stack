package fr.cotedazur.univ.polytech.startingpoint;

public class GameEngine {

    private Deck<Objective> objectiveDeck_;
    private Deck<Plot>      plotDeck_;
    private Map             map_;

    public GameEngine() {
        objectiveDeck_  = new Deck<Objective>();
        plotDeck_       = new Deck<Plot>();
        map_             = new Map();
    }

    public GameEngine(Deck<Objective> objectiveDeck, Deck<Plot> plotDeck, Map map) {
        objectiveDeck_  = objectiveDeck;
        plotDeck_       = plotDeck;
        map_            = map;
    }

    public Objective pickObjective() {
        return objectiveDeck_.getNextCard();
    }

    public Plot pickPlot() {
        return plotDeck_.getNextCard();
    }

    public boolean askToSetPlot( Position position ){
        if(map_.isPlotFree(position)){
            map_.setPlot(position);
            return true;
        }
        return false;
    }

    public Map GetMap(){
        final Map mapCp = map_;
        return mapCp;
    }
}
