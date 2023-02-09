package fr.cotedazur.univ.polytech.startingpoint.action;

import fr.cotedazur.univ.polytech.startingpoint.GameEngine;
import fr.cotedazur.univ.polytech.startingpoint.Position;
import fr.cotedazur.univ.polytech.startingpoint.game.Game;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class RainActionTest {
    @Mock
    GameEngine mockedGameEngine = mock(GameEngine.class);
    @Mock
    Game mockedGame = mock(Game.class);

    @Test
    void play() {
        Position position = new Position(2, 1);
        when(mockedGameEngine.rainAction(position)).thenReturn(true);

        RainAction rainAction = new RainAction(position);
        assertTrue(rainAction.play(null, mockedGameEngine));
    }

    @Test
    void verifyObjectiveAfterAction() {
        when(mockedGame.computeObjectivesGardener()).thenReturn(true);
        RainAction rainAction = new RainAction(null);
        assertTrue(rainAction.verifyObjectiveAfterAction(mockedGame));
    }

    @Test
    void isActionMoveGardener() {
        Position position = new Position(3,6);
        RainAction rainAction = new RainAction(position);
        assertFalse(rainAction.isActionMoveGardener());
    }

    @Test
    void isActionMovePanda() {
        Position position = new Position(3,6);
        RainAction rainAction = new RainAction(position);
        assertFalse(rainAction.isActionMovePanda());
    }

    @Test
    void isActionPutPlot() {
        Position position = new Position(3,6);
        RainAction rainAction = new RainAction(position);
        assertFalse(rainAction.isActionPutPlot());
    }

    @Test
    void isActionPickObjective() {
        Position position = new Position(3,6);
        RainAction rainAction = new RainAction(position);
        assertFalse(rainAction.isActionPickObjective());
    }

    @Test
    void isActionRain() {
        Position position = new Position(3,6);
        RainAction rainAction = new RainAction(position);
        assertTrue(rainAction.isActionRain());
    }

    @Test
    void isActionThunder() {
        Position position = new Position(3,6);
        RainAction rainAction = new RainAction(position);
        assertFalse(rainAction.isActionThunder());
    }

    @Test
    void getPosition() {
        Position position = new Position(3,6);
        RainAction rainAction = new RainAction(position);
        assertEquals(rainAction.getPosition(),position);
    }

    @Test
    void toType() {
        Position position = new Position(3,6);
        RainAction rainAction = new RainAction(position);
        assertEquals(rainAction.toType(), ActionType.RAIN);
    }
}