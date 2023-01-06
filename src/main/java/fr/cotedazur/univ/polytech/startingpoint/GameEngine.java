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

    public ArrayList<Plot> haveNeighbours(Plot plot){
        return map_.getNeighbours(plot);
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
        map_.growBambou(gardener_.getPosition());
    }

    public boolean movePanda(Position position){
        return false;
    }


    public boolean computeObjectivePlot(Pattern pattern, Plot lastPLacedPlot){
        int area_size = pattern.size();
        Position lastPlacedPosition = lastPLacedPlot.getPosition();
        for (int i=0; i<area_size/2 ; ++i){
            pattern.translateRight();
        }
        for (int i=0; i<area_size/4 ; ++i){
            pattern.translateUp();
        }

        for (int i=0; i<area_size ; ++i){
            for (int j=0; j<6 ; ++j){
                for (int k=0 ; k<area_size ; ++k){
                    Pattern tempPattern = new Pattern(pattern);
                    tempPattern.applyMask(lastPlacedPosition);
                    if(computePatternVerification(tempPattern))return true;
                    pattern.translateDown();
                }
                for (int k=0 ; k<area_size ; ++k){
                    pattern.translateUp();
                }
                pattern.rotate60Right();
            }
            pattern.translateLeft();
        }
        return false;
    }
    private boolean computePatternVerification(Pattern pattern){
        for(Plot plot : pattern.getPlots()){
            if(map_.isSpaceFree(plot.getPosition()) || plot.getPosition().isCenter())return false;
        }
        return true;
    }

    public boolean computeObjectiveGardener(int nbBambou, PlotType bambouType, boolean improvement){
        if(nbBambou> 3){
            Plot plot = map_.findPlot(gardener_.getPosition());
            System.out.println(plot.getNumberOfBambou());
            System.out.println(plot.getType());
            if(plot.getNumberOfBambou() == nbBambou && plot.getType() == bambouType){
                return true;
            }
        }
        else {

        }
        return false;
    }

    public boolean computeObjectivePanda(ArrayList<Bambou> bambous){
        return false;
    }
}
