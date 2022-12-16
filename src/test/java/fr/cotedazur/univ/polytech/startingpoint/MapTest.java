package fr.cotedazur.univ.polytech.startingpoint;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MapTest {

    @Test
    void putPlot() {
        Map map = new Map();
        Plot plot = new Plot(PlotType.GREEN, new Position(1,0));
        assertTrue(map.putPlot(plot));
    }

    @Test
    void getMap() {
        Map map = new Map();
        Plot plot = new Plot(PlotType.GREEN, new Position(1,0));
        map.putPlot(plot);
        assertEquals(map.getMap().get(1), plot);
    }


    @Test
    void isSpaceFree() {
        Map map = new Map();
        Plot plot = new Plot(PlotType.GREEN, new Position(0,0));
        map.putPlot(plot);
        assertFalse(map.isSpaceFree(new Position(0,0)));
    }

    @Test
    void closestAvailableSpace() {
        Map map = new Map();
        assertTrue(map.closestAvailableSpace(new Position(0, 0)).get(0).equals(new Position(-1, 0)));
    }

    @Test
    void haveNeighbours() {
        Map map = new Map();
        Plot plot = new Plot(PlotType.GREEN, new Position(0,0));
        map.putPlot(plot);
        assertTrue(map.haveNeighbours(new Position(0,1)));
    }
}