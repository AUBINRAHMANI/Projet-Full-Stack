package fr.cotedazur.univ.polytech.startingpoint.Action;
import fr.cotedazur.univ.polytech.startingpoint.*;

public class MoveGardenerAction implements Action{

    Position _position;
    public MoveGardenerAction(Position position){
        _position = position;
    }

    @Override
    public boolean play(Game game, GameEngine gameEngine) {
        return gameEngine.moveGardener(_position);
    }
    @Override
    public boolean verifyObjectiveAfterAction(Game game){
        return game.computeObjectivesGardener();
    }

    @Override
    public String toString() {
        return "MoveGardenerAction{" +
                "_position=" + _position +
                '}';
    }
}
