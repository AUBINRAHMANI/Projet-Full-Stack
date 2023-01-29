package fr.cotedazur.univ.polytech.startingpoint.Action;
import fr.cotedazur.univ.polytech.startingpoint.*;
import fr.cotedazur.univ.polytech.startingpoint.Game.Game;
import fr.cotedazur.univ.polytech.startingpoint.Game.Referee;

public class MoveGardenerAction implements Action{

    Position _position;
    public MoveGardenerAction(Position position){
        _position = position;
    }

    @Override
    public boolean play(Referee referee, GameEngine gameEngine) {
        return gameEngine.moveGardener(_position);
    }
    @Override
    public boolean verifyObjectiveAfterAction(Referee referee){
        return referee.computeObjectivesGardener();
    }

    @Override
    public String toString() {
        return "MoveGardenerAction{" +
                "_position=" + _position +
                '}';
    }
}
