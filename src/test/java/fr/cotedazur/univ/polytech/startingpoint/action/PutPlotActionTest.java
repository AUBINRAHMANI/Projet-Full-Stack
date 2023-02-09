package fr.cotedazur.univ.polytech.startingpoint.action;

import fr.cotedazur.univ.polytech.startingpoint.GameEngine;
import fr.cotedazur.univ.polytech.startingpoint.Plot;
import fr.cotedazur.univ.polytech.startingpoint.PlotType;
import fr.cotedazur.univ.polytech.startingpoint.Position;
import fr.cotedazur.univ.polytech.startingpoint.game.Game;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class PutPlotActionTest {

    @Mock
    GameEngine mockedGameEngine = mock(GameEngine.class);
    @Mock
    Game mockedGame = mock(Game.class);

    @Test
    void play() {
        Plot plot = new Plot(PlotType.GREEN, new Position(2, 1));
        when(mockedGameEngine.askToPutPlot(plot)).thenReturn(true);

        PutPlotAction putPlotAction = new PutPlotAction(plot);
        assertTrue(putPlotAction.play(null, mockedGameEngine));
    }

    @Test
    void verifyObjectiveAfterAction() {
        when(mockedGame.computeObjectivesPlot(null)).thenReturn(true);
        PutPlotAction putPlotAction = new PutPlotAction(null);
        assertTrue(putPlotAction.verifyObjectiveAfterAction(mockedGame, null));
    }
    @Test
    void isActionMoveGardener() {
        Plot plot = new Plot(PlotType.GREEN, new Position(2, 1));
        PutPlotAction putPlotAction = new PutPlotAction(plot);
        assertFalse(putPlotAction.isActionMoveGardener());
    }

    @Test
    void isActionMovePanda() {
        Plot plot = new Plot(PlotType.GREEN, new Position(2, 1));
        PutPlotAction putPlotAction = new PutPlotAction(plot);
        assertFalse(putPlotAction.isActionMovePanda());
    }

    @Test
    void isActionPutPlot() {
        Plot plot = new Plot(PlotType.GREEN, new Position(2, 1));
        PutPlotAction putPlotAction = new PutPlotAction(plot);
        assertTrue(putPlotAction.isActionPutPlot());
    }

    @Test
    void isActionPickObjective() {
        Plot plot = new Plot(PlotType.GREEN, new Position(2, 1));
        PutPlotAction putPlotAction = new PutPlotAction(plot);
        assertFalse(putPlotAction.isActionPickObjective());
    }

    @Test
    void isActionRain() {
        Plot plot = new Plot(PlotType.GREEN, new Position(2, 1));
        PutPlotAction putPlotAction = new PutPlotAction(plot);
        assertFalse(putPlotAction.isActionRain());
    }

    @Test
    void isActionThunder() {
        Plot plot = new Plot(PlotType.GREEN, new Position(2, 1));
        PutPlotAction putPlotAction = new PutPlotAction(plot);
        assertFalse(putPlotAction.isActionThunder());
    }
    @Test
    void getPosition() {
        Plot plot = new Plot(PlotType.GREEN, new Position(2, 1));
        PutPlotAction putPlotAction = new PutPlotAction(plot);
        assertEquals(putPlotAction.getPosition(),new Position(2,1));
    }
    @Test
    void toType() {
        Plot plot = new Plot(PlotType.GREEN, new Position(2, 1));
        PutPlotAction putPlotAction = new PutPlotAction(plot);
        assertEquals(putPlotAction.toType(), ActionType.PUT_PLOT);
    }
}