package fr.cotedazur.univ.polytech.startingpoint;

import java.util.ArrayList;
import java.util.List;

public class Map {
    public ArrayList<Plot> map_;

    public Map() {
        map_ = new ArrayList<>();
        map_.add(new Plot(PlotType.POND, new Position(0,0)));
    }

    public boolean putPlot(Plot parcel) {
        if (isSpaceFree(parcel) == true) {
            map_.add(parcel);
            return true;
        }
        return false;
    }

    public ArrayList<Plot> getMap() {
        return map_;
    }

    public boolean isIrrigated(Plot p) {
        return false;
    }

    public boolean isSpaceFree(Plot parcel) {
        for (int i = 0; i < map_.size(); i++) {
            if (parcel.getPosition() == map_.get(i).getPosition()) {
                return false;
            }
        }
        return true;
    }


    public ArrayList<Position> closestAvailableSpace(Plot plot) {
        ArrayList<Position> positionsAvailable = new ArrayList<>();
        for (int i = 0; i < plot.getPosition().closestPositions().size(); i++) {
            for (int j = 0; j < map_.size(); j++) {
                if (map_.get(j).getPosition() != plot.getPosition().closestPositions().get(i)) {
                    positionsAvailable.add(plot.getPosition().closestPositions().get(i));
                }
            }
        }
        return positionsAvailable;
    }

    public boolean haveNeighbours(Position position) {//on parcourt la map puis on parcourt la liste des plus proche voisin du plot et si la position d'un voisin est egale a la position d'un plot alors on return true
        for (int i = 0; i < map_.size(); i++) {
            for (int j = 0; j < position.closestPositions().size(); j++) {
                if (map_.get(i).getPosition().equals(position.closestPositions().get(j))){
                    return true;
                }
            }
        }
        return false;
    }
}


