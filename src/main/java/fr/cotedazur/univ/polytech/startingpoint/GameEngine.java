package fr.cotedazur.univ.polytech.startingpoint;

import fr.cotedazur.univ.polytech.startingpoint.objective.*;
import fr.cotedazur.univ.polytech.startingpoint.Action.*;
import java.util.ArrayList;

public class GameEngine {


    private Deck<Objective> objectiveDeck_;
    private Deck<Plot>      plotDeck_;
    private Map             map_;
    private Gardener        gardener_;


    public GameEngine(Deck<Objective> objectiveDeck, Deck<Plot> plotDeck, Map map) {
        objectiveDeck_              = objectiveDeck;
        plotDeck_                   = plotDeck;
        map_                        = map;
        gardener_                   = new Gardener();
    }

    public fr.cotedazur.univ.polytech.startingpoint.objective.Objective pickObjective() {
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

    public Position getGardenerPosition(){
        return gardener_.getPosition();
    }

    public boolean moveGardener(Position position){
        if(map_.isSpaceFree(position)){
            return false;
        }
        else{
            gardener_.setPosition(position);
            return true;
        }
    }

    public void growBambou(){
        if(map_.closestPlot(gardener_.getPosition()).isEmpty()){
            map_.growBambou(gardener_.getPosition());
        }
        else{
            for(Position position : map_.closestPlot(gardener_.getPosition())){
                map_.growBambou(position);
            }
        }
    }

    public void addBambouToSameSidePlot(){
        if(map_.closestPlot(gardener_.getPosition()).isEmpty()){

        }
    }
    /*
    public boolean computeObjectivePlot(ArrayList<Plot> configuration){
        return false;
    }

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
