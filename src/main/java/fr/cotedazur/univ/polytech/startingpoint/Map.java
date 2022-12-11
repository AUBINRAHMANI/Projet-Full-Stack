package fr.cotedazur.univ.polytech.startingpoint;

import java.util.ArrayList;
import java.util.List;

public class Map {
    public Plot pond;//on initialise l'etang
    public ArrayList<Plot> map;

    public Map() {
        this.map = new ArrayList<Plot>();
    }

    public ArrayList<Plot> initializeMap(ArrayList<Plot> map) {
        this.putPlot(pond);
        return map;
    }

    public boolean putPlot(Plot parcel) {
        if (isSpaceFree(parcel) == true) {
            map.add(parcel);
            return true;
        }
        return false;
    }

    public ArrayList<Plot> getMap() {
        return map;
    }

    public boolean isIrrigated(Plot p) {
        return false;
    }

    public boolean isSpaceFree(Plot parcel) {
        for (int i = 0; i < map.size(); i++) {
            if (parcel == map.get(i)) {
                return true;
            }
        }
        return false;
    }

    public ArrayList<Position> closestAvailableSpace(Plot plot) {
        ArrayList<Position> positionsAvailable = new ArrayList<>();
        for (int i = 0; i < plot.getPosition().closestPositions().size(); i++) {
            for (int j = 0; j < map.size(); j++) {
                if (map.get(j).getPosition() != plot.getPosition().closestPositions().get(i)) {
                    positionsAvailable.add(plot.getPosition().closestPositions().get(i));
                }
            }
        }
        return positionsAvailable;
    }

    public boolean haveNeighbours(Position position) {//on parcourt la map puis on parcourt la liste des plus proche voisin du plot et si la position d'un voisin est egale a la position d'un plot alors on return true
        for (int i = 0; i < map.size(); i++) {
            for (int j = 0; j < position.closestPositions().size(); j++) {
                if (map.get(i).getPosition().equals(position.closestPositions().get(j))){
                    return true;
                }
            }
        }
        return false;
    }
}
