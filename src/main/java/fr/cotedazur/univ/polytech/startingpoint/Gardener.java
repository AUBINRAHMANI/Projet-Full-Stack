package fr.cotedazur.univ.polytech.startingpoint;

public class Gardener {
    private Position position;

    public Gardener() {
        position = new Position(0, 0);
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }
}
