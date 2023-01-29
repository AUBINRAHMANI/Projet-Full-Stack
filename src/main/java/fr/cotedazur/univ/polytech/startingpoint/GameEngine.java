package fr.cotedazur.univ.polytech.startingpoint;

import fr.cotedazur.univ.polytech.startingpoint.Game.Game;
import fr.cotedazur.univ.polytech.startingpoint.Game.Referee;
import fr.cotedazur.univ.polytech.startingpoint.objective.*;

import java.sql.Ref;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

    public void regenerateDecks(Deck<Objective> objectiveDeck, Deck<Plot> plotDeck){
        objectiveDeck_  = objectiveDeck;
        plotDeck_       = plotDeck;
    }

    public Objective pickObjective() {
        return objectiveDeck_.getNextCard();
    }

    public List<Plot> pickPlot() {
        return new ArrayList<Plot>(Arrays.asList(plotDeck_.getNextCard(),plotDeck_.getNextCard(),plotDeck_.getNextCard()));
    }

    public boolean askToPutPlot( Plot plot ){
        return  map_.putPlot(plot);
    }

    public Map getMap(){
        return map_;
    }

    public Position getGardenerPosition(){
        return gardener_.getPosition();
    }
    public Position getPandaPosition(){
        return panda.getPosition();
    }

    public boolean moveGardener(Position position){
        if(!map_.isSpaceFree(position) && position.isDeplacementALine(gardener_.getPosition())){
            gardener_.setPosition(position);
            growBambou();
            return true;
        }
        return false;
    }

    public void growBambou(){
        Plot gardenerPlot = map_.findPlot(gardener_.getPosition());
        if( gardenerPlot.getPosition().isCenter()==false )gardenerPlot.growBambou();
        for(Plot plot : map_.getNeighbours(gardener_.getPosition())){
            if((plot.getType() == gardenerPlot.getType()) && plot.isIrrigated() && plot.getPosition().isCenter()==false ){
                plot.growBambou();
            }
        }
    }

    public boolean movePanda(Referee referee, Bot bot, Position position){
        if(!map_.isSpaceFree(position) && position.isDeplacementALine(panda.getPosition())){
            panda.setPosition(position);
            eatBambou(referee, bot, position);
            return true;
        }
        return false;
    }

    public boolean eatBambou(Referee referee, Bot bot, Position position){
       Plot plot = map_.findPlot(position);
       Bambou bambou = plot.eatBambou();
       if( bambou!=null && referee!=null )referee.addBamboutToBot(bot, bambou);
       return true;
    }


    public boolean computeObjectivePlot(Pattern pattern, Plot lastPLacedPlot){
        ArrayList<Plot> missingPlots = map_.checkIfPossibleToPlacePattern(pattern, lastPLacedPlot.getPosition());
        if( missingPlots != null && missingPlots.isEmpty()){
            return true;
        }
        else return false;
    }


    public boolean computeObjectiveGardener(int nbBambou, PlotType bambouType, boolean improvement, int nbPlot){
        Plot plot = map_.findPlot(gardener_.getPosition());
        if(nbBambou> 3){
            if(plot.getNumberOfBambou() <= nbBambou && plot.getType() == bambouType){
                return true;
            }
        }
        else {
            if(plot.getNumberOfBambou() <= nbBambou || plot.getType() != bambouType)return  false;
            int nbValidatedPlots = 0;
            for(Plot neighbour : map_.getNeighbours(plot.getPosition())){
                if(neighbour.getNumberOfBambou() >= nbBambou && neighbour.getType() == bambouType){
                    nbValidatedPlots++;
                }
            }
            if(nbValidatedPlots >= nbPlot-1)return true;
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
