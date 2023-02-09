package fr.cotedazur.univ.polytech.startingpoint;

import fr.cotedazur.univ.polytech.startingpoint.game.Game;
import fr.cotedazur.univ.polytech.startingpoint.logger.Loggeable;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class MapTest implements Loggeable {

    @Test
    void putPlot() {
        Map map = new Map();
        Plot plot1 = new Plot(PlotType.GREEN, new Position(1, 0));
        Plot plot2 = new Plot(PlotType.GREEN, new Position(1, 1));
        Plot plot3 = new Plot(PlotType.GREEN, new Position(2, 1));
        assertTrue(map.putPlot(plot1));
        assertTrue(map.putPlot(plot2));
        assertFalse(map.putPlot(plot3));
    }

    @Test
    void verifyIrrigation() {
        Map map = new Map();
        Plot plot1 = new Plot(PlotType.GREEN, new Position(0, 1));
        Plot plot2 = new Plot(PlotType.GREEN, new Position(1, 1));
        Plot plot3 = new Plot(PlotType.GREEN, new Position(1, 2));
        map.putPlot(plot1);
        map.putPlot(plot2);
        map.putPlot(plot3);
        assertTrue(map.verifyIrrigation(plot1));
        assertTrue(map.verifyIrrigation(plot2));
        assertFalse(map.verifyIrrigation(plot3));
    }

    @Test
    void getMap() {
        Map map = new Map();
        ArrayList<Plot> plots = new ArrayList<>();
        plots.add(new Plot(PlotType.GREEN, new Position(1, 0)));
        plots.add(new Plot(PlotType.GREEN, new Position(1, 1)));
        plots.add(new Plot(PlotType.GREEN, new Position(0, 1)));
        for (Plot plot : plots) {
            map.putPlot(plot);
            plot.isIrrigatedIsTrue();
        }
        Plot pond = new Plot(PlotType.POND, new Position(0, 0));
        pond.isIrrigatedIsTrue();
        plots.add(pond);

        assertTrue(plots.containsAll(map.getMapPlots()));
    }

    @Test
    void isSpaceFree() {
        Map map = new Map();
        Plot plot = new Plot(PlotType.GREEN, new Position(1, 0));
        map.putPlot(plot);
        assertFalse(map.isSpaceFree(new Position(1, 0)));
        assertTrue(map.isSpaceFree(new Position(2, 0)));
        assertTrue(map.isSpaceFree(new Position(1, 1)));
    }

    @Test
    void isPossibleToPutPlot() {
        Map map = new Map();
        Plot plot = new Plot(PlotType.GREEN, new Position(1, 0));
        map.putPlot(plot);
        assertFalse(map.isPossibleToPutPlot(new Position(1, 0)));
        assertFalse(map.isPossibleToPutPlot(new Position(5, 6)));
        assertTrue(map.isPossibleToPutPlot(new Position(1, 1)));
    }

    @Test
    void getNeighbours() {
        Map map = new Map();
        ArrayList<Plot> plots = new ArrayList<>();
        Plot plot2 = new Plot(PlotType.GREEN, new Position(0, 1));
        Plot plot3 = new Plot(PlotType.GREEN, new Position(1, 1));
        Plot plot4 = new Plot(PlotType.GREEN, new Position(1, 0));
        Plot plot5 = new Plot(PlotType.GREEN, new Position(2, 0));

        plots.add(plot2);
        plots.add(plot4);
        plots.add(plot5);

        map.putPlot(plot2);
        map.putPlot(plot3);
        map.putPlot(plot4);
        map.putPlot(plot5);
        assertTrue(map.getNeighbours(plot3.getPosition()).containsAll(plots));
    }

    @Test
    void findPlot() {
        Map map = new Map();
        Plot plot = new Plot(PlotType.GREEN, new Position(0, 1));
        map.putPlot(plot);
        assertEquals(plot, map.findPlot(new Position(0, 1)));
    }


    @Test
    void closestAvailableSpace() {
        Map map = new Map();
        Plot plot1 = new Plot(PlotType.GREEN, new Position(1, 0));
        Plot plot2 = new Plot(PlotType.GREEN, new Position(1, 1));
        Plot plot3 = new Plot(PlotType.GREEN, new Position(2, 0));

        map.putPlot(plot1);
        map.putPlot(plot2);
        map.putPlot(plot3);

        ArrayList<Position> expected = new ArrayList<>();
        expected.add(new Position(0, 1));
        expected.add(new Position(2, 1));

        assertFalse(map.closestAvailableSpace(new Position(1, 0)).contains(new Position(1, 1)));
        assertFalse(map.closestAvailableSpace(new Position(1, 0)).contains(new Position(1, -1)));
        assertFalse(map.closestAvailableSpace(new Position(1, 0)).contains(new Position(2, 0)));
        assertFalse(map.closestAvailableSpace(new Position(1, 0)).contains(new Position(0, 1)));
        assertTrue(map.closestAvailableSpace(new Position(1, 1)).containsAll(expected));
        assertEquals(2, map.closestAvailableSpace(new Position(1, 1)).size());

    }

    @Test
    void computePatternVerification() {
        ArrayList<Plot> plots = new ArrayList<>();
        plots.add(new Plot(PlotType.GREEN, new Position(0, 1)));
        plots.add(new Plot(PlotType.POND, new Position(0, 0)));
        plots.add(new Plot(PlotType.GREEN, new Position(1, 2)));
        plots.add(new Plot(PlotType.RED, new Position(1, 1)));
        for (Plot plot : plots) {
            plot.isIrrigatedIsTrue();
        }
        Pattern pattern = new Pattern(plots);

        List<List<Plot>> result;

        ArrayList<Plot> plotsInMap = new ArrayList<>();
        plotsInMap.add(new Plot(PlotType.RED, new Position(1, 1)));
        plotsInMap.add(new Plot(PlotType.GREEN, new Position(0, 1)));

        Map map = new Map();
        for (Plot plot : plotsInMap) {
            map.putPlot(plot);
        }

        Plot lastPlot = new Plot(PlotType.GREEN, new Position(1, 2));
        result = map.computePatternVerification(pattern, plots.get(1).getPosition()).get();
        assertTrue(result.get(0).contains(lastPlot));
        assertTrue(result.get(1).contains(lastPlot));

        map.putPlot(lastPlot);
        result = map.computePatternVerification(pattern, plots.get(1).getPosition()).get();
        assertTrue(result.get(0).isEmpty());
        assertTrue(result.get(1).contains(lastPlot));

        lastPlot.isIrrigatedIsTrue();

        result = map.computePatternVerification(pattern, plots.get(1).getPosition()).get();
        assertTrue(result.get(0).isEmpty());
        assertTrue(result.get(1).isEmpty());

        plots.set(0, new Plot(PlotType.YELLOW, new Position(0,1)));
        pattern = new Pattern(plots);

        Optional<List<List<Plot>>> result2 = map.computePatternVerification(pattern, plots.get(1).getPosition());
        assertTrue(!result2.isPresent());

    }

    @Test
    void putIrrigation() {
        Map map = new Map();
        Irrigation irrigation1 = new Irrigation(new Position(0, 1), new Position(1, 1));
        Irrigation irrigation2 = new Irrigation(new Position(0, 1), new Position(1, 2));
        Irrigation irrigation3 = new Irrigation(new Position(0, 2), new Position(1, 2));

        assertFalse(map.putIrrigation(irrigation1));
        map.putPlot(new Plot(PlotType.GREEN, new Position(0, 1)));
        assertTrue(map.putIrrigation(irrigation1));
        assertFalse(map.putIrrigation(irrigation3));
        map.putPlot(new Plot(PlotType.GREEN, new Position(1, 1)));
        map.putPlot(new Plot(PlotType.GREEN, new Position(1, 2)));
        assertTrue(map.putIrrigation(irrigation2));
        assertTrue(map.putIrrigation(irrigation3));
    }

    @Test
    void isIrrigationLinked() {
        Map map = new Map();
        Irrigation irrigation1 = new Irrigation(new Position(0, 1), new Position(1, 1));
        Irrigation irrigation2 = new Irrigation(new Position(0, 1), new Position(1, 2));

        assertTrue(map.isIrrigationLinked(irrigation1));
        assertFalse(map.isIrrigationLinked(irrigation2));
    }

    @Test
    void checkIfPossibleToPlacePattern(){
        Map map = new Map();
        ArrayList<Plot> plots = new ArrayList<>();
        plots.add(new Plot( PlotType.POND, new Position(0,0)));
        plots.add(new Plot( PlotType.RED, new Position(1,0)));
        plots.add(new Plot( PlotType.YELLOW, new Position(0,1)));
        plots.add(new Plot( PlotType.YELLOW, new Position(2,0)));

        Pattern pattern = new Pattern(plots);

        List<List<Plot>> plots2 = map.checkIfPossibleToPlacePattern(pattern, plots.get(0).getPosition()).get();
        assertEquals(3 ,plots2.get(0).size());
        assertEquals(3 ,plots2.get(1).size());

        map.putPlot(plots.get(1));

        plots2 = map.checkIfPossibleToPlacePattern(pattern, plots.get(0).getPosition()).get();
        assertEquals(2 ,plots2.get(0).size());
        assertEquals(2 ,plots2.get(1).size());

        map.putPlot(plots.get(2));
        map.putPlot(new Plot(PlotType.GREEN, new Position(1,1)));
        map.putPlot(new Plot(PlotType.GREEN, new Position(1,2)));
        map.putPlot(plots.get(3));

        plots2 = map.checkIfPossibleToPlacePattern(pattern, plots.get(0).getPosition()).get();
        assertEquals(0 ,plots2.get(0).size());
        assertEquals(1 ,plots2.get(1).size());

        plots.set(0, new Plot( PlotType.YELLOW, new Position(0,0)));
        pattern = new Pattern(pattern);

        for(int i=-5; i<5 ; ++i){
            for(int j=-5; j<5 ; ++j){
                map.putPlot(new Plot(PlotType.RED, new Position(i,j)));
            }
        }
        Optional<List<List<Plot>>> result = map.checkIfPossibleToPlacePattern(pattern, plots.get(0).getPosition());
        assertFalse(result.isPresent());

    }

    @Test
    void getPathBetweenPositions(){
        Map map = new Map();
        map.putPlot(new Plot(PlotType.GREEN, new Position(0,1)));
        map.putPlot(new Plot(PlotType.GREEN, new Position(1,1)));
        map.putPlot(new Plot(PlotType.GREEN, new Position(1,2)));
        map.putPlot(new Plot(PlotType.GREEN, new Position(2,1)));
        map.putPlot(new Plot(PlotType.GREEN, new Position(2,2)));

        List<Position> result = map.getPathBetweenPositions(new Position(0,0), new Position(2,2));

        List<Position> expected = new ArrayList<>();
        expected.add(new Position(0,0));
        expected.add(new Position(1,1));
        expected.add(new Position(2,1));
        expected.add(new Position(2,2));

        assertTrue(result.containsAll(expected));
    }

    @Test
    void irrigationExist(){
        Map map = new Map();
        Irrigation irrigation = new Irrigation(new Position(0,1), new Position(1,1));

        map.putIrrigation(irrigation);
        assertFalse(map.irrigationExist(irrigation));

        map.putPlot(new Plot(PlotType.GREEN, new Position(0,1)));
        map.putPlot(new Plot(PlotType.GREEN, new Position(1,1)));

        map.putIrrigation(irrigation);
        assertTrue(map.irrigationExist(irrigation));

    }

}