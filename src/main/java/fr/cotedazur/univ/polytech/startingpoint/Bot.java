package fr.cotedazur.univ.polytech.startingpoint;

import java.util.ArrayList;

public class Bot {

    private Map map= null;



    public Bot() {
    }

    public void play(GameEngine gameEngine, Map map) {
       this.map=map;
       Position position = positionToSet();

    }

    ;


    private Position positionToSet() {

       // ArrayList<Plot> NewList = new ArrayList<>();
        ArrayList<Position> potentialPositions;

        for(Plot plot : this.map.getMap()){
            potentialPositions = this.map.closestAvailableSpace(plot);
            if(potentialPositions!=null){
                return plot.getPosition();
            }
        }
        return null;
        }

}
