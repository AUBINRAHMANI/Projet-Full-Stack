package fr.cotedazur.univ.polytech.startingpoint.bot.tools;

import fr.cotedazur.univ.polytech.startingpoint.Map;
import fr.cotedazur.univ.polytech.startingpoint.Plot;
import fr.cotedazur.univ.polytech.startingpoint.PlotType;
import fr.cotedazur.univ.polytech.startingpoint.WeatherType;
import fr.cotedazur.univ.polytech.startingpoint.action.Action;
import fr.cotedazur.univ.polytech.startingpoint.action.ActionType;
import fr.cotedazur.univ.polytech.startingpoint.action.MoveGardenerAction;
import fr.cotedazur.univ.polytech.startingpoint.action.RainAction;
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

        if (plotWithMaxBamboo != null) {
            return new MoveGardenerAction(plotWithMaxBamboo.getPosition());
        } else if (!typeValid.isEmpty()) {
            Plot nearestPlot = getNearestValidPlot(typeValid);
            if (nearestPlot != null) {
                return new MoveGardenerAction(nearestPlot.getPosition());
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

    private Plot getNearestValidPlot(List<Plot> typeValid) {
        for (Plot plot : typeValid) {
            if(!map.getNeighbours(plot.getPosition()).isEmpty()) {
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

    /* public Action fillObjectiveGardener(PlotType bambooType, List<ActionType> banActionTypes, WeatherType weather)
    {
        List<Plot> typeValid = new ArrayList<>();
        List<Plot> typeAndMovementValid = new ArrayList<>();
        int maxNbBamboo = 0;
        int indexMaxNbBamboo = 0;

        for (Plot plot : map.getMapPlots()) {
            if (plot.isIrrigated() && plot.getNumberOfBamboo() < 4 && plot.getType() == bambooType) {
                typeValid.add(plot);
                if (weather == WeatherType.RAIN) {
                    for (int i = 3; i > 0; i--) {
                        if (plot.getNumberOfBambou() == i) {
                            return new RainAction(plot.getPosition());
                        }
                    }
                }
                if (plot.getPosition().isDeplacementALine(referee.getGardenerPosition())) {
                    typeAndMovementValid.add(plot);
                }
            }
        }
        if (!typeAndMovementValid.isEmpty()) {
            for (int i = 0; i < typeAndMovementValid.size(); i++) {
                int numberOfBamboo = typeAndMovementValid.get(i).getNumberOfBambou();
                if (numberOfBamboo > maxNbBamboo) {
                    maxNbBamboo = numberOfBamboo;
                    indexMaxNbBamboo = i;
                }
            }
            return new MoveGardenerAction(typeAndMovementValid.get(indexMaxNbBamboo).getPosition());
        } else if (!typeValid.isEmpty()) {
            for (Plot plot : typeValid) {
                List<Plot> neighboursPlots = map.getNeighbours(plot.getPosition());
                for (Plot neighboursPlot : neighboursPlots) {
                    if (neighboursPlot.getPosition().isDeplacementALine(referee.getGardenerPosition())) {
                        return new MoveGardenerAction(neighboursPlot.getPosition());
                    }
                    for (Plot neighboursOfNeighboursPlot : map.getNeighbours(neighboursPlot.getPosition())) {
                        if (neighboursOfNeighboursPlot.getPosition().isDeplacementALine(referee.getGardenerPosition())) {
                            return new MoveGardenerAction(neighboursOfNeighboursPlot.getPosition());
                        }
                    }
                }
            }
        }

        return new PatternBotResolver(map, referee).putRandomlyAPLot(bambouType, banActionTypes);
    }*/

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
