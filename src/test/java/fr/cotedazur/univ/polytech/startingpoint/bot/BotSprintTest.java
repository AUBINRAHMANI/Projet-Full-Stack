package fr.cotedazur.univ.polytech.startingpoint.bot;

import fr.cotedazur.univ.polytech.startingpoint.*;
import fr.cotedazur.univ.polytech.startingpoint.action.Action;
import fr.cotedazur.univ.polytech.startingpoint.action.ActionType;
import fr.cotedazur.univ.polytech.startingpoint.game.Game;
import fr.cotedazur.univ.polytech.startingpoint.game.Referee;
import fr.cotedazur.univ.polytech.startingpoint.objective.Objective;
import fr.cotedazur.univ.polytech.startingpoint.objective.ObjectivePanda;
import fr.cotedazur.univ.polytech.startingpoint.objective.ObjectivePlots;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class BotSprintTest {

    @Mock
    Referee mockedReferee = mock(Game.class);
    Map mockedMap = mock(Map.class);
    @Test
    void play() {
        BotSprint botSprint = new BotSprint();
        botSprint.setEnvirronement(mockedReferee, mockedMap);
        when(mockedReferee.getMyObjectives(botSprint)).thenReturn(List.of());
        Action resutl = botSprint.play(List.of(), WeatherType.QUESTIONMARK);
        assertEquals(ActionType.PICK_OBJECTIVE, resutl.toType());

        List<Objective> objectives = new ArrayList<>();
        Objective objective = new ObjectivePlots(4, new Pattern());
        for(int i=0; i<5 ;++i){
            objectives.add(objective);
        }
        mockedMap.putPlot(new Plot(PlotType.GREEN, new Position(1,1)));
        mockedMap.putPlot(new Plot(PlotType.GREEN, new Position(0,1)));

       // when(mockedReferee.getMyObjectives(botSprint)).thenReturn(objectives);
        //resutl = botSprint.play(List.of(), WeatherType.QUESTIONMARK);
        //assertEquals(ActionType.PUT_IRRIGATION, resutl.toType());
    }

    @Test
    void tryToFillObjective() {
    }

    @Test
    void fillObjectiveGardener() {
    }

    @Test
    void fillObjectivePanda() {
    }

    @Test
    void fillObjectivePlots() {
    }
}