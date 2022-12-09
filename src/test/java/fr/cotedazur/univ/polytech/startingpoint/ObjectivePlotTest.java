package fr.cotedazur.univ.polytech.startingpoint;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ObjectivePlotTest {

    @Test
    void TestgetNbPlotsAdjacents() {
        ObjectivePlot objectivePlot = new ObjectivePlot(4, ObjectiveType.PLOT, 2);
        assertEquals(2, objectivePlot.getNbPlotsAdjacents());
    }
}