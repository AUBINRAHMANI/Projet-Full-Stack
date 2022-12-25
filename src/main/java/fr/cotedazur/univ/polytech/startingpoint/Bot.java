package fr.cotedazur.univ.polytech.startingpoint;

import java.util.ArrayList;

public class Bot {

    private Map map= null;

    public Bot() {
    }

    public void play(Game game, Map map) {
       this.map=map;
       Plot plot = game.pickPlot(this);
       if(positionToSet(plot)) {
           game.askToPutPLot(plot);
       }
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
