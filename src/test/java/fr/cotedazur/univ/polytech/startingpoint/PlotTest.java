package fr.cotedazur.univ.polytech.startingpoint;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PlotTest {
    @Test
    void getTypeTest() {
        Plot plot = new Plot(PlotType.VERT);
        assertEquals(PlotType.VERT, plot.getType());
    }
}
