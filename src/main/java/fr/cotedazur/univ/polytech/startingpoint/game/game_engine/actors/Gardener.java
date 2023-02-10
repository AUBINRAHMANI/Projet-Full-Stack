package fr.cotedazur.univ.polytech.startingpoint.game.game_engine.actors;

import fr.cotedazur.univ.polytech.startingpoint.game.game_engine.map.Position;

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
