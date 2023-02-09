package fr.cotedazur.univ.polytech.startingpoint.action;

import fr.cotedazur.univ.polytech.startingpoint.GameEngine;
import fr.cotedazur.univ.polytech.startingpoint.Position;
import fr.cotedazur.univ.polytech.startingpoint.game.Game;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ThunderActionTest {
    @Mock
    GameEngine mockedGameEngine = mock(GameEngine.class);
    @Mock
    Game mockedGame = mock(Game.class);

    @Test
    void play() {
        Position position = new Position(2, 1);
        when(mockedGameEngine.thunderAction(position)).thenReturn(true);

       ThunderAction thunderAction = new ThunderAction(position);
        assertTrue(thunderAction.play(null, mockedGameEngine));
    }

    @Test
    void verifyObjectiveAfterAction() {
        when(mockedGame.computeObjectivesPanda()).thenReturn(true);
        ThunderAction thunderAction  = new ThunderAction(null);
        assertTrue(thunderAction.verifyObjectiveAfterAction(mockedGame, null));
    }

    @Test
    void getPosition() {
        Position position = new Position(3,6);
        ThunderAction thunderAction = new ThunderAction(position);
        assertEquals(thunderAction.getPosition(), position);
    }

    @Test
    void toType() {
        Position position = new Position(3,6);
        ThunderAction thunderAction = new ThunderAction(position);
        assertEquals(thunderAction.toType(), ActionType.THUNDER);
    }
}
