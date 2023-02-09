package fr.cotedazur.univ.polytech.startingpoint.bot.tools;

import fr.cotedazur.univ.polytech.startingpoint.Map;
import fr.cotedazur.univ.polytech.startingpoint.action.Action;
import fr.cotedazur.univ.polytech.startingpoint.action.ActionType;
import fr.cotedazur.univ.polytech.startingpoint.game.Game;
import fr.cotedazur.univ.polytech.startingpoint.game.Referee;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class GardenerBotResolverTest {

    @Mock
    Referee mockedReferee = mock(Game.class);
    Map mockedMap = mock(Map.class);
    @Test
    void fillObjectiveGardener() {
        GardenerBotResolver gardenerBotResolver = new GardenerBotResolver(mockedMap, mockedReferee);
        Action result = gardenerBotResolver.fillObjectiveGardener(null, List.of(), null);
        //assertEquals(ActionType.PUT_PLOT, result.toType());
    }
}