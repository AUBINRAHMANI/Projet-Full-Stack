package fr.cotedazur.univ.polytech.startingpoint;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PlotTest {
    @Test
    void getTypeTest() {
        Plot plot = new Plot(PlotType.GREEN);
        assertEquals(PlotType.GREEN, plot.getType());
    }
    @Test
    public void getPosition() {
        Position position = new Position(2, 3);
        Plot plot = new Plot(PlotType.GREEN, new Position(2,3));
        assertEquals(position, plot.getPosition());
    }
}
