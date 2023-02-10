package fr.cotedazur.univ.polytech.startingpoint.game.game_engine.map;

import fr.cotedazur.univ.polytech.startingpoint.game.game_engine.map.Plot;
import fr.cotedazur.univ.polytech.startingpoint.game.game_engine.map.PlotType;
import fr.cotedazur.univ.polytech.startingpoint.game.game_engine.map.Position;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PlotTest {
    @Test
    void getTypeTest() {
        Position position = new Position(2, 2);
        Plot plot = new Plot(PlotType.GREEN, position);
        assertEquals(PlotType.GREEN, plot.getType());
    }

    @Test
    void getPosition() {
        Position position = new Position(2, 3);
        Plot plot = new Plot(PlotType.GREEN, new Position(2, 3));
        assertEquals(position, plot.getPosition());
    }

    @Test
    void getNumberOfBambooTest() {
        Plot plot = new Plot(PlotType.GREEN, new Position(2, 3));
        plot.isIrrigatedIsTrue();
        assertEquals(0, plot.getNumberOfBamboo());
        plot.growBamboo();
        assertEquals(1, plot.getNumberOfBamboo());
        plot.growBamboo();
        assertEquals(2, plot.getNumberOfBamboo());
    }

    @Test
    void checkIfGrowBambooWorksTest() {
        Plot plot = new Plot(PlotType.GREEN, new Position(2, 3));
        plot.isIrrigatedIsTrue();
        plot.growBamboo();
        assertEquals(1, plot.getNumberOfBamboo());
    }

    @Test
    void eatBambooTest() {
        Plot plot = new Plot(PlotType.GREEN, new Position(2, 3));
        plot.isIrrigatedIsTrue();
        assertEquals(0, plot.getNumberOfBamboo());
        plot.growBamboo();
        assertEquals(1, plot.getNumberOfBamboo());
        plot.eatBamboo();
        assertEquals(0, plot.getNumberOfBamboo());
    }

}
