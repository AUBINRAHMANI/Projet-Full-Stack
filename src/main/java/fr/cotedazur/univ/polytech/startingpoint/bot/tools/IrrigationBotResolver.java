package fr.cotedazur.univ.polytech.startingpoint.bot.tools;

import fr.cotedazur.univ.polytech.startingpoint.*;
import fr.cotedazur.univ.polytech.startingpoint.action.Action;
import fr.cotedazur.univ.polytech.startingpoint.action.ActionType;
import fr.cotedazur.univ.polytech.startingpoint.action.PutIrrigationAction;
import fr.cotedazur.univ.polytech.startingpoint.game.Referee;

import java.util.List;

import static fr.cotedazur.univ.polytech.startingpoint.logger.Loggeable.LOGGER;

public class IrrigationBotResolver {

    Map map;
    Referee referee;

    public IrrigationBotResolver(Map map, Referee referee) {
        this.map = map;
        this.referee = referee;
    }

    public Action tryPutIrrigation(Position target, List<ActionType> banActionTypes) {
        if (banActionTypes.contains(ActionType.PUT_IRRIGATION)) {
            return null;
        }
        List<Position> path = map.getPathBetweenPositions(new Position(0, 0), target);
        if(path!=null) {
            for (int i = path.size() - 1; i > 0; --i) {
                Plot plot = map.findPlot(path.get(i));
                if (!plot.isIrrigated()) {
                    Action action = tryPutIrrigationOnPosition(path.get(i));
                    if (action != null) {
                        return action;
                    }else {
                        return tryPutIrrigationOnPosition(path.get(i-1));
                    }
                }
            }
        }
        return placeRandomIrrigation(banActionTypes);
    }

    private Action placeRandomIrrigation(List<ActionType> banActionTypes) {
        if(!banActionTypes.contains(ActionType.PUT_IRRIGATION)) {
            for (Plot plot : map.getMapPlots()) {
                if (!plot.isIrrigated()) {
                    Action action = tryPutIrrigationOnPosition(plot.getPosition());
                    if (action != null) return action;
                }
            }
            for (Plot plot : map.getMapPlots()) {
                if (plot.isIrrigated()) {
                    Action action = tryPutIrrigationOnPosition(plot.getPosition());
                    if (action != null) return action;
                }
            }
        }
        if(!banActionTypes.contains(ActionType.PUT_PLOT)) {
            PatternBotResolver patternBotResolver = new PatternBotResolver(map, referee);
            return patternBotResolver.putRandomlyAPLot(PlotType.GREEN, banActionTypes);
        }
        return null;
    }

    private Action tryPutIrrigationOnPosition(Position position) {
        for (Plot plot : map.getNeighbours(position)) {
            Irrigation irrigation = new Irrigation(position, plot.getPosition());
            if (map.isIrrigationLinked(irrigation) && !map.irrigationExist(irrigation)) {
                return new PutIrrigationAction(irrigation);
            }
        }
        return null;
    }
}
