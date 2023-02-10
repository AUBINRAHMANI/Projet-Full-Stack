package fr.cotedazur.univ.polytech.startingpoint.game.game_engine.map;

import fr.cotedazur.univ.polytech.startingpoint.game.game_engine.items.Irrigation;
import fr.cotedazur.univ.polytech.startingpoint.logger.Loggeable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class Map implements Loggeable {
    List<Plot> mapPlots;
    List<Irrigation> irrigations;

    public Map() {
        mapPlots = new ArrayList<>();
        irrigations = new ArrayList<>();
        Plot pond = new Plot(PlotType.POND, new Position(0, 0));
        for (Position position : pond.getPosition().closestPositions()) {
            irrigations.add(new Irrigation(new Position(0, 0), position));
        }
        pond.isIrrigatedIsTrue();
        mapPlots.add(pond);
    }

    public boolean putPlot(Plot plot) {
        if (isPossibleToPutPlot(plot.getPosition())) {
            mapPlots.add(plot);
            verifyIrrigation(plot);
            return true;
        }
        return false;
    }

    public List<Plot> getMapPlots() {
        return new ArrayList<>(mapPlots);
    }

    public boolean verifyIrrigation(Plot plot) {
        for (Irrigation irrigation : irrigations) {
            if (irrigation.getPositions().contains(plot.getPosition())) {
                plot.isIrrigatedIsTrue();
                return true;
            }
        }
        return false;
    }

    public Plot findPlot(Position position) {
        Plot plot1 = null;
        for (Plot plot : mapPlots) {
            if (plot.getPosition().equals(position)) {
                plot1 = plot;
            }
        }
        return plot1;
    }

    public boolean isSpaceFree(Position position) {
        for (Plot plot : mapPlots) {
            if (plot.getPosition().equals(position)) {
                return false;
            }
        }
        return true;
    }

    public boolean isPossibleToPutPlot(Position position) {
        if ((getNeighbours(position).size() > 1 || position.isCloseToCenter()) && (position.isCenter()))
            return isSpaceFree(position);
        return false;
    }


    public List<Position> closestAvailableSpace(Position position) {
        ArrayList<Position> positionsAvailable = new ArrayList<>();
        for (Position potentialPosition : position.closestPositions()) {
            if (isPossibleToPutPlot(potentialPosition)) {
                positionsAvailable.add(potentialPosition);
            }
        }
        return positionsAvailable;
    }

    public List<Plot> getNeighbours(Position position) {
        ArrayList<Plot> plots = new ArrayList<>();
        for (Plot mapPlot : this.mapPlots) {
            for (Position tempPosition : position.closestPositions()) {
                if (mapPlot.getPosition().equals(tempPosition)) {
                    plots.add(mapPlot);
                }
            }
        }
        return plots;
    }

    public List<Position> getPathBetweenPositions(Position start, Position target) {
        List<Position> path = new ArrayList<>();
        path.add(target);
        while (!path.get(path.size() - 1).equals(start)) {
            Position bestNextPosition = null;
            int minDistance = -1;
            for (Position position : path.get(path.size() - 1).closestPositions()) {
                if (!path.contains(position) && findPlot(position) != null && (minDistance == -1 || position.getDistanceToPosition(new Position(0, 0)) < minDistance)) {
                    bestNextPosition = position;
                    minDistance = bestNextPosition.getDistanceToPosition(new Position(0, 0));
                }
            }
            if (bestNextPosition == null) return List.of();
            path.add(bestNextPosition);
        }
        return path;
    }

    public Optional<List<List<Plot>>> checkIfPossibleToPlacePattern(Pattern pattern, Position position) {
        List<List<Plot>> potentialPatternSpots = new ArrayList<>();
        List<List<Plot>> potentialNonIrrigatedPlots = new ArrayList<>();

        Pattern tempPattern = new Pattern(pattern);
        for (Plot plot : pattern.getPlots()) {
            tempPattern.setAncerPoint(plot.getPosition());
            for (int i = 0; i < 5; i++) {
                Optional<List<List<Plot>>> result = computePatternVerification(tempPattern, position);
                if (result.isPresent()) {
                    List<Plot> missingPlots = result.get().get(0);
                    List<Plot> nonIrrigatedPlots = result.get().get(1);
                    if (missingPlots.isEmpty() && nonIrrigatedPlots.isEmpty()) {
                        return Optional.of(Arrays.asList(missingPlots, nonIrrigatedPlots));
                    } else {
                        potentialPatternSpots.add(missingPlots);
                        potentialNonIrrigatedPlots.add(nonIrrigatedPlots);
                    }
                }
                tempPattern.rotate60Right();
            }
            tempPattern.rotate60Right();
        }
        if (potentialPatternSpots.isEmpty()) return Optional.empty();

        List<Plot> bestPatternSpot = potentialPatternSpots.get(0);
        List<Plot> bestNonIrrigatedSpots = potentialNonIrrigatedPlots.get(0);
        for (int i = 0; i < potentialPatternSpots.size(); ++i) {
            List<Plot> potentialPatternSpot = potentialPatternSpots.get(i);
            List<Plot> potentialNonIrrigatedPlot = potentialNonIrrigatedPlots.get(i);
            if (potentialPatternSpot.size() < bestPatternSpot.size()) {
                bestPatternSpot = potentialPatternSpot;
                bestNonIrrigatedSpots = potentialNonIrrigatedPlot;

            }
        }
        return Optional.of(Arrays.asList(bestPatternSpot, bestNonIrrigatedSpots));
    }

    public Optional<List<List<Plot>>> computePatternVerification(Pattern pattern, Position currentPosition) {
        Pattern tempPattern = new Pattern(pattern);
        tempPattern.applyMask(currentPosition);
        ArrayList<Plot> missingPlots = new ArrayList<>();
        ArrayList<Plot> nonIrrigatedPlot = new ArrayList<>();
        for (Plot plot : tempPattern.getPlots()) {
            if (isSpaceFree(plot.getPosition())) {
                missingPlots.add(new Plot(plot.getType(), plot.getPosition()));
                nonIrrigatedPlot.add(new Plot(plot.getType(), plot.getPosition()));
            } else if (plot.getType() == findPlot(plot.getPosition()).getType() && !findPlot(plot.getPosition()).isIrrigated()) {
                nonIrrigatedPlot.add(new Plot(plot.getType(), plot.getPosition()));
            } else if (plot.getType() != findPlot(plot.getPosition()).getType()) {
                return Optional.empty();
            }
        }
        return Optional.of(Arrays.asList(missingPlots, nonIrrigatedPlot));
    }

    public boolean putIrrigation(Irrigation irrigation) {
        if (isIrrigationLinked(irrigation)) {
            List<Position> positions = irrigation.getPositions();
            for (Plot plot : mapPlots) {
                if (positions.contains(plot.getPosition())) {
                    this.irrigations.add(irrigation);
                    for (Position position : irrigation.getPositions()) {
                        Plot plot2 = this.findPlot(position);
                        if (plot2 != null) {
                            plot2.isIrrigatedIsTrue();
                        }
                    }
                    return true;
                }
            }
        }
        return false;
    }

    public boolean isIrrigationLinked(Irrigation irrigation) {
        for (Irrigation irrigation2 : irrigation.getNeighbours()) {
            if (this.irrigations.contains(irrigation2)) {
                return true;
            }
        }
        return false;
    }

    public boolean irrigationExist(Irrigation irrigation) {
        return this.irrigations.contains(irrigation);
    }
}


