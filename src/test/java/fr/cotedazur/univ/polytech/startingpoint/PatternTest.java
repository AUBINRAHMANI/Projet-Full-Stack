package fr.cotedazur.univ.polytech.startingpoint;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PatternTest {

    @Test
    void rotate60Right() {

        ArrayList<Plot> plots1 = new ArrayList<>();
        ArrayList<Plot> plots2 = new ArrayList<>();

        plots1.add(new Plot(PlotType.GREEN, new Position(0, 0)));
        plots1.add(new Plot(PlotType.GREEN, new Position(1, 1)));
        plots1.add(new Plot(PlotType.GREEN, new Position(1, 2)));
        plots1.add(new Plot(PlotType.GREEN, new Position(2, 2)));

        plots2.add(new Plot(PlotType.GREEN, new Position(0, 0)));
        plots2.add(new Plot(PlotType.GREEN, new Position(1, 0)));
        plots2.add(new Plot(PlotType.GREEN, new Position(2, 0)));
        plots2.add(new Plot(PlotType.GREEN, new Position(3, 0)));

        Pattern pattern1 = new Pattern(plots1);
        Pattern pattern2 = new Pattern(plots2);

        pattern1.rotate60Right();

        assertEquals(pattern2, pattern1);

    }

    @Test
    void translateLeft() {
        ArrayList<Plot> plots1 = new ArrayList<>();
        ArrayList<Plot> plots2 = new ArrayList<>();

        plots1.add(new Plot(PlotType.GREEN, new Position(1, 0)));
        plots1.add(new Plot(PlotType.GREEN, new Position(2, 0)));
        plots1.add(new Plot(PlotType.GREEN, new Position(2, 1)));
        plots1.add(new Plot(PlotType.GREEN, new Position(3, 2)));

        plots2.add(new Plot(PlotType.GREEN, new Position(0, 0)));
        plots2.add(new Plot(PlotType.GREEN, new Position(1, 1)));
        plots2.add(new Plot(PlotType.GREEN, new Position(1, 2)));
        plots2.add(new Plot(PlotType.GREEN, new Position(2, 2)));

        Pattern pattern1 = new Pattern(plots1);
        Pattern pattern2 = new Pattern(plots2);

        pattern1.translateLeft();

        assertEquals(pattern2, pattern1);
    }

    @Test
    void translateUp() {
        ArrayList<Plot> plots1 = new ArrayList<>();
        ArrayList<Plot> plots2 = new ArrayList<>();

        plots1.add(new Plot(PlotType.GREEN, new Position(0, 0)));
        plots1.add(new Plot(PlotType.GREEN, new Position(1, 1)));
        plots1.add(new Plot(PlotType.GREEN, new Position(1, 2)));
        plots1.add(new Plot(PlotType.GREEN, new Position(2, 2)));

        plots2.add(new Plot(PlotType.GREEN, new Position(0, 1)));
        plots2.add(new Plot(PlotType.GREEN, new Position(1, 2)));
        plots2.add(new Plot(PlotType.GREEN, new Position(1, 3)));
        plots2.add(new Plot(PlotType.GREEN, new Position(2, 3)));

        Pattern pattern1 = new Pattern(plots1);
        Pattern pattern2 = new Pattern(plots2);

        pattern1.translateUp();

        assertEquals(pattern2, pattern1);
    }

    @Test
    void translateDown() {
        ArrayList<Plot> plots1 = new ArrayList<>();
        ArrayList<Plot> plots2 = new ArrayList<>();

        plots1.add(new Plot(PlotType.GREEN, new Position(0, 1)));
        plots1.add(new Plot(PlotType.GREEN, new Position(1, 2)));
        plots1.add(new Plot(PlotType.GREEN, new Position(1, 3)));
        plots1.add(new Plot(PlotType.GREEN, new Position(2, 3)));

        plots2.add(new Plot(PlotType.GREEN, new Position(0, 0)));
        plots2.add(new Plot(PlotType.GREEN, new Position(1, 1)));
        plots2.add(new Plot(PlotType.GREEN, new Position(1, 2)));
        plots2.add(new Plot(PlotType.GREEN, new Position(2, 2)));

        Pattern pattern1 = new Pattern(plots1);
        Pattern pattern2 = new Pattern(plots2);

        pattern1.translateDown();

        assertEquals(pattern2, pattern1);
    }

    @Test
    void applyMask() {
        ArrayList<Plot> plots = new ArrayList<>();
        plots.add(new Plot(PlotType.GREEN, new Position(-1, 1)));
        plots.add(new Plot(PlotType.GREEN, new Position(0, 0)));
        plots.add(new Plot(PlotType.GREEN, new Position(1, 0)));
        plots.add(new Plot(PlotType.GREEN, new Position(1, 1)));
        Pattern pattern = new Pattern(plots);

        ArrayList<Plot> expected = new ArrayList<>();
        expected.add(new Plot(PlotType.GREEN, new Position(3, 4)));
        expected.add(new Plot(PlotType.GREEN, new Position(4, 3)));
        expected.add(new Plot(PlotType.GREEN, new Position(5, 3)));
        expected.add(new Plot(PlotType.GREEN, new Position(5, 4)));
        pattern.applyMask(new Position(4, 3));
        assertEquals(expected, pattern.getPlots());
    }

    @Test
    void setAncerPoint() {
        ArrayList<Plot> plots = new ArrayList<>();
        plots.add(new Plot(PlotType.GREEN, new Position(-1, 1)));
        plots.add(new Plot(PlotType.GREEN, new Position(0, 0)));
        plots.add(new Plot(PlotType.GREEN, new Position(1, 0)));
        plots.add(new Plot(PlotType.GREEN, new Position(1, 1)));

        Pattern pattern = new Pattern(plots);

        ArrayList<Plot> expected1 = new ArrayList<>();
        expected1.add(new Plot(PlotType.GREEN, new Position(0, 0)));
        expected1.add(new Plot(PlotType.GREEN, new Position(1, 0)));
        expected1.add(new Plot(PlotType.GREEN, new Position(2, -1)));
        expected1.add(new Plot(PlotType.GREEN, new Position(2, 0)));

        ArrayList<Plot> expected2 = new ArrayList<>();
        expected2.add(new Plot(PlotType.GREEN, new Position(-2, 1)));
        expected2.add(new Plot(PlotType.GREEN, new Position(-1, 1)));
        expected2.add(new Plot(PlotType.GREEN, new Position(0, 0)));
        expected2.add(new Plot(PlotType.GREEN, new Position(0, 1)));

        pattern.setAncerPoint(new Position(-1, 1));
        assertEquals(expected1, pattern.getPlots());

        pattern.setAncerPoint(new Position(2, -1));
        assertEquals(expected2, pattern.getPlots());


    }

}