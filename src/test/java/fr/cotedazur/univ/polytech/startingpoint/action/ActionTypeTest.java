package fr.cotedazur.univ.polytech.startingpoint.action;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ActionTypeTest {

    @Test
    void testOfActionTypeEnumValues() {
        ActionType actionTypeMoveGardener = ActionType.MOVE_GARDENER;
        assertEquals(ActionType.valueOf("MOVE_GARDENER"), actionTypeMoveGardener);

        ActionType actionTypeMovePanda = ActionType.MOVE_PANDA;
        assertEquals(ActionType.valueOf("MOVE_PANDA"), actionTypeMovePanda);

        ActionType actionTypePickObjective = ActionType.PICK_OBJECTIVE;
        assertEquals(ActionType.valueOf("PICK_OBJECTIVE"), actionTypePickObjective);

        ActionType actionTypePutIrrigation = ActionType.PUT_IRRIGATION;
        assertEquals(ActionType.valueOf("PUT_IRRIGATION"), actionTypePutIrrigation);

        ActionType actionTypePutPlot = ActionType.PUT_PLOT;
        assertEquals(ActionType.valueOf("PUT_PLOT"), actionTypePutPlot);

        ActionType actionTypeRain = ActionType.RAIN;
        assertEquals(ActionType.valueOf("RAIN"), actionTypeRain);

        ActionType actionTypeThunder = ActionType.THUNDER;
        assertEquals(ActionType.valueOf("THUNDER"), actionTypeThunder);
    }
}