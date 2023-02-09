package fr.cotedazur.univ.polytech.startingpoint;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BambooTest {

    @Test
    void getBambooType() {
        Bamboo bamboo = new Bamboo(PlotType.RED);
        assertEquals(bamboo.getBambooType(), PlotType.RED);
    }

    @Test
    void bambooEqualsTest(){
        Bamboo bamboo = new Bamboo(PlotType.RED);
        assertTrue(PlotType.RED.equals(bamboo.getBambooType()));
    }
}