package fr.cotedazur.univ.polytech.startingpoint;

import fr.cotedazur.univ.polytech.startingpoint.game.Game;
import fr.cotedazur.univ.polytech.startingpoint.bot.mbappe.Bot;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
class PandaTest {

    @Test
    void getPosition() {

            Map map = new Map();
            Game game = new Game();
            Bot bot = new Bot(game,map,null);
            Panda panda = new Panda();
            Position position = new Position(1,1);
            Position position2 = new Position(2,2);

            GameEngine gameEngine = new GameEngine(null,null,map);
            Plot plot = new Plot(PlotType.GREEN, position);


            plot.setPosition(position);
            panda.setPosition(position); //on peut aussi faire un gameEngine.movePanda(position);

            assertEquals(position, panda.getPosition());


            gameEngine.movePanda(game,bot,position2);
            assertFalse(panda.getPosition().equals(position2));
    }

}