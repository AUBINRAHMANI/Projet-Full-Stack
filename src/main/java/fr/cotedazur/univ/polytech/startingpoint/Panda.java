package fr.cotedazur.univ.polytech.startingpoint;

public class Panda {

    private Position position;

    public Panda() {

        position = new Position(0, 0);
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }
}
