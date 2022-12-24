package fr.cotedazur.univ.polytech.startingpoint;

import fr.cotedazur.univ.polytech.startingpoint.objective.Objective;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GameEngineTest {


    @Test
    void pickObjectiveTest() {
        Deck<Objective> deck = new Deck<>();
        Objective objective = new Objective(5, ObjectiveType.PLOTS);
        deck.addCard(objective);
        GameEngine gameEngine = new GameEngine(deck, null, null);

        assertEquals(objective , gameEngine.pickObjective());
    }

    @Test
    void pickPlotTest() {
        Deck<Plot> deck = new Deck<>();
        deck.addCard(new Plot(PlotType.GREEN));
        GameEngine gameEngine = new GameEngine(null, deck, null);

        assertEquals(PlotType.GREEN , gameEngine.pickPlot().getType());
    }

    @Test
    void askGetMapTest() {
        Map map = new Map();
        GameEngine gameEngine = new GameEngine(null, null, map);
        assertEquals(map, gameEngine.getMap());
    }
}