package fr.cotedazur.univ.polytech.startingpoint.objective;

import fr.cotedazur.univ.polytech.startingpoint.objective.ObjectivePlots;
import fr.cotedazur.univ.polytech.startingpoint.objective.ObjectiveType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ObjectivePlotsTest {

    @Test
    void getNbPlotsAdjacents() {
        ObjectivePlots objectivePlots = new ObjectivePlots(4, 2);
        assertEquals(2, objectivePlots.getNbPlotsAdjacents());
    }
}