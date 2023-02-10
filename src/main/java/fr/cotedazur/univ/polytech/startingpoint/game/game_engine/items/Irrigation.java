package fr.cotedazur.univ.polytech.startingpoint.game.game_engine.items;

import fr.cotedazur.univ.polytech.startingpoint.game.game_engine.map.Position;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;

public class Irrigation {

    List<Position> positions;

    public Irrigation(Position position1, Position position2) {
        this.positions = new ArrayList<>();
        setPosition(position1, position2);
    }

    public boolean setPosition(Position position1, Position position2) {
        if (position1 != null && position2 != null && position1.isCloseTo(position2)) {
            positions.clear();
            positions.add(position1);
            positions.add(position2);
            return true;
        }
        return false;

    }

    public List<Position> getPositions() {
        return positions;
    }

    public List<Irrigation> getNeighbours() {
        List<Irrigation> neighbours = new ArrayList<>();
        List<Position> neighbours1 = positions.get(0).closestPositions();
        neighbours1.retainAll(positions.get(1).closestPositions());
        for (Position position1 : neighbours1) {
            for (Position position2 : positions) {
                neighbours.add(new Irrigation(position1, position2));
            }
        }
        return neighbours;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Irrigation that = (Irrigation) o;
        return new HashSet<>(positions).containsAll(that.positions) && positions.size() == 2;
    }

    @Override
    public int hashCode() {
        return Objects.hash(positions);
    }

    @Override
    public String toString() {
        return "Irrigation{" +
                "positions=" + positions +
                '}';
    }
}
