package fr.cotedazur.univ.polytech.startingpoint.game.action;

import fr.cotedazur.univ.polytech.startingpoint.game.game_engine.GameEngine;
import fr.cotedazur.univ.polytech.startingpoint.game.game_engine.map.Map;
import fr.cotedazur.univ.polytech.startingpoint.game.game_engine.map.Position;
import fr.cotedazur.univ.polytech.startingpoint.bots.Playable;
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
    public boolean verifyObjectiveAfterAction(Referee referee, Map map) {
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        return ActionType.PICK_OBJECTIVE.equals(toType());
    }

    @Override
    public int hashCode() {
        return Objects.hash(toType());
    }

    @Override
    public String toString() {
        return "PickObjectiveAction{" +
                "bot=" + bot +
                '}';
    }

    @Override
    public Position getPosition() {
        return null;
    }

    @Override
    public ActionType toType() {
        return ActionType.PICK_OBJECTIVE;
    }

    public void incrementAction(StatisticManager statisticManager, Playable bot) {
        statisticManager.incrementObjectiveAction(bot);
    }
}
