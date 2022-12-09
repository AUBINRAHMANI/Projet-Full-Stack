package fr.cotedazur.univ.polytech.startingpoint;

import java.util.ArrayList;

public class Bot {

    private ArrayList<Plot> map= null;



    public Bot() {
    }

    public void play(GameEngine gameEngine, Map map) {
       this.map=map.getMap();
       Position position = positionToSet();

    }

    ;


    private Position positionToSet() {

       // ArrayList<Plot> NewList = new ArrayList<>();
        ArrayList<Position> potentialPositions;

        for(Plot plot : this.map){
            potentialPositions = this.map.closestAvailableSpace();
            if(potentialPositions!=null){
                return plot.get_position();
            }
        }








        }

}
