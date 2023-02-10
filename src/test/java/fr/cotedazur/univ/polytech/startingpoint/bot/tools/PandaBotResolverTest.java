package fr.cotedazur.univ.polytech.startingpoint.bot.tools;

import fr.cotedazur.univ.polytech.startingpoint.action.Action;
import fr.cotedazur.univ.polytech.startingpoint.action.ActionType;
import fr.cotedazur.univ.polytech.startingpoint.bot.BotMbappe;
import fr.cotedazur.univ.polytech.startingpoint.bot.Playable;
import fr.cotedazur.univ.polytech.startingpoint.game.Game;
import fr.cotedazur.univ.polytech.startingpoint.game.Referee;
import fr.cotedazur.univ.polytech.startingpoint.game.game_engine.items.Bamboo;
import fr.cotedazur.univ.polytech.startingpoint.game.game_engine.map.Map;
import fr.cotedazur.univ.polytech.startingpoint.game.game_engine.map.Plot;
import fr.cotedazur.univ.polytech.startingpoint.game.game_engine.map.PlotType;
import fr.cotedazur.univ.polytech.startingpoint.game.game_engine.map.Position;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

class PandaBotResolverTest {

    @Mock
    Referee mockedReferee = spy(new Game(null, List.of()));
    Map mockedMap = spy(new Map());
    Playable bot = spy(new BotMbappe());

    @Test
    void fillObjectivePanda() {
        Playable bot = new BotMbappe();
        PandaBotResolver pandaBotResolver = new PandaBotResolver(mockedMap, mockedReferee, bot);

        List<Bamboo> bambousToHave = new ArrayList<>();
        bambousToHave.add(new Bamboo(PlotType.GREEN));
        bambousToHave.add(new Bamboo(PlotType.GREEN));

        Action result = pandaBotResolver.fillObjectivePanda(bambousToHave, List.of(new Bamboo(PlotType.GREEN)), List.of(), null);
        assertEquals(ActionType.PUT_PLOT, result.toType());
        assertEquals(1, result.getPosition().getDistanceToPosition(new Position(0, 0)));

        result = pandaBotResolver.fillObjectivePanda(bambousToHave, bambousToHave, List.of(), null);
        assertEquals(ActionType.PUT_PLOT, result.toType());
        assertEquals(1, result.getPosition().getDistanceToPosition(new Position(0, 0)));

        mockedMap.putPlot(new Plot(PlotType.GREEN, new Position(0, 1)));

        result = pandaBotResolver.fillObjectivePanda(bambousToHave, List.of(), List.of(), null);
        assertEquals(ActionType.MOVE_GARDENER, result.toType());
        assertEquals(new Position(0, 1), result.getPosition());

        mockedMap.getMapPlots().get(1).growBamboo();

        result = pandaBotResolver.fillObjectivePanda(bambousToHave, List.of(), List.of(), null);
        assertEquals(ActionType.MOVE_PANDA, result.toType());
        assertEquals(new Position(0, 1), result.getPosition());
    }

    @Test
    void movePandaOnPlantation() {
        when(mockedReferee.getPandaPosition()).thenReturn(new Position(0, 0));
        Playable bot = new BotMbappe();
        PandaBotResolver pandaBotResolver = new PandaBotResolver(mockedMap, mockedReferee, bot);
        Action result = pandaBotResolver.movePandaOnPlantation(new Position(2, 1));
        assertEquals(ActionType.MOVE_PANDA, result.toType());
        assertEquals(new Position(2, 1), result.getPosition());

        result = pandaBotResolver.movePandaOnPlantation(new Position(2, 3));
        assertNull(result);
    }
}