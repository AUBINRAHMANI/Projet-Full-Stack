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
    void isPossibleToPutPlot() {
        Map map = new Map();
        Plot plot = new Plot(PlotType.GREEN, new Position(1,0));
        map.putPlot(plot);
        assertFalse(map.isPossibleToPutPlot(new Position(1,0)));
        assertFalse(map.isPossibleToPutPlot(new Position(2,0)));
        assertTrue(map.isPossibleToPutPlot(new Position(1,1)));
    }

    @Test
    void haveNeighbours() {
        Map map = new Map();
        Plot plot = new Plot(PlotType.GREEN, new Position(0,0));
        map.putPlot(plot);
        assertTrue(map.haveNeighbours(new Position(0,1)));
    }

    @Test
    void findPlot() {
        Map map = new Map();
        Plot plot = new Plot(PlotType.GREEN, new Position(3,3));
        map.putPlot(plot);
        assertEquals(plot, map.findPlot(new Position(3,3)));
    }
}