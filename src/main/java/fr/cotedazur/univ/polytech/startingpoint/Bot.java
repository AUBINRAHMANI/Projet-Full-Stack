package fr.cotedazur.univ.polytech.startingpoint;

import fr.cotedazur.univ.polytech.startingpoint.Action.Action;
import fr.cotedazur.univ.polytech.startingpoint.Action.PutPlotAction;

import java.util.ArrayList;

public class Bot {

    private Map map= null;



    public Bot() {
    }

    public Action play(Game game, Map map) {
       this.map=map;
       Plot plot = game.pickPlot(this);
       if(positionToSet(plot)) {
           return new PutPlotAction(plot);
       }
       return null;
    }

    private boolean positionToSet(Plot plot) {

       // ArrayList<Plot> NewList = new ArrayList<>();
        ArrayList<Position> potentialPositions;
        for(Plot plotMap : this.map.getMap()){
            potentialPositions = this.map.closestAvailableSpace(plotMap.getPosition());
            if(potentialPositions.size() >0){
                plot.setPosition(potentialPositions.get(0));
                return true;
            }
        }
        return false;
        }

}
