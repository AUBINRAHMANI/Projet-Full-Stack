package fr.cotedazur.univ.polytech.startingpoint.bots.tools;

import fr.cotedazur.univ.polytech.startingpoint.game.action.Action;
import fr.cotedazur.univ.polytech.startingpoint.game.action.ActionType;
import fr.cotedazur.univ.polytech.startingpoint.game.action.PutIrrigationAction;
import fr.cotedazur.univ.polytech.startingpoint.game.Referee;
import fr.cotedazur.univ.polytech.startingpoint.game.game_engine.items.Irrigation;
import fr.cotedazur.univ.polytech.startingpoint.game.game_engine.map.Map;
import fr.cotedazur.univ.polytech.startingpoint.game.game_engine.map.Plot;
import fr.cotedazur.univ.polytech.startingpoint.game.game_engine.map.PlotType;
import fr.cotedazur.univ.polytech.startingpoint.game.game_engine.map.Position;

import java.util.List;


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
        if (path != null) {
            for (int i = path.size() - 1; i > 0; --i) {
                Plot plot = map.findPlot(path.get(i));
                if (!plot.isIrrigated()) {
                    Action action = tryPutIrrigationOnPosition(path.get(i), banActionTypes);
                    if (action != null) {
                        return action;
                    } else {
                        return tryPutIrrigationOnPosition(path.get(i - 1), banActionTypes);
                    }
                }
            }
        }
        return placeRandomIrrigation(banActionTypes);
    }

    private Action placeRandomIrrigation(List<ActionType> banActionTypes) {
        for (Plot plot : map.getMapPlots()) {
            if (!plot.isIrrigated()) {
                Action action = tryPutIrrigationOnPosition(plot.getPosition(), banActionTypes);
                if (action != null) return action;
            }
        }
        for (Plot plot : map.getMapPlots()) {
            if (plot.isIrrigated()) {
                Action action = tryPutIrrigationOnPosition(plot.getPosition(), banActionTypes);
                if (action != null) return action;
            }
        }
        PatternBotResolver patternBotResolver = new PatternBotResolver(map, referee);
        return patternBotResolver.putRandomlyAPLot(PlotType.GREEN, banActionTypes);
    }

    private Action tryPutIrrigationOnPosition(Position position, List<ActionType> banActionTypes) {
        for (Plot plot : map.getNeighbours(position)) {
            Irrigation irrigation = new Irrigation(position, plot.getPosition());
            if (map.isIrrigationLinked(irrigation) && !map.irrigationExist(irrigation) && !banActionTypes.contains(ActionType.PUT_IRRIGATION) ) {
                return new PutIrrigationAction(irrigation);
            }
        }
        return null;
    }
}
