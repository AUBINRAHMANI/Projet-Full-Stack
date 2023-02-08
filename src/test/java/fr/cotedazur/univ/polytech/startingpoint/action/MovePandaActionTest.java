package fr.cotedazur.univ.polytech.startingpoint.action;

import fr.cotedazur.univ.polytech.startingpoint.GameEngine;
import fr.cotedazur.univ.polytech.startingpoint.Position;
import fr.cotedazur.univ.polytech.startingpoint.game.Game;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertTrue;
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
}