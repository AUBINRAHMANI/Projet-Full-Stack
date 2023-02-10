package fr.cotedazur.univ.polytech.startingpoint.bots.tools;

import fr.cotedazur.univ.polytech.startingpoint.game.game_engine.map.Map;
import fr.cotedazur.univ.polytech.startingpoint.game.game_engine.map.Plot;
import fr.cotedazur.univ.polytech.startingpoint.game.game_engine.map.PlotType;
import fr.cotedazur.univ.polytech.startingpoint.game.game_engine.items.WeatherType;
import fr.cotedazur.univ.polytech.startingpoint.game.action.Action;
import fr.cotedazur.univ.polytech.startingpoint.game.action.ActionType;
import fr.cotedazur.univ.polytech.startingpoint.game.action.MoveGardenerAction;
import fr.cotedazur.univ.polytech.startingpoint.game.action.RainAction;
import fr.cotedazur.univ.polytech.startingpoint.game.Referee;

import java.util.ArrayList;
import java.util.List;

public class GardenerBotResolver {

    Map map;
    Referee referee;

    public GardenerBotResolver(Map map, Referee referee) {
        this.map = map;
        this.referee = referee;
    }

    public Action fillObjectiveGardener(PlotType bambooType, List<ActionType> banActionTypes, WeatherType weather) {
        List<Plot> typeValid = getValidPlots(bambooType);
        List<Plot> typeAndMovementValid = getValidPlotsOnDirectLine(typeValid);
        Plot plotWithMaxBamboo = getPlotWithMaxBamboo(typeAndMovementValid);
        if (!banActionTypes.contains(ActionType.MOVE_GARDENER)) {
            if (plotWithMaxBamboo != null) {
                return new MoveGardenerAction(plotWithMaxBamboo.getPosition());
            } else if (!typeValid.isEmpty()) {
                Plot nearestPlot = getNearestValidPlotWithValidDisplacement(typeValid);
                if (nearestPlot != null) {
                    return new MoveGardenerAction(nearestPlot.getPosition());
                }
            }
        }

        if (weather == WeatherType.RAIN) {
            Plot plotToRain = getPlotToRain(typeValid);
            if (plotToRain != null) {
                return new RainAction(plotToRain.getPosition());
            }
        }

        return new PatternBotResolver(map, referee).putRandomlyAPLot(bambooType, banActionTypes);
    }

    private Plot getNearestValidPlotWithValidDisplacement(List<Plot> typeValid) {
        for (Plot plot : typeValid) {
            if (!map.getNeighbours(plot.getPosition()).isEmpty()) {
                List<Plot> neighboursPlots = map.getNeighbours(plot.getPosition());
                for (Plot neighboursPlot : neighboursPlots) {
                    if (neighboursPlot.getPosition().isMovementALine(referee.getGardenerPosition())) {
                        return neighboursPlot;
                    }
                }
            }
        }
        return null;
    }

    private Plot getPlotWithMaxBamboo(List<Plot> typeAndMovementValid) {
        Plot plotWithMaxBamboo = null;
        for (Plot plot : typeAndMovementValid) {
            if (plotWithMaxBamboo == null || plotWithMaxBamboo.getNumberOfBamboo() < plot.getNumberOfBamboo()) {
                plotWithMaxBamboo = plot;
            }
        }
        return plotWithMaxBamboo;
    }

    private Plot getPlotToRain(List<Plot> typeValid) {
        Plot plotToRain = null;
        for (Plot plot : typeValid) {
            if (plotToRain == null || plotToRain.getNumberOfBamboo() < plot.getNumberOfBamboo()) {
                plotToRain = plot;
            }
        }
        return plotToRain;
    }

    private List<Plot> getValidPlotsOnDirectLine(List<Plot> typeValid) {
        List<Plot> typeAndMovementValid = new ArrayList<>();
        for (Plot plot : typeValid) {
            if (plot.getPosition().isMovementALine(referee.getGardenerPosition())) {
                typeAndMovementValid.add(plot);
            }
        }
        return typeAndMovementValid;
    }

    private List<Plot> getValidPlots(PlotType bambooType) {
        List<Plot> typeValid = new ArrayList<>();
        for (Plot plot : map.getMapPlots()) {
            if (plot.isIrrigated() && plot.getNumberOfBamboo() < 4 && plot.getType() == bambooType) {
                typeValid.add(plot);
            }
        }
        return typeValid;
    }
}
