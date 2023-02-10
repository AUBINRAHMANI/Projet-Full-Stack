package fr.cotedazur.univ.polytech.startingpoint.game.game_engine.items;

import fr.cotedazur.univ.polytech.startingpoint.game.game_engine.items.Bamboo;
import fr.cotedazur.univ.polytech.startingpoint.game.game_engine.map.PlotType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BambooTest {

    @Test
    void getBambooType() {
        Bamboo bamboo = new Bamboo(PlotType.RED);
        assertEquals(PlotType.RED, bamboo.getBambooType());
    }

    @Test
    void bambooEqualsTest() {
        Bamboo bamboo = new Bamboo(PlotType.RED);
        assertEquals(PlotType.RED, bamboo.getBambooType());
    }
}