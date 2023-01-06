package fr.cotedazur.univ.polytech.startingpoint;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PlotTest {
    @Test
    void getTypeTest() {
        Position position = new Position(2,2);
        Plot plot = new Plot(PlotType.GREEN,position);
        assertEquals(PlotType.GREEN, plot.getType());
    }
    @Test
    public void getPosition() {
        Position position = new Position(2, 3);
        Plot plot = new Plot(PlotType.GREEN, new Position(2,3));
        assertEquals(position, plot.getPosition());
    }
    @Test
    void getNumberOfBambouTest(){
        Plot plot = new Plot(PlotType.GREEN, new Position(2,3));
        assertEquals(0,plot.getNumberOfBambou());
        plot.growBambou();
        assertEquals(1,plot.getNumberOfBambou());
        plot.growBambou();
        assertEquals(2,plot.getNumberOfBambou());
    }
    @Test
    void checkIfgrowBambouWorksTest(){
        Plot plot = new Plot(PlotType.GREEN, new Position(2,3));
        plot.growBambou();
        assertEquals(1,plot.getNumberOfBambou());
    }
    @Test
    void eatBambouTest(){
        Plot plot = new Plot(PlotType.GREEN, new Position(2,3));
        assertEquals(0,plot.getNumberOfBambou());
        plot.growBambou();
        assertEquals(1,plot.getNumberOfBambou());
        plot.eatBambou();
        assertEquals(0,plot.getNumberOfBambou());
    }

}
