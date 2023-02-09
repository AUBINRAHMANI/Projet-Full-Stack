package fr.cotedazur.univ.polytech.startingpoint.action;

import fr.cotedazur.univ.polytech.startingpoint.GameEngine;
import fr.cotedazur.univ.polytech.startingpoint.Position;
import fr.cotedazur.univ.polytech.startingpoint.bot.Playable;
import fr.cotedazur.univ.polytech.startingpoint.game.Referee;
import fr.cotedazur.univ.polytech.startingpoint.statistique_manager.StatisticManager;

import java.util.Objects;

public class PickObjectiveAction implements Action {

    Playable bot;

    public PickObjectiveAction(Playable bot) {
        this.bot = bot;
    }

    @Override
    public boolean play(Referee referee, GameEngine gameEngine) {
        return referee.pickObjective(bot);
    }

    @Override
    public boolean verifyObjectiveAfterAction(Referee referee) {
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        return isActionPickObjective();
    }

    @Override
    public int hashCode() {
        return Objects.hash(isActionPickObjective());
    }

    @Override
    public String toString() {
        return "PickObjectiveAction{" +
                "bot=" + bot +
                '}';
    }

    @Override
    public boolean isActionMoveGardener() {
        return false;
    }

    @Override
    public boolean isActionMovePanda() {
        return false;
    }

    @Override
    public boolean isActionPutPlot() {
        return false;
    }

    @Override
    public boolean isActionPickObjective() {
        return true;
    }

    @Override
    public boolean isActionRain() {
        return false;
    }

    @Override
    public boolean isActionThunder() {
        return false;
    }

    @Override
    public Position getPosition() {
        return null;
    }

    @Override
    public ActionType toType() {
        return ActionType.PICK_OBJECTIVE;
    }

    public void incrementAction(StatisticManager statistiqueManager, Playable bot){
        statistiqueManager.incrementObjectiveAction(bot);
    }
}
