package fr.cotedazur.univ.polytech.startingpoint;

import fr.cotedazur.univ.polytech.startingpoint.objective.*;
import fr.cotedazur.univ.polytech.startingpoint.Action.*;
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
    public boolean moveGardener(Position position){
        return false;
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
                    System.out.println(tempPattern);
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
    public boolean computeObjectiveGardener(ArrayList<Plot> bambouPlots, boolean improvement){
        return false;
    }
    /*
    public boolean computeObjectivePanda(ArrayList<Bambou> bambous){
        return false;
    }

     */

}
