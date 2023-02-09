package fr.cotedazur.univ.polytech.startingpoint;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BambooTest {

    @Test
    void getBambooType() {
        Bamboo bamboo = new Bamboo(PlotType.RED);
        assertEquals(PlotType.RED, bamboo.getBambooType());
    }

    @Test
    void bambooEqualsTest(){
        Bamboo bamboo = new Bamboo(PlotType.RED);
        assertEquals(PlotType.RED, bamboo.getBambooType());
    }
}