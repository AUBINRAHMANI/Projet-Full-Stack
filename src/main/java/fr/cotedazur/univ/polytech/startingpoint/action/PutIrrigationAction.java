package fr.cotedazur.univ.polytech.startingpoint.action;

import fr.cotedazur.univ.polytech.startingpoint.GameEngine;
import fr.cotedazur.univ.polytech.startingpoint.Irrigation;
import fr.cotedazur.univ.polytech.startingpoint.Map;
import fr.cotedazur.univ.polytech.startingpoint.Position;
import fr.cotedazur.univ.polytech.startingpoint.bot.Playable;
import fr.cotedazur.univ.polytech.startingpoint.game.Referee;
import fr.cotedazur.univ.polytech.startingpoint.statistique_manager.StatisticManager;

import java.util.Objects;

public class PutIrrigationAction implements Action {

    Irrigation irrigation;

    public PutIrrigationAction(Irrigation irrigation) {
        this.irrigation = irrigation;
    }

    @Override
    public boolean play(Referee referee, GameEngine gameEngine) {
        return gameEngine.askToPutIrrigation(irrigation);
    }

    @Override
    public boolean verifyObjectiveAfterAction(Referee referee, Map map) {
        referee.computeObjectivesPlot(map.findPlot(irrigation.getPositions().get(0)));
        return referee.computeObjectivesPlot(map.findPlot(irrigation.getPositions().get(1)));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        return ActionType.PUT_IRRIGATION.equals(toType());
    }

    @Override
    public int hashCode() {
        return Objects.hash(toType());
    }

    @Override
    public String toString() {
        return "PutIrrigationAction{" +
                "irrigation=" + irrigation +
                '}';
    }

    @Override
    public Position getPosition() {
        return null;
    }

    @Override
    public ActionType toType() {
        return ActionType.PUT_IRRIGATION;
    }

    public void incrementAction(StatisticManager statistiqueManager, Playable bot) {
        statistiqueManager.incrementIrrigationAction(bot);
    }
}
