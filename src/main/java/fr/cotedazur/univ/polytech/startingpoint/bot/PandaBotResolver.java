package fr.cotedazur.univ.polytech.startingpoint.bot;

import fr.cotedazur.univ.polytech.startingpoint.*;
import fr.cotedazur.univ.polytech.startingpoint.action.Action;
import fr.cotedazur.univ.polytech.startingpoint.action.ActionType;
import fr.cotedazur.univ.polytech.startingpoint.action.MovePandaAction;
import fr.cotedazur.univ.polytech.startingpoint.game.Referee;

import java.util.ArrayList;
import java.util.List;

public class PandaBotResolver {

    Map map;
    Referee referee;
    Bot bot;

    public PandaBotResolver(Map map, Referee referee, Bot bot){
        this.map = map;
        this.referee = referee;
        this.bot = bot;
    }


    public Action fillObjectivePanda(List<Bambou> bambouSections, List<Bambou> myBambous, List<ActionType> banActionTypes){
        ArrayList<Bambou> missingBambous = new ArrayList<>(bambouSections);
        for(Bambou bambou : myBambous)removeBambou(missingBambous ,bambou);
        if( missingBambous.isEmpty()==false ){
            if(banActionTypes.contains(ActionType.MOVE_PANDA) ==false ) {
                for (Bambou bambou : missingBambous) {
                    MovePandaAction action = movePandaOnPlantation(bambou.getBambouType());
                    if (action != null) return action;
                }
                for (Plot plot : map.getMapPlots()) {
                    if (plot.getType() == missingBambous.get(missingBambous.size() - 1).getBambouType() && plot.getNumberOfBambou() > 0) {
                        return movePandaToUnlock(referee.getPandaPosition());
                    }
                }
            }
            GardenerBotResolver gardenerBotResolver = new GardenerBotResolver(map, referee);
            return gardenerBotResolver.fillObjectiveGardener(missingBambous.get(missingBambous.size()-1).getBambouType(), false, banActionTypes);
        }
        PatternBotResolver patternBotResolver = new PatternBotResolver(map, referee);
        return patternBotResolver.putRandomlyAPLot(bambouSections.get(0).getBambouType(), banActionTypes);
    }

    private MovePandaAction movePandaOnPlantation(PlotType bambouType) {
        for(Plot plot : map.getMapPlots()){
            if(plot.getType()==bambouType && plot.getNumberOfBambou()>0 && plot.getPosition().isDeplacementALine(referee.getPandaPosition()))
            {
                return new MovePandaAction(bot, plot.getPosition());
            }
        }
        return null;
    }

    private void removeBambou(ArrayList<Bambou> bambous, Bambou bambouToRemove){
        for(int i=0; i<bambous.size() ; ++i){
            if(bambous.get(i).getBambouType() == bambouToRemove.getBambouType()){
                bambous.remove(i);
                break;
            }
        }
    }

    private MovePandaAction movePandaToUnlock(Position pandaPosition){
        List<Plot> potentialPlot = map.getNeighbours(pandaPosition);
        if( potentialPlot!=null ){
            return new MovePandaAction(bot, potentialPlot.get(0).getPosition());
        }
        return null;
    }

}
