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
        Pattern pattern = new Pattern();
        when(mockedGameEngine.computeObjectivePlot(pattern, null)).thenReturn(true);
        ObjectivePlots objectivePlots = new ObjectivePlots(0, pattern);
        assertTrue(objectivePlots.verifyPlotObj(mockedGameEngine, null));
    }

    @Test
    void verifyGardenerObj() {
        assertFalse(new ObjectivePlots(0, (Pattern) null).verifyGardenerObj(null));
    }

    @Test
    void verifyPandaObj() {
        assertFalse(new ObjectivePlots(0, (Pattern) null).verifyPandaObj(null));
    }
}