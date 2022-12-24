package fr.cotedazur.univ.polytech.startingpoint.objective;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ObjectiveGardenerTest {

    @Test
    void getNbBambouSection() {
        ObjectiveGardener objectiveGardener = new ObjectiveGardener(2, 3, false, 1);
        assertEquals(3, objectiveGardener.getNbBambouSections());
    }

    @Test
    void needImprovement() {
        ObjectiveGardener objectiveGardener = new ObjectiveGardener(2, 3, false, 1);
        assertTrue(objectiveGardener.needImprovement());
    }
}