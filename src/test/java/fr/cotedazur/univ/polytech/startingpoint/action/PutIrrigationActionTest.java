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
    void isActionMoveGardener() {
        Irrigation irrigation = new Irrigation(new Position(0, 0), new Position(0, 1));
        PutIrrigationAction putIrrigationAction = new PutIrrigationAction(irrigation);
        assertFalse(putIrrigationAction.isActionMoveGardener());
    }

    @Test
    void isActionMovePanda() {
        Irrigation irrigation = new Irrigation(new Position(0, 0), new Position(0, 1));
        PutIrrigationAction putIrrigationAction = new PutIrrigationAction(irrigation);
        assertFalse(putIrrigationAction.isActionMovePanda());
    }

    @Test
    void isActionPutPlot() {
        Irrigation irrigation = new Irrigation(new Position(0, 0), new Position(0, 1));
        PutIrrigationAction putIrrigationAction = new PutIrrigationAction(irrigation);
        assertFalse(putIrrigationAction.isActionPutPlot());
    }

    @Test
    void isActionPickObjective() {
        Irrigation irrigation = new Irrigation(new Position(0, 0), new Position(0, 1));
        PutIrrigationAction putIrrigationAction = new PutIrrigationAction(irrigation);
        assertFalse(putIrrigationAction.isActionPickObjective());
    }

    @Test
    void isActionRain() {
        Irrigation irrigation = new Irrigation(new Position(0, 0), new Position(0, 1));
        PutIrrigationAction putIrrigationAction = new PutIrrigationAction(irrigation);
        assertFalse(putIrrigationAction.isActionRain());
    }

    @Test
    void isActionThunder() {
        Irrigation irrigation = new Irrigation(new Position(0, 0), new Position(0, 1));
        PutIrrigationAction putIrrigationAction = new PutIrrigationAction(irrigation);
        assertFalse(putIrrigationAction.isActionThunder());
    }
    @Test
    void toType() {
        Irrigation irrigation = new Irrigation(new Position(0, 0), new Position(0, 1));
        PutIrrigationAction putIrrigationAction = new PutIrrigationAction(irrigation);
        assertEquals(putIrrigationAction.toType(),ActionType.PUT_IRRIGATION);
    }
}
