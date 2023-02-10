package fr.cotedazur.univ.polytech.startingpoint.game.objectives;

import fr.cotedazur.univ.polytech.startingpoint.game.game_engine.GameEngine;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ObjectiveGardenerTest {

    @Mock
    GameEngine mockedGameEngine = mock(GameEngine.class);

    @Test
    void verifyPlotObj() {
        assertFalse(new ObjectiveGardener(0, 0, null, false, 0).verifyPlotObj(null, null));
    }

    @Test
    void verifyGardenerObj() {
        when(mockedGameEngine.computeObjectiveGardener(0, null, 0)).thenReturn(true);
        ObjectiveGardener objectiveGardener = new ObjectiveGardener(0, 0, null, true, 0);
        assertTrue(objectiveGardener.verifyGardenerObj(mockedGameEngine));
    }

    @Test
    void verifyPandaObj() {
        assertFalse(new ObjectiveGardener(0, 0, null, false, 0).verifyPandaObj(null, null));
    }
}