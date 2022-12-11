package fr.cotedazur.univ.polytech.startingpoint;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GameEngineTest {


    @Test
    void pickObjectiveTest() {
        Deck<Objective> deck = new Deck<>();
        deck.addCard(new Objective(5, ObjectiveType.PLOT));
        GameEngine gameEngine = new GameEngine(deck, null, null);

        assertEquals(4 , gameEngine.pickObjective().getPoint());
        assertEquals( ObjectiveType.PLOT , gameEngine.pickObjective().getType());
    }

    @Test
    void pickPlotTest() {
        Deck<Plot> deck = new Deck<>();
        deck.addCard(new Plot(PlotType.GREEN));
        GameEngine gameEngine = new GameEngine(null, deck, null);

        assertEquals(PlotType.GREEN , gameEngine.pickPlot().getType());
    }

    @Test
    void askToSetPlotTest() {
        GameEngine gameEngine = new GameEngine();
        Plot plot1 = new Plot(PlotType.GREEN ,new Position(1,0));
        Plot plot2 = new Plot(PlotType.GREEN ,new Position(1,0));
        assertTrue(gameEngine.askToSetPlot(plot1));
        assertFalse(gameEngine.askToSetPlot(plot2));
    }

    @Test
    void askGetMapTest() {
        assert false;
    }
}