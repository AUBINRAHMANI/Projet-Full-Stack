package fr.cotedazur.univ.polytech.startingpoint;

import fr.cotedazur.univ.polytech.startingpoint.objective.*;

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

    public boolean isObjectivePlotCompleted(ObjectivePlots objectivePlots, BotProfil botProfil){
        if(positionPlacedDuringRound_ != null) {
            if (haveNeighbours(positionPlacedDuringRound_)) return true;
        }
        return false;
    }
    /*
    public boolean isObjectiveGardenerCompleted(ObjectiveGardener objectiveGardener, BotProfil botProfil){
        Position gardenerPosition = getGardenerPosition();
        Plot gardenerPlot = getMap().getPlot(gardenerPosition);
        ArrayList<Bambou> bambouSections = gardenerPlot.getBambouSections();
        if(bambouSections.size() == objectiveGardener.getNbBambouSections()){
            return true;
        }
        return false;
    }



    public boolean isObjectivePandaCompleted(ObjectivePanda objectivePanda, BotProfil botProfil){
        return false;
    }

     */

}
