package fr.cotedazur.univ.polytech.startingpoint;

import java.util.ArrayList;
import java.util.List;

public class Map {
    List<Plot> mapPlots;

    public Map(){
        mapPlots = new ArrayList<>();
        Plot pond = new Plot(PlotType.POND, new Position(0,0));
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

    public boolean verifyIrrigation(Plot p) {
        if(this.getNeighbours(p.getPosition()).contains(new Plot(PlotType.POND, new Position(0,0)))){
            p.isIrrigatedIsTrue();
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
        if((getNeighbours( position).size() >1 ||  position.isCloseToCenter()) && (position.isCenter() ==false)) return isSpaceFree(position);
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

    public List<Plot> checkIfPossibleToPlacePattern(Pattern pattern, Position position) {
        List<List<Plot>> potentialPatternSpot = new ArrayList<>();
        Pattern tempPattern = new Pattern(pattern);
        for(Plot plot : pattern.getPlots()){
            tempPattern.setAncerPoint(plot.getPosition());
            for(int i=0 ; i<5 ; i++){
                List<Plot> missingPLots = computePatternVerification(tempPattern, position);
                if(missingPLots != null)
                {
                    potentialPatternSpot.add(missingPLots);
                }
                tempPattern.rotate60Right();
            }
            tempPattern.rotate60Right();
        }
        if(potentialPatternSpot.isEmpty())return null;

        List<Plot> bestSpot = potentialPatternSpot.get(0);
        for(List<Plot> patternSpot : potentialPatternSpot)
        {
            if(patternSpot.size() < bestSpot.size())
            {
                bestSpot = patternSpot;
            }
        }
        return bestSpot;
    }

    public List<Plot> computePatternVerification(Pattern pattern, Position currentPosition){
        Pattern tempPattern = new Pattern(pattern);
        tempPattern.applyMask(currentPosition);
        ArrayList<Plot> incompletePlot = new ArrayList<>();
        for(Plot plot : tempPattern.getPlots()){
            if (isSpaceFree(plot.getPosition())){
                incompletePlot.add(plot);
            }
            else if (plot.getType() != findPlot(plot.getPosition()).getType()){
                return null;
            }
        }
        return incompletePlot;
    }
}


