package fr.cotedazur.univ.polytech.startingpoint.bot.tools;

import fr.cotedazur.univ.polytech.startingpoint.*;
import fr.cotedazur.univ.polytech.startingpoint.action.Action;
import fr.cotedazur.univ.polytech.startingpoint.action.ActionType;
import fr.cotedazur.univ.polytech.startingpoint.action.MoveGardenerAction;
import fr.cotedazur.univ.polytech.startingpoint.bot.BotMbappe;
import fr.cotedazur.univ.polytech.startingpoint.bot.Playable;
import fr.cotedazur.univ.polytech.startingpoint.game.Game;
import fr.cotedazur.univ.polytech.startingpoint.game.Referee;
import fr.cotedazur.univ.polytech.startingpoint.objective.Objective;
import fr.cotedazur.univ.polytech.startingpoint.objective.ObjectivePanda;
import org.apache.commons.collections.map.ListOrderedMap;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.refEq;
import static org.mockito.Mockito.*;

class PandaBotResolverTest {

    @Mock
    Referee mockedReferee = spy(new Game());
    Map mockedMap = spy(new Map());
    Playable bot = spy(new BotMbappe());

    @Test
    void fillObjectivePanda() {
        Playable bot = new BotMbappe();
        PandaBotResolver pandaBotResolver = new PandaBotResolver(mockedMap, mockedReferee, bot);

        List<Bambou> bambousToHave = new ArrayList<>();
        bambousToHave.add(new Bambou(PlotType.GREEN));
        bambousToHave.add(new Bambou(PlotType.GREEN));

        Action result = pandaBotResolver.fillObjectivePanda( bambousToHave , Arrays.asList(new Bambou(PlotType.GREEN)),List.of(),null);
        assertEquals(ActionType.PUT_PLOT, result.toType());
        assertEquals(1, result.getPosition().getDistanceToPosition(new Position(0,0)));

        result = pandaBotResolver.fillObjectivePanda( bambousToHave , bambousToHave ,List.of(),null);
        assertEquals(ActionType.PUT_PLOT, result.toType());
        assertEquals(1, result.getPosition().getDistanceToPosition(new Position(0,0)));

        mockedMap.putPlot(new Plot(PlotType.GREEN, new Position(0,1) ));

        result = pandaBotResolver.fillObjectivePanda( bambousToHave , List.of() ,List.of(),null);
        assertEquals(ActionType.MOVE_GARDENER, result.toType());
        assertEquals(new Position(0,1), result.getPosition());

        mockedMap.getMapPlots().get(1).growBambou();

        result = pandaBotResolver.fillObjectivePanda( bambousToHave , List.of() ,List.of(),null);
        assertEquals(ActionType.MOVE_PANDA, result.toType());
        assertEquals(new Position(0,1), result.getPosition());
    }

    @Test
    void movePandaOnPlantation() {
        when(mockedReferee.getPandaPosition()).thenReturn(new Position(0,0));
        Playable bot = new BotMbappe();
        PandaBotResolver pandaBotResolver = new PandaBotResolver(mockedMap, mockedReferee, bot);
        Action result = pandaBotResolver.movePandaOnPlantation(new Position(2,1));
        assertEquals(ActionType.MOVE_PANDA, result.toType());
        assertEquals(new Position(2,1), result.getPosition());

        result = pandaBotResolver.movePandaOnPlantation(new Position(2,3));
        assertNull(result);
    }
}