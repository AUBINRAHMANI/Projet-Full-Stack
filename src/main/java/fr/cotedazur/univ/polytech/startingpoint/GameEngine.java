package fr.cotedazur.univ.polytech.startingpoint;

import fr.cotedazur.univ.polytech.startingpoint.objective.*;
import fr.cotedazur.univ.polytech.startingpoint.Action.*;
import java.util.ArrayList;

public class GameEngine {


    private Deck<Objective> objectiveDeck_;
    private Deck<Plot>      plotDeck_;
    private Map             map_;
    private Gardener        gardener_;

    private Panda           panda;


    public GameEngine(Deck<Objective> objectiveDeck, Deck<Plot> plotDeck, Map map) {
        objectiveDeck_              = objectiveDeck;
        plotDeck_                   = plotDeck;
        map_                        = map;

        panda                       = new Panda();

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

    public boolean haveNeighbours( Position position){
        return map_.haveNeighbours(position);
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
        if(!map_.isSpaceFree(position)){
            panda.setPosition(position);
            return true;
        }
        else{
            System.out.println("Veuillez bouger votre pandat sur une plote existante");

        }
        return false;
    }

    public boolean eatBambou(Position position){
       Plot plot = map_.findPlot(position);
       plot.eatBambou();
       return true;
    }


    public boolean computeObjectivePlot(ArrayList<Plot> pattern){
        return false;
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
