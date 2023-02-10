package fr.cotedazur.univ.polytech.startingpoint.game.game_engine.actors;

import fr.cotedazur.univ.polytech.startingpoint.game.game_engine.map.Position;

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
