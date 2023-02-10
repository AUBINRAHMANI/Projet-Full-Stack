package fr.cotedazur.univ.polytech.startingpoint.bots.tools;

import fr.cotedazur.univ.polytech.startingpoint.game.action.Action;
import fr.cotedazur.univ.polytech.startingpoint.game.action.ActionType;
import fr.cotedazur.univ.polytech.startingpoint.game.Game;
import fr.cotedazur.univ.polytech.startingpoint.game.Referee;
import fr.cotedazur.univ.polytech.startingpoint.game.game_engine.map.*;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

class PatternBotResolverTest {

    @Mock
    Referee mockedReferee = spy(new Game(null, List.of()));
    Map mockedMap = spy(new Map());

    @Test
    void fillObjectivePlots() {
        PatternBotResolver patternBotResolver = new PatternBotResolver(mockedMap, mockedReferee);

        ArrayList<Plot> plots = new ArrayList<>();
        plots.add(new Plot(PlotType.GREEN, new Position(0, 0)));
        plots.add(new Plot(PlotType.GREEN, new Position(1, 1)));
        plots.add(new Plot(PlotType.GREEN, new Position(2, 1)));
        plots.add(new Plot(PlotType.GREEN, new Position(3, 2)));
        Pattern pattern = new Pattern(plots);

        Action result = patternBotResolver.fillObjectivePlots(pattern, List.of());
        assertEquals(ActionType.PUT_PLOT, result.toType());
        assertEquals(1, result.getPosition().getDistanceToPosition(new Position(0, 0)));
        mockedMap.putPlot(new Plot(PlotType.GREEN, result.getPosition()));

        result = patternBotResolver.fillObjectivePlots(pattern, List.of());
        assertEquals(ActionType.PUT_PLOT, result.toType());
        assertEquals(1, result.getPosition().getDistanceToPosition(new Position(0, 0)));
        mockedMap.putPlot(new Plot(PlotType.GREEN, result.getPosition()));

        result = patternBotResolver.fillObjectivePlots(pattern, List.of());
        mockedMap.putPlot(new Plot(PlotType.GREEN, result.getPosition()));


        result = patternBotResolver.fillObjectivePlots(pattern, List.of());
        assertEquals(ActionType.PUT_PLOT, result.toType());
        assertEquals(2, result.getPosition().getDistanceToPosition(new Position(0, 0)));
        mockedMap.putPlot(new Plot(PlotType.GREEN, result.getPosition()));

        result = patternBotResolver.fillObjectivePlots(pattern, List.of(ActionType.PUT_PLOT));
        assertEquals(ActionType.PUT_IRRIGATION, result.toType());
    }

    @Test
    void placePLot() {
        Plot plot = new Plot(PlotType.GREEN, null);
        when(mockedReferee.pickPlot()).thenReturn(Arrays.asList(plot, plot, plot));
        PatternBotResolver patternBotResolver = new PatternBotResolver(mockedMap, mockedReferee);

        Action result = patternBotResolver.placePLot(PlotType.GREEN, new Position(0, 1), List.of());
        assertEquals(ActionType.PUT_PLOT, result.toType());
        assertEquals(new Position(0, 1), result.getPosition());

        result = patternBotResolver.placePLot(PlotType.GREEN, new Position(5, 4), List.of());
        assertNotEquals(new Position(5, 4), result.getPosition());

        result = patternBotResolver.placePLot(null, new Position(0, 1), List.of());
        assertEquals(new Position(0, 1), result.getPosition());
    }

    @Test
    void putRandomlyAPLot() {
        Plot plot = new Plot(PlotType.GREEN, null);
        when(mockedReferee.pickPlot()).thenReturn(Arrays.asList(plot, plot, plot));
        PatternBotResolver patternBotResolver = new PatternBotResolver(mockedMap, mockedReferee);

        Action result = patternBotResolver.putRandomlyAPLot(PlotType.GREEN, List.of());
        assertEquals(ActionType.PUT_PLOT, result.toType());
    }
}