package fr.cotedazur.univ.polytech.startingpoint;

import fr.cotedazur.univ.polytech.startingpoint.debugInterface.MapInterface;

import java.util.ArrayList;
import java.util.List;

public class Map {
    public ArrayList<Plot> map_;
    private MapInterface _mapInterface;

    public Map() {
        this( null);
    }
    public Map(MapInterface mapInterface){
        _mapInterface = mapInterface;
        map_ = new ArrayList<>();
        putPlot(new Plot(PlotType.POND, new Position(0,0,0)));
    }

    public boolean putPlot(Plot plot) {
        if (isSpaceFree(plot.getPosition()) == true) {
            map_.add(plot);
            if(_mapInterface != null){
                _mapInterface.drawHexagon(plot.getPosition());
            }
            return true;
        }
        return false;
    }

    public ArrayList<Plot> getMap() {
        return new ArrayList<>(map_);
    }

    public boolean isIrrigated(Plot p) {
        return false;
    }

    private boolean isSpaceFree(Position position) {
        for (Plot plot : map_) {
            if(plot.getPosition().equals(position))
            {
                return false;
            }
        }
        return true;
    }

    public boolean isPossibleToPutPlot(Position position) {
        if(closestAvailableSpace(position).size() >4) return false;
        return isSpaceFree(position);
    }


    public ArrayList<Position> closestAvailableSpace(Position position) {
        ArrayList<Position> positionsAvailable = new ArrayList<>();
        for (Position potentialPosition : position.closestPositions()) {
            if(isSpaceFree(potentialPosition))
            {
                positionsAvailable.add(potentialPosition);
            }
        }
        return positionsAvailable;
    }

    public ArrayList<Plot> getNeighbours(Plot plot) {
        ArrayList<Plot> plots = new ArrayList<>();
        for(Plot mapPlot : map_){
            for (Position position : plot.getPosition().closestPositions()) {
                if (mapPlot.getPosition().equals(position)){
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


