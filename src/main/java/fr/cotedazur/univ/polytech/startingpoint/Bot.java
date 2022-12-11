package fr.cotedazur.univ.polytech.startingpoint;

import java.util.ArrayList;

public class Bot {

    private Map map= null;



    public Bot() {
    }

    public void play(Game game, Map map) {
       this.map=map;
       Plot plot = game.pickPlot(this);
       if(positionToSet(plot) != null) {
           game.askToSetPLot(plot);
       }
    }

    private Position positionToSet(Plot plot) {

       // ArrayList<Plot> NewList = new ArrayList<>();
        ArrayList<Position> potentialPositions;

        for(Plot plotMap : this.map.getMap()){
            potentialPositions = this.map.closestAvailableSpace(plotMap);
            if(potentialPositions!=null){
                plot.setPosition(plotMap.getPosition());
            }
        }
        return null;
        }

}
