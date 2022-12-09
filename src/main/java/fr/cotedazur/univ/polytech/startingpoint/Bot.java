package fr.cotedazur.univ.polytech.startingpoint;

import java.util.ArrayList;

public class Bot {

    private Map map= null;



    public Bot() {
    }

    public void play(Plot P) {
       p.setPosition();
    }

    ;


    public void plotToSet(Game gameEngine, Map map) {
         int i=0;
         this.map=map.getMap();
         Plot FIRST_PLOT= this.map.get(0);
       // ArrayList<Plot> NewList = new ArrayList<>();

        if(map.closestAvailableSpace(FIRST_PLOT!=null){
            this.play(FIRST_PLOT);
        }
        else {
            while((map.closestAvailableSpace(this.map.get(i)).size)==null){
                i++;
            }
            this.play(this.map.get(i));

             }

        }

}
