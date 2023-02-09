package fr.cotedazur.univ.polytech.startingpoint.bot.tools;

import fr.cotedazur.univ.polytech.startingpoint.*;
import fr.cotedazur.univ.polytech.startingpoint.action.Action;
import fr.cotedazur.univ.polytech.startingpoint.action.ActionType;
import fr.cotedazur.univ.polytech.startingpoint.game.Game;
import fr.cotedazur.univ.polytech.startingpoint.game.Referee;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;

class GardenerBotResolverTest {

    @Mock
    Referee mockedReferee = spy(new Game(null, List.of()));
    Map mockedMap = spy(new Map());
    @Test
    void fillObjectiveGardener() {
        GardenerBotResolver gardenerBotResolver = new GardenerBotResolver(mockedMap, mockedReferee);
        Action result = gardenerBotResolver.fillObjectiveGardener(PlotType.GREEN, List.of(), null);
        assertEquals(ActionType.PUT_PLOT, result.toType());
        assertEquals(new Position(0,-1), result.getPosition());

        mockedMap.putPlot(new Plot(PlotType.YELLOW, new Position(1,1)));

        result = gardenerBotResolver.fillObjectiveGardener(PlotType.GREEN, List.of(), null);
        assertEquals(ActionType.PUT_PLOT, result.toType());
        assertEquals(new Position(0,-1), result.getPosition());

        mockedMap.putPlot(new Plot(PlotType.YELLOW, new Position(1,0)));
        Plot plot = new Plot(PlotType.GREEN, new Position(2,0));
        plot.isIrrigatedIsTrue();
        mockedMap.putPlot(plot);


        result = gardenerBotResolver.fillObjectiveGardener(PlotType.GREEN, List.of(), null);
        assertEquals(ActionType.MOVE_GARDENER, result.toType());
        assertEquals(1, result.getPosition().getDistanceToPosition(new Position(0,0)));


        mockedMap.putPlot(new Plot(PlotType.GREEN, new Position(0,1)));

        result = gardenerBotResolver.fillObjectiveGardener(PlotType.GREEN, List.of(), null);
        assertEquals(ActionType.MOVE_GARDENER, result.toType());
        assertEquals(new Position(0,1), result.getPosition());

        result = gardenerBotResolver.fillObjectiveGardener(PlotType.GREEN, Arrays.asList(ActionType.MOVE_GARDENER), null);
        assertEquals(ActionType.PUT_PLOT, result.toType());
        assertEquals(new Position(0,-1), result.getPosition());

        result = gardenerBotResolver.fillObjectiveGardener(PlotType.GREEN, Arrays.asList(ActionType.MOVE_GARDENER), WeatherType.RAIN);
        assertEquals(ActionType.RAIN, result.toType());
        assertEquals(new Position(2,0), result.getPosition());

    }
}