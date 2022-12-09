package fr.cotedazur.univ.polytech.startingpoint;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

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

    @Test
    void closestPosition() {
        ArrayList<Position> expected = new ArrayList<>();
        expected.add(new Position(2,3));
        expected.add(new Position(2,4));
        expected.add(new Position(3,2));
        expected.add(new Position(3,4));
        expected.add(new Position(4,3));
        expected.add(new Position(4,4));

        Position position = new Position(3,3);
        assertTrue(expected.equals(position.closestPositions()));
    }
}