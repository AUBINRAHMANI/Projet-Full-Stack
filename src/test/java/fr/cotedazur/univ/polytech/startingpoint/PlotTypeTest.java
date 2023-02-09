package fr.cotedazur.univ.polytech.startingpoint;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlotTypeTest {

    @Test
    void testOfPlotTypeEnumValues() {
        PlotType plotType = PlotType.POND;
        assertEquals(PlotType.valueOf("POND"), plotType);

        PlotType plotTypeRed = PlotType.RED;
        assertEquals(PlotType.valueOf("RED"), plotTypeRed);

        PlotType plotTypeGreen = PlotType.GREEN;
        assertEquals(PlotType.valueOf("GREEN"), plotTypeGreen);

        PlotType plotTypeYellow = PlotType.YELLOW;
        assertEquals(PlotType.valueOf("YELLOW"), plotTypeYellow);
    }
}
