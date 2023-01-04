package fr.cotedazur.univ.polytech.startingpoint;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class PositionTest {

    @Test
    void getQ() {
        Position position = new Position(1, 2,3);
        assertEquals(1, position.getQ());
    }

    @Test
    void getR() {
        Position position = new Position(1, 2,3);
        assertEquals(2, position.getR());
    }

    @Test
    void getS() {
        Position position = new Position(1, 2,3);
        assertEquals(3, position.getS());
    }

    @Test
    void getX() {
        Position position = new Position(1, 2);
        assertEquals(1, position.getX());
    }

    @Test
    void  getY() {
        Position position = new Position(1, 2);
        assertEquals(2, position.getY());
    }

    @Test
    void closestPosition() {
        ArrayList<Position> expected = new ArrayList<>();
        expected.add(new Position(3,2,4));
        expected.add(new Position(4,2,3));
        expected.add(new Position(4,3,2));
        expected.add(new Position(3,4,2));
        expected.add(new Position(2,4,3));
        expected.add(new Position(2,3,4));

        Position position = new Position(3,3,3);
        assertTrue(expected.containsAll(position.closestPositions()));
        assertEquals(6, position.closestPositions().size());
    }


    @Test
    void rotate60Right() {
        Position position = new Position( -2,3, -1);

        position.rotate60Right();
        assertEquals(1, position.getQ());
        assertEquals(2, position.getR());
        assertEquals(-3, position.getS());

        position.rotate60Right();
        assertEquals(3, position.getQ());
        assertEquals(-1, position.getR());
        assertEquals(-2, position.getS());

        position.rotate60Right();
        assertEquals(2, position.getQ());
        assertEquals(-3, position.getR());
        assertEquals(1, position.getS());
    }

    @Test
    void translateUP() {
        Position position1 = new Position(2,3);
        Position position2 = new Position(2,4);
        Position position3 = new Position(2,5);
        position1.translateUP();
        assertEquals(position2, position1);
        position1.translateUP();
        assertEquals(position3, position1);
    }
    @Test
    void translateDown() {
        Position position1 = new Position(2,3);
        Position position2 = new Position(2,4);
        Position position3 = new Position(2,5);
        position3.translateDown();
        assertEquals(position2, position3);
        position3.translateDown();
        assertEquals(position1, position3);
    }
    @Test
    void plus(){
        Position position1 = new Position(-1,0);
        Position position2 = new Position(3,3);
        Position position3 = new Position(3, 2);
        assertEquals(new Position(2,2), position1.plus(position2) );
        assertEquals(new Position(2,1), position1.plus(position3) );
    }
}