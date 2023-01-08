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

    public ArrayList<Plot> haveNeighbours(Plot plot){
        return map_.getNeighbours(plot);
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
        if(map_.closestPlot(gardener_.getPosition()).isEmpty()){
            map_.growBambou(gardener_.getPosition());
        }
        else{
            for(Position position : map_.closestPlot(gardener_.getPosition())){
                map_.growBambou(position);
            }
        }
    }

    public boolean movePanda(Position position){
        if(!map_.isSpaceFree(position)){
            panda.setPosition(position);
            return true;
        }
        return false;
    }

    public boolean eatBambou(Position position){
       Plot plot = map_.findPlot(position);
       plot.eatBambou();
       return true;
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

    public boolean computeObjectiveGardener(int nbBambou, PlotType bambouType, boolean improvement, int nbSections){
        Plot plot = map_.findPlot(gardener_.getPosition());
        if(nbBambou> 3){
            if(plot.getNumberOfBambou() == nbBambou && plot.getType() == bambouType){
                return true;
            }
        }
        else {
            if(plot.getNumberOfBambou() != nbBambou || plot.getType() != bambouType)return  false;
            int nbValidatedPlots = 0;
            for(Plot neighbour : map_.getNeighbours(plot)){
                if(neighbour.getNumberOfBambou() == nbBambou && neighbour.getType() == bambouType){
                    nbValidatedPlots++;
                }
            }
            if(nbValidatedPlots == nbSections-1)return true;
        }
        return false;
    }

    public boolean computeObjectivePanda(BotProfil botProfil, ArrayList<Bambou> bambousToHave){
        ArrayList<Bambou> playerBambous = new ArrayList<>(botProfil.getBambous());
        ArrayList<Bambou> BambousToRemove = new ArrayList<>();

        for(Bambou bambou : bambousToHave){
            if(playerBambous.contains(bambou)){
                playerBambous.remove(bambou);
            }
            else {
                return false;
            }
        }
        botProfil.setBambous(playerBambous);
        return true;
    }
}
