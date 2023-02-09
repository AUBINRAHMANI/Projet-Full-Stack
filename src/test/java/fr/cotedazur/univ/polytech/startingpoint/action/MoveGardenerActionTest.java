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

class MoveGardenerActionTest {

    @Mock
    GameEngine mockedGameEngine = mock(GameEngine.class);
    @Mock
    Game mockedGame = mock(Game.class);

    @Test
    void play() {
        Position position = new Position(2, 1);
        when(mockedGameEngine.moveGardener(position)).thenReturn(true);

        MoveGardenerAction moveGardenerAction = new MoveGardenerAction(position);
        assertTrue(moveGardenerAction.play(null, mockedGameEngine));
    }

    @Test
    void verifyObjectiveAfterAction() {
        when(mockedGame.computeObjectivesGardener()).thenReturn(true);
        MoveGardenerAction moveGardenerAction = new MoveGardenerAction(null);
        assertTrue(moveGardenerAction.verifyObjectiveAfterAction(mockedGame));
    }
    @Test
    void isActionMoveGardener() {
        Position position = new Position(2, 1);
        MoveGardenerAction moveGardenerAction = new MoveGardenerAction(position);
        assertTrue(moveGardenerAction.isActionMoveGardener());
    }

    @Test
    void isActionMovePanda() {
        Position position = new Position(2, 1);
        MoveGardenerAction moveGardenerAction = new MoveGardenerAction(position);
        assertFalse(moveGardenerAction.isActionMovePanda());
    }

    @Test
    void isActionPutPlot() {
        Position position = new Position(2, 1);
        MoveGardenerAction moveGardenerAction = new MoveGardenerAction(position);
        assertFalse(moveGardenerAction.isActionPutPlot());
    }

    @Test
    void isActionPickObjective() {
        Position position = new Position(2, 1);
        MoveGardenerAction moveGardenerAction = new MoveGardenerAction(position);
        assertFalse(moveGardenerAction.isActionPickObjective());
    }

    @Test
    void isActionRain() {
        Position position = new Position(2, 1);
        MoveGardenerAction moveGardenerAction = new MoveGardenerAction(position);
        assertFalse(moveGardenerAction.isActionRain());
    }

    @Test
    void isActionThunder() {
        Position position = new Position(2, 1);
        MoveGardenerAction moveGardenerAction = new MoveGardenerAction(position);
        assertFalse(moveGardenerAction.isActionThunder());
    }

    @Test
    void getPosition() {
        Position position = new Position(2, 1);
        MoveGardenerAction moveGardenerAction = new MoveGardenerAction(position);
        assertEquals(moveGardenerAction.getPosition(),position);
    }

    @Test
    void toType() {
        Position position = new Position(2, 1);
        MoveGardenerAction moveGardenerAction = new MoveGardenerAction(position);
        assertEquals(moveGardenerAction.toType(), ActionType.MOVE_GARDENER);
    }
}