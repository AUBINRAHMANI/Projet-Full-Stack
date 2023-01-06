package fr.cotedazur.univ.polytech.startingpoint;

import fr.cotedazur.univ.polytech.startingpoint.objective.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class GameEngineTest {


    @Test
    void pickObjectiveTest() {
        Deck<Objective> deck = new Deck<>();
        Objective objective = new ObjectivePlots(5, null);
        deck.addCard(objective);
        GameEngine gameEngine = new GameEngine(deck, null, null);

        assertEquals(objective , gameEngine.pickObjective());
    }

    @Test
    void pickPlotTest() {
        Position position = new Position(2,2);
        Deck<Plot> deck = new Deck<>();
        deck.addCard(new Plot(PlotType.GREEN,position));
        GameEngine gameEngine = new GameEngine(null, deck, null);

        assertEquals(PlotType.GREEN , gameEngine.pickPlot().getType());
    }

    @Test
    void askGetMapTest() {
        Map map = new Map();
        GameEngine gameEngine = new GameEngine(null, null, map);
        assertEquals(map, gameEngine.getMap());
    }


    @Test
    void movePandaTest(){
        Map map = new Map();
        GameEngine gameEngine = new GameEngine(null, null,map);
        Position position = new Position(1,0);
        Position position2 = new Position(372,1);

        Plot plot = new Plot(PlotType.GREEN, position);
        map.putPlot(plot);


        gameEngine.movePanda(position);
        assertEquals(true,gameEngine.movePanda(position));
        assertEquals(false, gameEngine.movePanda(position2));
    }




    /*
    @Test
    void computeObjectivePlotTest(ArrayList<Plot> pattern){
    }

    @Test
    void computeObjectiveGardenerTest(ArrayList<Plot> bambouPlots, boolean improvement){

    }

 */
}