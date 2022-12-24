package fr.cotedazur.univ.polytech.startingpoint.objective;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ObjectiveTest {

    @Test
    void getPoint() {
        Objective Obj = new Objective(4, ObjectiveType.PLOTS);
        assertEquals(4, Obj.getPoint());
    }

    @Test
    void getType() {
        Objective objective = new Objective(4, ObjectiveType.PLOTS);
        assertEquals(ObjectiveType.PLOTS, objective.getType());
    }
}