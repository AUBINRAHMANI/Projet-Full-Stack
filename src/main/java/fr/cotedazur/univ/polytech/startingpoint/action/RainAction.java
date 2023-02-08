package fr.cotedazur.univ.polytech.startingpoint.action;
import fr.cotedazur.univ.polytech.startingpoint.GameEngine;
import fr.cotedazur.univ.polytech.startingpoint.Position;
import fr.cotedazur.univ.polytech.startingpoint.game.Referee;

import java.util.Objects;

public class RainAction implements Action {
    Position position;

    public RainAction(Position position) {
        this.position = position;
    }

    @Override
    public boolean play(Referee referee, GameEngine gameEngine) {
        return gameEngine.rainAction(position);
    }

    @Override
    public boolean verifyObjectiveAfterAction(Referee referee) {
        return referee.computeObjectivesGardener();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        return isActionRain();
    }

    @Override
    public int hashCode() {
        return Objects.hash(isActionRain());
    }

    @Override
    public String toString() {
        return "RainAction{" +
                "_position=" + position +
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
        return false;
    }

    @Override
    public boolean isActionRain() {
        return true;
    }

    @Override
    public boolean isActionThunder() {
        return false;
    }

    @Override
    public boolean isActionPutIrrigation() {
        return false;
    }

    @Override
    public Position getPosition() {
        return position;
    }

    @Override
    public ActionType toType() {
        return ActionType.RAIN;
    }
}
