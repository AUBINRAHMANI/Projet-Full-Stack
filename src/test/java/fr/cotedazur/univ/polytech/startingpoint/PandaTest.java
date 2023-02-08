package fr.cotedazur.univ.polytech.startingpoint;

import fr.cotedazur.univ.polytech.startingpoint.game.Game;
import fr.cotedazur.univ.polytech.startingpoint.bot.BotMbappe;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
class PandaTest {

    @Test
    void getPosition() {
            Panda panda = new Panda();
            assertEquals(new Position(0,0), panda.getPosition());
    }

    @Test
    void setPosition() {
           Panda panda = new Panda();
           panda.setPosition(new Position(2,1));
           assertEquals(new Position(2,1) , panda.getPosition());
    }

}