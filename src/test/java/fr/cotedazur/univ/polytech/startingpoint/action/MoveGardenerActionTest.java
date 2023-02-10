package fr.cotedazur.univ.polytech.startingpoint.action;

import fr.cotedazur.univ.polytech.startingpoint.game.game_engine.GameEngine;
import fr.cotedazur.univ.polytech.startingpoint.game.game_engine.map.Position;
import fr.cotedazur.univ.polytech.startingpoint.game.Game;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
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
        assertTrue(moveGardenerAction.verifyObjectiveAfterAction(mockedGame, null));
    }

    @Test
    void getPosition() {
        Position position = new Position(2, 1);
        MoveGardenerAction moveGardenerAction = new MoveGardenerAction(position);
        assertEquals(moveGardenerAction.getPosition(), position);
    }

    @Test
    void toType() {
        Position position = new Position(2, 1);
        MoveGardenerAction moveGardenerAction = new MoveGardenerAction(position);
        assertEquals(ActionType.MOVE_GARDENER, moveGardenerAction.toType());
    }

    @Test
    void equals() {
        Action action1 = new MoveGardenerAction(new Position(1, 0));
        Action action2 = new MoveGardenerAction(new Position(3, 0));
        assertEquals(action1, action2);
    }
}