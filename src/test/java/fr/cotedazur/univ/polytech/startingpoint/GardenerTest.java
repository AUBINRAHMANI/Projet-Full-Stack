package fr.cotedazur.univ.polytech.startingpoint;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GardenerTest {

    @Test
    void setPositionTest() {
        Gardener gardener = new Gardener();
        Position position = new Position(2, 1);
        gardener.setPosition(position);
        assertEquals(position, gardener.getPosition());
    }

    @Test
    void getPositionTest(){
        Gardener gardener = new Gardener();
        assertEquals(gardener.getPosition(),new Position(0,0));
    }
}
