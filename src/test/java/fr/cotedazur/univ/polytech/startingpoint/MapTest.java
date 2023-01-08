package fr.cotedazur.univ.polytech.startingpoint;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class MapTest {

    @Test
    void putPlot() {
        Map map = new Map();
        Plot plot1 = new Plot(PlotType.GREEN, new Position(1,0));
        Plot plot2 = new Plot(PlotType.GREEN, new Position(1,1));
        Plot plot3 = new Plot(PlotType.GREEN, new Position(2,1));
        assertTrue(map.putPlot(plot1));
        assertTrue(map.putPlot(plot2));
        assertFalse(map.putPlot(plot3));
    }

    @Test
    void getMap() {
        Map map = new Map();
        ArrayList<Plot> plots = new ArrayList<>();
        plots.add(new Plot(PlotType.GREEN, new Position(1,0)));
        plots.add(new Plot(PlotType.POND, new Position(0, 0)));

        for(Plot plot : plots){
            map.putPlot(plot);
        }
        assertTrue(plots.containsAll(map.getMap()));
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
    void getNeighbours() {
        Map map = new Map();
        ArrayList<Plot> plots = new ArrayList<>();
        Plot plot1 = new Plot(PlotType.POND, new Position(0,0));
        Plot plot2 = new Plot(PlotType.GREEN, new Position(0,1));
        Plot plot3 = new Plot(PlotType.GREEN, new Position(1,1));
        Plot plot4 = new Plot(PlotType.GREEN, new Position(1,0));
        Plot plot5 = new Plot(PlotType.GREEN, new Position(2,0));

        plots.add(plot1);
        plots.add(plot2);
        plots.add(plot4);
        plots.add(plot5);

        map.putPlot(plot2);
        map.putPlot(plot3);
        map.putPlot(plot4);
        map.putPlot(plot5);

        //System.out.println(plots);
        //System.out.println(map.getNeighbours(plot3));
        assertTrue(map.getNeighbours(plot3.getPosition()).containsAll(plots));
    }

    @Test
    void findPlot() {
        Map map = new Map();
        Plot plot = new Plot(PlotType.GREEN, new Position(0,1));
        map.putPlot(plot);
        assertEquals(plot, map.findPlot(new Position(0,1)));
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
        Plot plot = new Plot(PlotType.GREEN, new Position(1,0));
        map.putPlot(plot);
        plot.isIrrigatedIsTrue();
        map.growBambou(new Position(1,0));
        assertEquals(1, plot.getNumberOfBambou());
    }

    @Test
    public void closestAvailableSpace(){
        Map map = new Map();
        Plot plot1 = new Plot(PlotType.GREEN, new Position(1,0));
        Plot plot2 = new Plot(PlotType.GREEN, new Position(1,1));
        Plot plot3 = new Plot(PlotType.GREEN, new Position(2,0));

        map.putPlot(plot1);
        map.putPlot(plot2);
        map.putPlot(plot3);

        ArrayList<Position> expected = new ArrayList<>();
        expected.add(new  Position(0,1));
        expected.add(new  Position(2,1));

        assertFalse(map.closestAvailableSpace(new Position(1,0)).contains(new Position(1,1)));
        assertFalse(map.closestAvailableSpace(new Position(1,0)).contains(new Position(1,-1)));
        assertFalse(map.closestAvailableSpace(new Position(1,0)).contains(new Position(2,0)));
        assertFalse(map.closestAvailableSpace(new Position(1,0)).contains(new Position(0,1)));
        assertTrue(map.closestAvailableSpace(new Position(1,1)).containsAll(expected));
        assertTrue(map.closestAvailableSpace(new Position(1,1)).size() == 2);

    }

}