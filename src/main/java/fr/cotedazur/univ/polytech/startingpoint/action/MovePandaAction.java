package fr.cotedazur.univ.polytech.startingpoint.action;

import fr.cotedazur.univ.polytech.startingpoint.GameEngine;
import fr.cotedazur.univ.polytech.startingpoint.Map;
import fr.cotedazur.univ.polytech.startingpoint.Position;
import fr.cotedazur.univ.polytech.startingpoint.bot.Playable;
import fr.cotedazur.univ.polytech.startingpoint.game.Referee;
import fr.cotedazur.univ.polytech.startingpoint.statistique_manager.StatisticManager;

import java.util.Objects;

public class MovePandaAction implements Action {

    Playable bot;
    Position position;

    public MovePandaAction(Playable bot, Position position) {
        this.bot = bot;
        this.position = position;
    }

    @Override
    public boolean play(Referee referee, GameEngine gameEngine) {
        return gameEngine.movePanda(referee, bot, position);
    }

    @Override
    public boolean verifyObjectiveAfterAction(Referee referee, Map map) {

        return referee.computeObjectivesPanda();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        return ActionType.MOVE_PANDA.equals(toType());
    }

    @Override
    public int hashCode() {
        return Objects.hash(toType());
    }

    @Override
    public Position getPosition() {
        return position;
    }

    @Override
    public ActionType toType() {
        return ActionType.MOVE_PANDA;
    }

    @Override
    public String toString() {
        return "MovePandaAction{" +
                "_position=" + position +
                '}';
    }

    public void incrementAction(StatisticManager statistiqueManager, Playable bot) {
        statistiqueManager.incrementPandaAction(bot);
    }
}
