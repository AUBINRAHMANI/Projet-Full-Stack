package fr.cotedazur.univ.polytech.startingpoint;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PandaTest {

    @Test
    void getPosition() {

            Panda panda = new Panda();
            Position position = new Position(1,1);
            Position position2 = new Position(2,2);
            Map map = new Map();
            GameEngine gameEngine = new GameEngine(null,null,map);
            Plot plot = new Plot(PlotType.GREEN, position);


            plot.setPosition(position);
            panda.setPosition(position); //on peut aussi faire un gameEngine.movePanda(position);

            assertEquals(position, panda.getPosition());


            gameEngine.movePanda(position2);
            assertFalse(panda.getPosition().equals(position2));
    }

}