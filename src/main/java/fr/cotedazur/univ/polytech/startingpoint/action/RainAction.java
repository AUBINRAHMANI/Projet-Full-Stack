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

    @Override
    public String toString() {
        return "RainAction{" +
                "_position=" + _position +
                '}';
    }
}
