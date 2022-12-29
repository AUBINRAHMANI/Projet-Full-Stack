package fr.cotedazur.univ.polytech.startingpoint.objective;

import fr.cotedazur.univ.polytech.startingpoint.*;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ObjectivePlotsTest {

    @Mock GameEngine mockedGameEngine = mock(GameEngine.class);
    @Test
    void verifyPlotObj() {
        ArrayList<Plot> pattern = new ArrayList<>();
        when(mockedGameEngine.computeObjectivePlot(pattern)).thenReturn(true);
        ObjectivePlots objectivePlots = new ObjectivePlots(0, pattern);
        assertTrue(objectivePlots.verifyPlotObj(mockedGameEngine));
    }

    @Test
    void verifyGardenerObj() {
        assertFalse(new ObjectivePlots(0,null).verifyGardenerObj(null));
    }

    @Test
    void verifyPandaObj() {
        assertFalse(new ObjectivePlots(0,null).verifyPandaObj(null));
    }
}