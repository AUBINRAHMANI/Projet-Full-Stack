package fr.cotedazur.univ.polytech.startingpoint.game.game_engine.actors;

import fr.cotedazur.univ.polytech.startingpoint.game.game_engine.actors.Panda;
import fr.cotedazur.univ.polytech.startingpoint.game.game_engine.map.Position;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PandaTest {

    @Test
    void getPosition() {
        Panda panda = new Panda();
        assertEquals(new Position(0, 0), panda.getPosition());
    }

    @Test
    void setPosition() {
        Panda panda = new Panda();
        panda.setPosition(new Position(2, 1));
        assertEquals(new Position(2, 1), panda.getPosition());
    }

}