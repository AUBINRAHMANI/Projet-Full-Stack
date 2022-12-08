package fr.cotedazur.univ.polytech.startingpoint;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MapTest {
    @BeforeAll
    Map plateau = new Map(int x, int y);
    Plot nextToPond = new Plot(int x, int y);
    Plot notNextToPond = new Plot(int x, inty);
    Pond etang = new Pond(int x, int y);


    @Test
    void isIrrigatedTest() {
        AssertEquals(isIrrigated(etang),nextToPond.isCloseToPond());
        AssertEquals(isIrrigated(p1),notNextToPond?isCloseToPond());
    }

    @Test

    void isPlotFreeTest() {


    }


}