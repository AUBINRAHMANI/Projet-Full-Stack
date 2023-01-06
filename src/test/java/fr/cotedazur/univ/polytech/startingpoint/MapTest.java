package fr.cotedazur.univ.polytech.startingpoint;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

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
        assertFalse(map.isPossibleToPutPlot(new Position(5,6)));
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

    @Test
    void rotatePattern() {
        Map map = new Map();
        ArrayList<Plot> pattern = new ArrayList<>();
        pattern.add( new Plot(PlotType.GREEN, new Position(0,1)));
        pattern.add( new Plot(PlotType.GREEN, new Position(1,1)));
        pattern.add( new Plot(PlotType.GREEN, new Position(1,2)));
        pattern.add( new Plot(PlotType.GREEN, new Position(2,1)));

        ArrayList<Plot> patternExpected = new ArrayList<>();
        patternExpected.add( new Plot(PlotType.GREEN, new Position(1,1)));
        patternExpected.add( new Plot(PlotType.GREEN, new Position(1,0)));
        patternExpected.add( new Plot(PlotType.GREEN, new Position(2,0)));
        patternExpected.add( new Plot(PlotType.GREEN, new Position(2,-1)));

        map.rotatePattern(pattern);
        assertEquals(patternExpected, pattern);
    }

    @Test
    public void growBambouTest() {
        Map map = new Map();
        Plot plot = new Plot(PlotType.GREEN, new Position(2,2));
        map.putPlot(plot);
        plot.isIrrigatedIsTrue();
        map.growBambou(new Position(2,2));
        assertEquals(1, plot.getNumberOfBambou());
    }
}