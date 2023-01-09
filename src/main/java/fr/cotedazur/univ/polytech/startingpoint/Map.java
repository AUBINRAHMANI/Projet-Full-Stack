package fr.cotedazur.univ.polytech.startingpoint;

import fr.cotedazur.univ.polytech.startingpoint.debugInterface.MapInterface;

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

    public void rotatePattern(ArrayList<Plot> pattern){
        for(Plot plot : pattern){
            Position plotPosition = plot.getPosition();
            plotPosition.rotate60Right();
            plot.setPosition(plotPosition);
        }
    }
}


