package fr.cotedazur.univ.polytech.startingpoint;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PlotTest {
    @Test
<<<<<<< HEAD:src/test/java/fr/cotedazur/univ/polytech/startingpoint/MapTest.java
    void isIrrigated() {

=======
    void getTypeTest() {
        Plot plot = new Plot(PlotType.VERT);
        assertEquals(PlotType.VERT, plot.getType());
>>>>>>> develop:src/test/java/fr/cotedazur/univ/polytech/startingpoint/PlotTest.java
    }
}
