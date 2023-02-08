package fr.cotedazur.univ.polytech.startingpoint;

import java.util.*;

import static java.lang.Math.*;

public class Map {
    List<Plot> mapPlots;
    List<Irrigation> irrigations;

    public Map(){
        mapPlots = new ArrayList<>();
        irrigations = new ArrayList<>();
        Plot pond = new Plot(PlotType.POND, new Position(0,0));
        for(Position position : pond.getPosition().closestPositions()){
            irrigations.add(new Irrigation( new Position(0,0), position));
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
        for(Irrigation irrigation : irrigations){
            if(irrigation.getPositions().contains(plot.getPosition())){
                plot.isIrrigatedIsTrue();
                return true;
            }
        }
        if(plot.getPosition().isCloseToCenter()){
            plot.isIrrigatedIsTrue();
            return true;
        }
        return false;
    }

    public Plot findPlot(Position position) {
        Plot plot1 = null;
        for(Plot plot : mapPlots){
            if(plot.getPosition().equals(position)){
                plot1= plot;
            }
        }
        return plot1;
    }
    boolean isSpaceFree(Position position) {
        for (Plot plot : mapPlots) {
            if(plot.getPosition().equals(position))
            {
                return false;
            }
        }
        return true;
    }

    public boolean isPossibleToPutPlot(Position position) {
        if((getNeighbours( position).size() >1 ||  position.isCloseToCenter()) && (!position.isCenter())) return isSpaceFree(position);
        return false;
    }


    public List<Position> closestAvailableSpace(Position position) {
        ArrayList<Position> positionsAvailable = new ArrayList<>();
        for (Position potentialPosition : position.closestPositions()) {
            if(isPossibleToPutPlot(potentialPosition))
            {
                positionsAvailable.add(potentialPosition);
            }
        }
        return positionsAvailable;
    }

    public List<Plot> getNeighbours(Position position) {
        ArrayList<Plot> plots = new ArrayList<>();
        for(Plot mapPlot : this.mapPlots){
            for (Position tempPosition : position.closestPositions()) {
                if (mapPlot.getPosition().equals(tempPosition)){
                    plots.add(mapPlot);
                }
            }
        }
        return plots;
    }

    public int getDistanceBetweenPositions(Position position1, Position position2){
        int max = max(abs(position1.getQ()- position2.getQ()), abs(position1.getR() - position2.getR()));
        return max( abs(position1.getS() - position2.getS()), max);
    }

    public List<Position> getPathBetweenPositions(Position start, Position target){
        List<Position> path = new ArrayList<>();
        path.add(target);

        while (path.get( path.size()-1 ).equals(start)) {
            Position bestNextPosition = null;
            int minDistance = -1;
            for (Position position : path.get(path.size() - 1).closestPositions()) {
                if ( findPlot(position) != null && (minDistance == -1 || getDistanceBetweenPositions(position, new Position(0, 0)) < minDistance)) {
                    bestNextPosition = position;
                    minDistance = getDistanceBetweenPositions(bestNextPosition, new Position(0, 0));
                }
            }
            path.add(bestNextPosition);
        }
        return path;
    }

    public List<List<Plot>> checkIfPossibleToPlacePattern(Pattern pattern, Position position) {
        List<List<Plot>> potentialPatternSpots = new ArrayList<>();
        List<List<Plot>> potentialNonIrrigatedPlots = new ArrayList<>();
        Pattern tempPattern = new Pattern(pattern);
        for(Plot plot : pattern.getPlots()){
            tempPattern.setAncerPoint(plot.getPosition());
            for(int i=0 ; i<5 ; i++){
                List<List<Plot>> result = computePatternVerification(tempPattern, position);
                List<Plot> missingPlots = result.get(0);
                List<Plot> nonIrrigatedPlots = result.get(1);
                if(missingPlots.isEmpty() && nonIrrigatedPlots.isEmpty()) {
                    return Arrays.asList(missingPlots, nonIrrigatedPlots);
                }
                else {
                    potentialPatternSpots.add(missingPlots);
                    potentialNonIrrigatedPlots.add(nonIrrigatedPlots);
                }
                tempPattern.rotate60Right();
            }
            tempPattern.rotate60Right();
        }
        if(potentialPatternSpots.isEmpty())return Arrays.asList();

        List<Plot> bestPatternSpot = potentialPatternSpots.get(0);
        List<Plot> bestNonIrrigatedSpots = potentialNonIrrigatedPlots.get(0);
        for(int i=0; i<potentialPatternSpots.size() ;++i)
        {
            List<Plot> potentialPatternSpot = potentialPatternSpots.get(i);
            List<Plot> potentialNonIrrigatedPlot = potentialNonIrrigatedPlots.get(i);
            if(potentialPatternSpot.size() < bestPatternSpot.size())
            {
                bestPatternSpot = potentialPatternSpot;
                bestNonIrrigatedSpots = potentialNonIrrigatedPlot;

            }
        }
        return Arrays.asList(bestPatternSpot, bestNonIrrigatedSpots);
    }

    public List<List<Plot>> computePatternVerification(Pattern pattern, Position currentPosition){
        Pattern tempPattern = new Pattern(pattern);
        tempPattern.applyMask(currentPosition);
        ArrayList<Plot> missingPlots = new ArrayList<>();
        ArrayList<Plot> nonIrrigatedPlot = new ArrayList<>();
        for(Plot plot : tempPattern.getPlots()) {
            if (isSpaceFree(plot.getPosition())) {
                missingPlots.add(new Plot(plot.getType(), plot.getPosition()));
            } else if (plot.getType() == findPlot(plot.getPosition()).getType() && !findPlot(plot.getPosition()).isIrrigated()) {
                nonIrrigatedPlot.add(new Plot(plot.getType(), plot.getPosition()));
            }
        }
        return Arrays.asList(missingPlots, nonIrrigatedPlot);
    }

    public boolean putIrrigation(Irrigation irrigation) {
        if(isIrrigationsLinked(irrigation)){
            List<Position> positions = irrigation.getPositions();
            for(Plot plot : mapPlots){
                if(positions.contains(plot.getPosition())){
                    irrigations.add(irrigation);
                    for(Position position : irrigation.getPositions()){
                        Plot plot2 = this.findPlot(position);
                        if(plot2 != null){
                            plot2.isIrrigatedIsTrue();
                        }
                    }
                    return true;
                }
            }
        }
        return false;
    }

    public boolean isIrrigationsLinked(Irrigation irrigation) {
        for (Irrigation irrigation2 : irrigation.getNeighbours()) {
            if (irrigations.contains(irrigation2)) {
                return true;
            }
        }
        return irrigation.getPositions().get(0).isCloseToCenter() && irrigation.getPositions().get(1).isCloseToCenter();
    }

    public boolean irrigationExist(Irrigation irrigation) {
        return irrigations.contains(irrigation);
    }
}


