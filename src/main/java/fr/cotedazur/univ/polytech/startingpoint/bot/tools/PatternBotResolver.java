package fr.cotedazur.univ.polytech.startingpoint.bot.tools;

import fr.cotedazur.univ.polytech.startingpoint.*;
import fr.cotedazur.univ.polytech.startingpoint.action.Action;
import fr.cotedazur.univ.polytech.startingpoint.action.ActionType;
import fr.cotedazur.univ.polytech.startingpoint.action.PutPlotAction;
import fr.cotedazur.univ.polytech.startingpoint.game.Referee;

import java.util.List;

public class PatternBotResolver {

    Map map;
    Referee referee;

    public PatternBotResolver(Map map, Referee referee){
        this.map = map;
        this.referee = referee;
    }

    public Action fillObjectivePlots(Pattern pattern, List<ActionType> banActionTypes){
        for(Plot plot : map.getMapPlots()){
            if(plot.getType() == pattern.getPlots().get(0).getType()){
                List<List<Plot>> result = map.checkIfPossibleToPlacePattern(pattern, plot.getPosition());
                if( result!=null ) {
                    List<Plot> missingPlots = result.get(0);
                    List<Plot> nonIrrigatedPlots = result.get(1);
                    if( !banActionTypes.contains(ActionType.PUT_PLOT) ) {
                        for (Plot tempPlot : missingPlots) {
                            Position tempPlotPosition = tempPlot.getPosition();
                            if (map.isPossibleToPutPlot(tempPlotPosition)) {
                                return new PutPlotAction(tempPlot);
                            } else {
                                if ( !map.getNeighbours(tempPlotPosition).isEmpty() ) {
                                    List<Position> positions = map.closestAvailableSpace(tempPlotPosition);
                                    for (Position position : positions) {
                                        if (map.isPossibleToPutPlot(position)) {
                                            return new PutPlotAction(new Plot(tempPlot.getType(), position));
                                        }
                                    }

                                }
                            }
                        }
                    }
                    if ( !nonIrrigatedPlots.isEmpty() ) {
                        IrrigationBotResolver irrigationBotResolver = new IrrigationBotResolver(map, referee);
                        Action action = irrigationBotResolver.tryPutIrrigation(nonIrrigatedPlots.get(0).getPosition(), banActionTypes);
                        if(action!=null)return action;
                    }
                }
            }
        }
        if( !banActionTypes.contains(ActionType.PUT_PLOT) ) {
            for (Plot plot : map.getMapPlots()) {
                if ( !map.closestAvailableSpace(plot.getPosition()).isEmpty() ) {
                    return new PutPlotAction(new Plot(pattern.getPlots().get(0).getType(), map.closestAvailableSpace(plot.getPosition()).get(0)));
                }
            }
        }
        return null;
    }


    public PutPlotAction placePLot(PlotType plotType, Position position, List<ActionType> banActionTypes){
        List<Plot> plots = referee.pickPlot();
        for(Plot plot : plots){
            if(plotType == null){
                plot.setPosition(position);
                return new PutPlotAction(plot);
            }
            if(plot.getType() == plotType){
                plot.setPosition(position);
                return new PutPlotAction(plot);
            }
        }
        return putRandomlyAPLot(plots.get(0).getType(), banActionTypes);
    }

    public PutPlotAction putRandomlyAPLot(PlotType plotType, List<ActionType> banActionTypes){
        if( !banActionTypes.contains(ActionType.PUT_PLOT) ) {
            for (Plot plot : map.getMapPlots()) {
                List<Position> positions = map.closestAvailableSpace(plot.getPosition());
                if (positions != null && !positions.isEmpty() ) {
                    return placePLot(plotType, positions.get(0), banActionTypes);
                }
            }
        }
        return null;
    }
}
