package fr.cotedazur.univ.polytech.startingpoint;

import fr.cotedazur.univ.polytech.startingpoint.Action.*;
import fr.cotedazur.univ.polytech.startingpoint.objective.*;

import java.lang.reflect.Parameter;
import java.util.ArrayList;

public class Bot {

    Game game;
    Map map;

    public Bot(Game game, Map map) {
        this.map = map ;
    }

    public Action play(Game game, Map map) {
        this.game   = game;
        this.map    = map;
       ArrayList<Objective> objectives = game.getMyObjectives(this);
       if(objectives == null){
           assert false;
           return null;
       }
       else {
           if(objectives.isEmpty())return pickObjective();
           else {
               return objectives.get(0).tryToFillObjective(this);
           }
       }
    }

    pickObjectiveAction pickObjective(){
        return null;
    }

    public Action fillObjectiveGardener(int nbBambou, PlotType bambouType, boolean improvement, int nbSection) {
        return null;
    }

    public Action fillObjectivePlots(Pattern pattern){
        for(Plot plot : map.getMap()){
            if(plot.getType() == pattern.getPlots().get(0).getType()){
                Pattern patternToPlace = checkIfPossibleToPlacePattern(pattern, plot.getPosition());
                if( patternToPlace != null){
                    for(Plot tempPlot : patternToPlace.getPlots()){
                        if(map.isPossibleToPutPlot(tempPlot.getPosition())){
                            return new PutPlotAction(tempPlot);
                        }
                        else {
                            if(map.isSpaceFree(tempPlot.getPosition())){
                                return new PutPlotAction(new Plot(tempPlot.getType(), map.closestAvailableSpace(tempPlot.getPosition()).get(0)));
                            }
                        }
                    }
                }
            }
        }
        for(Plot plot : map.getMap()){
            return new PutPlotAction(new Plot(pattern.getPlots().get(0).getType(), map.closestAvailableSpace(plot.getPosition()).get(0)));
        }
        return null;
    }

    private Pattern checkIfPossibleToPlacePattern(Pattern pattern, Position position) {
        return null;
    }

    public Action fillObjectivePanda(ArrayList<Bambou> bambouSections){
        return null;
    }






    private boolean positionToSet(Map map, Plot plot) {

       // ArrayList<Plot> NewList = new ArrayList<>();
        ArrayList<Position> potentialPositions;
        for(Plot plotMap : map.getMap()){
            potentialPositions = map.closestAvailableSpace(plotMap.getPosition());
            if(potentialPositions.size() >0){
                plot.setPosition(potentialPositions.get(0));
                return true;
            }
        }
        return false;
    }
}
