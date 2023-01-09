package fr.cotedazur.univ.polytech.startingpoint;

import fr.cotedazur.univ.polytech.startingpoint.Action.*;
import fr.cotedazur.univ.polytech.startingpoint.objective.*;

import java.util.ArrayList;

public class Bot {

    Game game;

    public Bot() {
    }

    public Action play(Game game, Map map) {
        this.game = game;
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
