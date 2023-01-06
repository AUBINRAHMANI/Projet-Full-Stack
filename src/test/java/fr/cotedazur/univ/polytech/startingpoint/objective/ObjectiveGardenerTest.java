package fr.cotedazur.univ.polytech.startingpoint.objective;

import fr.cotedazur.univ.polytech.startingpoint.GameEngine;
import fr.cotedazur.univ.polytech.startingpoint.Plot;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ObjectiveGardenerTest {

    @Test
    void verifyPlotObj() {
        assertFalse(new ObjectiveGardener(0, 0 ,null, false).verifyPlotObj(null, null));
    }

    @Mock GameEngine mockedGameEngine = mock(GameEngine.class);
    @Test
    void verifyGardenerObj() {
        when(mockedGameEngine.computeObjectiveGardener(0, null, true)).thenReturn(true);
        ObjectiveGardener objectiveGardener = new ObjectiveGardener(0, 0, null, true);
        assertTrue(objectiveGardener.verifyGardenerObj(mockedGameEngine));
    }

    @Test
    void verifyPandaObj() {
        assertFalse(new ObjectiveGardener(0, 0, null ,false).verifyPandaObj(null));
    }
}