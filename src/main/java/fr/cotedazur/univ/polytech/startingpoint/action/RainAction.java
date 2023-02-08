package fr.cotedazur.univ.polytech.startingpoint.action;

import fr.cotedazur.univ.polytech.startingpoint.*;
import fr.cotedazur.univ.polytech.startingpoint.game.Game;
import fr.cotedazur.univ.polytech.startingpoint.game.Referee;

public class RainAction implements Action{
    Position _position;
    public RainAction(Position position){
        this._position = position;
    }

    @Override
    public boolean play(Referee referee, GameEngine gameEngine) {
        return gameEngine.rainAction(_position);
    }
    @Override
    public boolean verifyObjectiveAfterAction(Referee referee){
        return referee.computeObjectivesGardener();
    }

    public boolean equals(Action action) {
        return isActionRain();
    }

    @Override
    public String toString() {
        return "RainAction{" +
                "_position=" + _position +
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
    public Position getPosition() {
        return _position;
    }

    @Override
    public ActionType toType() {
        return ActionType.RAIN;
    }
}
