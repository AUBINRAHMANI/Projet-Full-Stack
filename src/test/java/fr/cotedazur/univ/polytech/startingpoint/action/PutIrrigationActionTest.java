package fr.cotedazur.univ.polytech.startingpoint.action;

import fr.cotedazur.univ.polytech.startingpoint.*;
import fr.cotedazur.univ.polytech.startingpoint.game.Game;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class PutIrrigationActionTest {
    @Mock
    GameEngine mockedGameEngine = mock(GameEngine.class);
    @Test
    void play() {
        Irrigation irrigation = new Irrigation( new Position(0,0) , new Position(0,1));
        when(mockedGameEngine.askToPutIrrigation(irrigation)).thenReturn(true);

        PutIrrigationAction putIrrigationAction = new PutIrrigationAction(irrigation);
        assertTrue(putIrrigationAction.play(null, mockedGameEngine));
    }
}
