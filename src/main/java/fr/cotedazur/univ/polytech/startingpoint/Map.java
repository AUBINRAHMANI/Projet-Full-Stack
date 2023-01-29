package fr.cotedazur.univ.polytech.startingpoint;

import fr.cotedazur.univ.polytech.startingpoint.debugInterface.MapInterface;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class Map {
    public ArrayList<Plot> map_;
    private MapInterface _mapInterface;

    public Map(){
        map_ = new ArrayList<>();
        Plot Pond = new Plot(PlotType.POND, new Position(0,0));
        Pond.isIrrigatedIsTrue();
        map_.add(Pond);
    }

    public boolean putPlot(Plot plot) {
        if (isPossibleToPutPlot(plot.getPosition()) == true) {
            map_.add(plot);
            verifyIrrigation(plot);
            return true;
        }
        return false;
    }

    public ArrayList<Plot> getMap() {
        return new ArrayList<>(map_);
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
        for(Plot plot : map_){
            if(plot.getPosition().equals(position)){
                plot1= plot;
            }
        }
        return plot1;
    }
    boolean isSpaceFree(Position position) {
        for (Plot plot : map_) {
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


    public ArrayList<Position> closestAvailableSpace(Position position) {
        ArrayList<Position> positionsAvailable = new ArrayList<>();
        for (Position potentialPosition : position.closestPositions()) {
            if(isPossibleToPutPlot(potentialPosition))
            {
                positionsAvailable.add(potentialPosition);
            }
        }
        return positionsAvailable;
    }

    public ArrayList<Plot> getNeighbours(Position position) {
        ArrayList<Plot> plots = new ArrayList<>();
        for(Plot mapPlot : map_){
            for (Position tempPosition : position.closestPositions()) {
                if (mapPlot.getPosition().equals(tempPosition)){
                    plots.add(mapPlot);
                }
            }
        }
        return plots;
    }

    public ArrayList<Plot> checkIfPossibleToPlacePattern(Pattern pattern, Position position) {
        ArrayList<ArrayList<Plot>> potentialPatternSpot = new ArrayList<>();
        Pattern tempPattern = new Pattern(pattern);
        for(Plot plot : pattern.getPlots()){
            tempPattern.setAncerPoint(plot.getPosition());
            for(int i=0 ; i<5 ; i++){
                ArrayList<Plot> missingPLots = computePatternVerification(tempPattern, position);
                if(missingPLots != null)
                {
                    potentialPatternSpot.add(missingPLots);
                }
                tempPattern.rotate60Right();
            }
            tempPattern.rotate60Right();
        }
        if(potentialPatternSpot.isEmpty())return null;

        ArrayList<Plot> bestSpot = potentialPatternSpot.get(0);
        for(ArrayList<Plot> patternSpot : potentialPatternSpot)
        {
            if(patternSpot.size() < bestSpot.size())
            {
                bestSpot = patternSpot;
            }
        }
        return bestSpot;
    }

    public ArrayList<Plot> computePatternVerification(Pattern pattern, Position currentPosition){
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


