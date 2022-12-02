package fr.cotedazur.univ.polytech.startingpoint;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ObjectiveTest {

    @Test
    void TestgetPoint() {
        Objective Obj = new Objective();
        assertEquals(4, Obj.getPoint());
    }

    @Test
    void TestType() {
        Objective Obj = new Objective();
        assertEquals(ObjectiveType.PLOT, Obj.type());
    }
}