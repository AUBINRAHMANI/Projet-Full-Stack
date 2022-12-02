package fr.cotedazur.univ.polytech.startingpoint;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GameEngineTest {


    @Test
    void pickObjectiveTest() {
        GameEngine gameEngine = new GameEngine();
        assertEquals(4 , gameEngine.pickObjective().getPoint());
        assertEquals( ObjectiveType.PLOT , gameEngine.pickObjective().getType());
    }

    @Test
    void pickPlotTest() {
        GameEngine gameEngine = new GameEngine();
        assertEquals(PlotType.VERT , gameEngine.pickPlot().getType());
    }

    @Test
    void askToSetPlotTest() {
        GameEngine gameEngine = new GameEngine();
        Position position1 = new Position(1,0);
        Position position2 = new Position(0,0);
        assertTrue(gameEngine.askToSetPlot(position1));
        assertFalse(gameEngine.askToSetPlot(position2));
    }

    @Test
    void askGetMapTest() {
        assert false;
    }
}