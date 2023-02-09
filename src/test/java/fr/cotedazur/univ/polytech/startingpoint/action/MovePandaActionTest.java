package fr.cotedazur.univ.polytech.startingpoint.action;

import fr.cotedazur.univ.polytech.startingpoint.GameEngine;
import fr.cotedazur.univ.polytech.startingpoint.Position;
import fr.cotedazur.univ.polytech.startingpoint.game.Game;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class MovePandaActionTest {

    @Mock
    GameEngine mockedGameEngine = mock(GameEngine.class);
    @Mock
    Game mockedGame = mock(Game.class);

    @Test
    void play() {
        Position position = new Position(2, 1);
        when(mockedGameEngine.movePanda(null, null, position)).thenReturn(true);

        MovePandaAction movePandaAction = new MovePandaAction(null, position);
        assertTrue(movePandaAction.play(null, mockedGameEngine));
    }

    @Test
    void verifyObjectiveAfterAction() {
        when(mockedGame.computeObjectivesPanda()).thenReturn(true);
        MovePandaAction movePandaAction = new MovePandaAction(null, null);
        assertTrue(movePandaAction.verifyObjectiveAfterAction(mockedGame));
    }
    @Test
    void isActionMoveGardener() {
        Position position = new Position(2, 1);
        MovePandaAction movePandaAction = new MovePandaAction(null, position);
        assertFalse(movePandaAction.isActionMoveGardener());
    }

    @Test
    void isActionMovePanda() {
        Position position = new Position(2, 1);
        MovePandaAction movePandaAction = new MovePandaAction(null, position);
        assertTrue(movePandaAction.isActionMovePanda());
    }

    @Test
    void isActionPutPlot() {
        Position position = new Position(2, 1);
        MovePandaAction movePandaAction = new MovePandaAction(null, position);
        assertFalse(movePandaAction.isActionPutPlot());
    }

    @Test
    void isActionPickObjective() {
        Position position = new Position(2, 1);
        MovePandaAction movePandaAction = new MovePandaAction(null, position);
        assertFalse(movePandaAction.isActionPickObjective());
    }

    @Test
    void isActionRain() {
        Position position = new Position(2, 1);
        MovePandaAction movePandaAction = new MovePandaAction(null, position);
        assertFalse(movePandaAction.isActionRain());
    }

    @Test
    void isActionThunder() {
        Position position = new Position(2, 1);
        MovePandaAction movePandaAction = new MovePandaAction(null, position);
        assertFalse(movePandaAction.isActionThunder());
    }

    @Test
    void getPosition() {
        Position position = new Position(2, 1);
        MovePandaAction movePandaAction = new MovePandaAction(null, position);
        assertEquals(movePandaAction.getPosition(),position);
    }

    @Test
    void toType() {
        Position position = new Position(2, 1);
        MovePandaAction movePandaAction = new MovePandaAction(null, position);
        assertEquals(movePandaAction.toType(), ActionType.MOVE_PANDA);
    }
}