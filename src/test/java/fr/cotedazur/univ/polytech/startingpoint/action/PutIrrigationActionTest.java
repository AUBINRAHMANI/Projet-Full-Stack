package fr.cotedazur.univ.polytech.startingpoint.action;

import fr.cotedazur.univ.polytech.startingpoint.GameEngine;
import fr.cotedazur.univ.polytech.startingpoint.Irrigation;
import fr.cotedazur.univ.polytech.startingpoint.Position;
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
        Irrigation irrigation = new Irrigation(new Position(0, 0), new Position(0, 1));
        when(mockedGameEngine.askToPutIrrigation(irrigation)).thenReturn(true);

        PutIrrigationAction putIrrigationAction = new PutIrrigationAction(irrigation);
        assertTrue(putIrrigationAction.play(null, mockedGameEngine));
    }

    @Test
    void toType() {
        Irrigation irrigation = new Irrigation(new Position(0, 0), new Position(0, 1));
        PutIrrigationAction putIrrigationAction = new PutIrrigationAction(irrigation);
        assertEquals(ActionType.PUT_IRRIGATION, putIrrigationAction.toType());
    }
}
