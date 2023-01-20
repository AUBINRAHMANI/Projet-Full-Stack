package fr.cotedazur.univ.polytech.startingpoint.Action;

import fr.cotedazur.univ.polytech.startingpoint.*;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class MoveGardenerActionTest {

    @Mock GameEngine mockedGameEngine = mock(GameEngine.class);
    @Test
    void play() {
        Position position = new Position(2,1);
        Gardener gardener = new Gardener();
        when(mockedGameEngine.moveGardener(position)).thenReturn(true);

        MoveGardenerAction moveGardenerAction = new MoveGardenerAction(position);
        assertTrue(moveGardenerAction.play(null, mockedGameEngine));
    }

    @Mock Game mockedGame = mock(Game.class);
    @Test
    void verifyObjectiveAfterAction() {
        when(mockedGame.computeObjectivesGardener()).thenReturn(true);
        MoveGardenerAction moveGardenerAction = new MoveGardenerAction(null);
        assertTrue(moveGardenerAction.verifyObjectiveAfterAction(mockedGame));
    }
}