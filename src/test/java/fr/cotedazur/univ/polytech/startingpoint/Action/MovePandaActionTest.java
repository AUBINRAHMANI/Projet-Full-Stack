package fr.cotedazur.univ.polytech.startingpoint.Action;

import fr.cotedazur.univ.polytech.startingpoint.Game;
import fr.cotedazur.univ.polytech.startingpoint.GameEngine;
import fr.cotedazur.univ.polytech.startingpoint.Position;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class MovePandaActionTest {

    @Mock
    GameEngine mockedGameEngine = mock(GameEngine.class);
    @Test
    void play() {
        Position position = new Position(2,1);
        when(mockedGameEngine.movePanda(position)).thenReturn(true);

        MovePandaAction movePandaAction = new MovePandaAction(position);
        assertTrue(movePandaAction.play(mockedGameEngine));
    }

    @Mock
    Game mockedGame = mock(Game.class);
    @Test
    void verifyObjectiveAfterAction() {
        when(mockedGame.computeObjectivesPanda()).thenReturn(true);
        MovePandaAction movePandaAction = new MovePandaAction(null);
        assertTrue(movePandaAction.verifyObjectiveAfterAction(mockedGame));
    }
}