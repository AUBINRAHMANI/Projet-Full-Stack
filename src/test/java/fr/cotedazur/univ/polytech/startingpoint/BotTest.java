package fr.cotedazur.univ.polytech.startingpoint;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BotTest {

    @Test
    void play() {
        Position position = new Position(0,0);
        Plot plot = new Plot(PlotType.GREEN, position);
        Map map = new Map();
        map.putPlot(plot);

        GameEngine gameEngine  = new GameEngine();
        Bot bot = new Bot();

        bot.play(gameEngine, map);

        assertEquals(position, plot.getPosition());


    }
}