package fr.cotedazur.univ.polytech.startingpoint.bot.tools;

import fr.cotedazur.univ.polytech.startingpoint.*;
import fr.cotedazur.univ.polytech.startingpoint.action.Action;
import fr.cotedazur.univ.polytech.startingpoint.action.ActionType;
import fr.cotedazur.univ.polytech.startingpoint.action.MoveGardenerAction;
import fr.cotedazur.univ.polytech.startingpoint.game.Referee;
import fr.cotedazur.univ.polytech.startingpoint.action.RainAction;

import java.util.ArrayList;
import java.util.List;

public class GardenerBotResolver {

    Map map;
    Referee referee;

    public  GardenerBotResolver(Map map, Referee referee){
        this.map = map;
        this.referee = referee;
    }

    public Action fillObjectiveGardener(PlotType bambouType, List<ActionType> banActionTypes, WeatherType weather) {
        ArrayList<Plot> typeValid = new ArrayList<>();
        ArrayList<Plot> typeAndDeplacementValid = new ArrayList<>();
        int maxNbBambou = 0;
        int indexMaxNbBambou = 0;
        if (map.getMapPlots().size() > 1 && !banActionTypes.contains(ActionType.MOVE_GARDENER)) {
            for (Plot plot : map.getMapPlots()) {
                if(plot.isIrrigated() && plot.getNumberOfBambou()<4 ){
                    if (plot.getType() == bambouType) {
                        typeValid.add(plot);
                    }
                    if((!typeValid.isEmpty()) && (weather == WeatherType.RAIN)){
                        for(int i = 3; i>0; i--) {
                            for (Plot plot1 : typeValid) {
                                if (plot1.getNumberOfBambou() == i) {
                                    return new RainAction(plot1.getPosition());
                                }
                            }
                        }
                    }
                    else if ((plot.getType() == bambouType) && (plot.getPosition().isDeplacementALine(referee.getGardenerPosition()))) {
                        typeAndDeplacementValid.add(plot);
                    }
                }
            }
            if ( !typeAndDeplacementValid.isEmpty()) {
                for (int i = 0; i < typeAndDeplacementValid.size(); i++) {
                    int numberOfBambou  = typeAndDeplacementValid.get(i).getNumberOfBambou();
                    if ( numberOfBambou > maxNbBambou) {
                        maxNbBambou = typeAndDeplacementValid.get(i).getNumberOfBambou();
                        indexMaxNbBambou = i;
                    }
                }
                return new MoveGardenerAction(typeAndDeplacementValid.get(indexMaxNbBambou).getPosition());

            } else if (!typeValid.isEmpty()) {
                for (Plot plot : typeValid) {
                    List<Plot> neighboursPlots = map.getNeighbours(plot.getPosition());
                    for (int i = 0; i < neighboursPlots.size(); i++) {
                        if (neighboursPlots.get(i).getPosition().isDeplacementALine(referee.getGardenerPosition())) {
                            return new MoveGardenerAction(neighboursPlots.get(i).getPosition());
                        }
                        List<Plot> neighboursOfNeighboursPlots = map.getNeighbours(neighboursPlots.get(i).getPosition());
                        for (int j = 0; j < neighboursOfNeighboursPlots.size(); j++) {
                            if (neighboursOfNeighboursPlots.get(j).getPosition().isDeplacementALine(referee.getGardenerPosition())) {
                                return new MoveGardenerAction(neighboursOfNeighboursPlots.get(j).getPosition());
                            }
                        }
                    }
                }
            }
        }
        PatternBotResolver patternBotResolver = new PatternBotResolver(map, referee);
        return patternBotResolver.putRandomlyAPLot(bambouType, banActionTypes);
    }
}
