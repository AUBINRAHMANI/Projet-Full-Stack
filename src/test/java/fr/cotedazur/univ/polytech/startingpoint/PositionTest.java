package fr.cotedazur.univ.polytech.startingpoint;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class PositionTest {

    @Test
    void getX_() {
        Position position = new Position(3, 4);
        assertEquals(3, position.getX_());
    }

    @Test
    void getY_() {
        Position position = new Position(3, 4);
        assertEquals(4, position.getY_());
    }
}