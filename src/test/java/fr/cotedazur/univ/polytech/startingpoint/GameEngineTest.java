package fr.cotedazur.univ.polytech.startingpoint;

import fr.cotedazur.univ.polytech.startingpoint.bot.BotMbappe;
import fr.cotedazur.univ.polytech.startingpoint.bot.BotProfil;
import fr.cotedazur.univ.polytech.startingpoint.game.Game;
import fr.cotedazur.univ.polytech.startingpoint.objective.Objective;
import fr.cotedazur.univ.polytech.startingpoint.objective.ObjectivePlots;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class GameEngineTest {

    @Mock
    Game mockedGame = mock(Game.class);

    @Test
    void pickObjectiveTest() {
        Deck<Objective> deck = new Deck<>(mockedGame);
        Objective objective = new ObjectivePlots(5, null);
        deck.addCard(objective);
        GameEngine gameEngine = new GameEngine(deck, null, null);

        assertEquals(objective, gameEngine.pickObjective());
    }

    @Test
    void pickPlotTest() {
        Deck<Plot> deck = new Deck<>(mockedGame);
        deck.addCard(new Plot(PlotType.GREEN, null));
        deck.addCard(new Plot(PlotType.YELLOW, null));
        deck.addCard(new Plot(PlotType.RED, null));
        GameEngine gameEngine = new GameEngine(null, deck, null);

        assertEquals(PlotType.GREEN, gameEngine.pickPlot().get(0).getType());
    }

    @Test
    void askGetMapTest() {
        Map map = new Map();
        GameEngine gameEngine = new GameEngine(null, null, map);
        assertEquals(map, gameEngine.getMap());
    }

    @Test
    void movePandaTest() {
        Map map = new Map();
        GameEngine gameEngine = new GameEngine(null, null, map);
        Position position = new Position(1, 0);
        Position position2 = new Position(372, 1);

        Plot plot = new Plot(PlotType.GREEN, position);
        map.putPlot(plot);


        gameEngine.movePanda(null, null, position);
        assertTrue(gameEngine.movePanda(null, null, position));
        assertFalse(gameEngine.movePanda(null, null, position2));
    }

    @Test
    void computeObjectivePlotTest() {
        Map map = new Map();
        GameEngine gameEngine = new GameEngine(null, null, map);

        ArrayList<Plot> plots = new ArrayList<>();
        plots.add(new Plot(PlotType.GREEN, new Position(-1, 1)));
        plots.add(new Plot(PlotType.GREEN, new Position(0, 0)));
        plots.add(new Plot(PlotType.GREEN, new Position(0, -1)));
        plots.add(new Plot(PlotType.GREEN, new Position(1, 0)));
        for (Plot plot : plots) {
            plot.isIrrigatedIsTrue();
        }
        Pattern pattern = new Pattern(plots);

        Plot plot6 = new Plot(PlotType.GREEN, new Position(1, 0));
        Plot plot7 = new Plot(PlotType.GREEN, new Position(0, 1));
        Plot plot8 = new Plot(PlotType.GREEN, new Position(1, 1));
        Plot plot9 = new Plot(PlotType.GREEN, new Position(2, 0));


        gameEngine.askToPutPlot(plot6);
        gameEngine.askToPutPlot(plot7);
        gameEngine.askToPutPlot(plot8);

        assertFalse(gameEngine.computeObjectivePlot(new Pattern(pattern), plot7));
        gameEngine.askToPutPlot(plot9);
        assertFalse(gameEngine.computeObjectivePlot(new Pattern(pattern), plot7));
        plot9.isIrrigatedIsTrue();
        assertTrue(gameEngine.computeObjectivePlot(pattern, plot7));
    }

    @Test
    void computeObjectivePanda() {
        GameEngine gameEngine = new GameEngine(null, null, null);

        BotProfil botProfil = new BotProfil(new BotMbappe(null, null), "");
        botProfil.addBanbou(new Bamboo(PlotType.GREEN));

        ArrayList<Bamboo> bamboosObjective = new ArrayList<>();
        bamboosObjective.add(new Bamboo(PlotType.GREEN));
        bamboosObjective.add(new Bamboo(PlotType.GREEN));

        assertFalse(gameEngine.computeObjectivePanda(botProfil, bamboosObjective));
        botProfil.addBanbou(new Bamboo(PlotType.GREEN));
        assertTrue(gameEngine.computeObjectivePanda(botProfil, bamboosObjective));
        assertTrue(botProfil.getBambous().isEmpty());

    }

    @Test
    void moveGardenerTest() {
        Map map = new Map();
        Plot plot2 = new Plot(PlotType.GREEN, new Position(0, 1));
        Plot plot3 = new Plot(PlotType.GREEN, new Position(1, 2));
        map.putPlot(plot2);
        map.putPlot(plot3);
        GameEngine gameEngine = new GameEngine(null, null, map);
        gameEngine.moveGardener(new Position(0, 1));
        assertEquals(new Position(0, 1), gameEngine.getGardenerPosition());

        gameEngine.moveGardener(new Position(3, 1));
        assertEquals(new Position(0, 1), gameEngine.getGardenerPosition());

        gameEngine.moveGardener(new Position(1, 2));
        assertEquals(new Position(0, 1), gameEngine.getGardenerPosition());
    }

    @Test
    void growBambooTest() {
        Map map = new Map();
        Plot plot = new Plot(PlotType.GREEN, new Position(0, 1));
        Plot plot2 = new Plot(PlotType.GREEN, new Position(1, 1));
        map.putPlot(plot);
        map.putPlot(plot2);
        GameEngine gameEngine = new GameEngine(null, null, map);
        gameEngine.moveGardener(new Position(0, 1));
        assertEquals(1, plot.getNumberOfBamboo());
        assertEquals(1, plot2.getNumberOfBamboo());
    }

    @Test
    void computeObjectiveGardener() {
        Map map = new Map();
        Plot plot1 = new Plot(PlotType.GREEN, new Position(0, 1));
        Plot plot2 = new Plot(PlotType.GREEN, new Position(1, 1));
        Plot plot3 = new Plot(PlotType.GREEN, new Position(1, 0));
        map.putPlot(plot1);
        map.putPlot(plot2);
        map.putPlot(plot3);

        for (int i = 0; i < 3; ++i) plot1.growBamboo();
        for (int i = 0; i < 3; ++i) {
            plot2.growBamboo();
            plot3.growBamboo();
        }

        GameEngine gameEngine = new GameEngine(null, null, map);
        gameEngine.moveGardener(new Position(0, 1));
        assertTrue(gameEngine.computeObjectiveGardener(4, PlotType.GREEN, 1));
        gameEngine.moveGardener(new Position(0, 0));
        assertFalse(gameEngine.computeObjectiveGardener(4, PlotType.GREEN, 1));
        gameEngine.moveGardener(plot2.getPosition());
        assertTrue(gameEngine.computeObjectiveGardener(3, PlotType.GREEN, 2));
    }

    @Test
    void eatBamboo() {
        Map map = new Map();
        Position position = new Position(1, 0);
        Plot plot = new Plot(PlotType.GREEN, position);

        plot.isIrrigatedIsTrue();

        map.putPlot(plot);
        Game game = new Game();
        GameEngine gameEngine = new GameEngine(null, null, map);

        gameEngine.moveGardener(position);
        gameEngine.moveGardener(position);

        gameEngine.eatBamboo(game, null, position);

        assertEquals(1, plot.getNumberOfBamboo());
    }

    @Test
    void testDrawWeather() {
        GameEngine gameEngine = new GameEngine(null, null, null);
        WeatherType weather = gameEngine.drawWeather();
        for (WeatherType weatherType : WeatherType.values()) {
            if (weatherType == weather) {
                assertTrue(true);
            }
        }
    }

    @Test
    void testRainAction() {
        Map map = new Map();
        Plot plot = new Plot(PlotType.GREEN, new Position(0, 1));
        Plot plot2 = new Plot(PlotType.GREEN, new Position(1, 1));
        map.putPlot(plot);
        map.putPlot(plot2);
        GameEngine gameEngine = new GameEngine(null, null, map);
        gameEngine.rainAction(new Position(0, 1));
        assertEquals(1, plot.getNumberOfBamboo());
        assertNotEquals(1, plot2.getNumberOfBamboo());

    }

    @Test
    void testThunderAction() {
        Map map = new Map();
        GameEngine gameEngine = new GameEngine(null, null, map);
        Position position = new Position(1, 0);
        Position position2 = new Position(372, 1);

        Plot plot = new Plot(PlotType.GREEN, position);
        map.putPlot(plot);


        gameEngine.thunderAction(position);
        assertTrue(gameEngine.thunderAction(position));
        assertFalse(gameEngine.thunderAction(position2));
    }
}
