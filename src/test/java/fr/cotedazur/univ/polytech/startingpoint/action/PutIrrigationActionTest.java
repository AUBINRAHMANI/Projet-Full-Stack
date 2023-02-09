package fr.cotedazur.univ.polytech.startingpoint.action;

import fr.cotedazur.univ.polytech.startingpoint.GameEngine;
import fr.cotedazur.univ.polytech.startingpoint.Irrigation;
import fr.cotedazur.univ.polytech.startingpoint.Position;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
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
        assertEquals(putIrrigationAction.toType(), ActionType.PUT_IRRIGATION);
    }

    @Test
    void equals() {
        Action action1 = new PutIrrigationAction(new Irrigation(new Position(0, 1), new Position(1, 1)));
        Action action2 = new PutIrrigationAction(new Irrigation(new Position(1, 1), new Position(0, 1)));
        assertEquals(action1, action2);
    }
}
