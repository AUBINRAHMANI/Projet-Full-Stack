package fr.cotedazur.univ.polytech.startingpoint.Action;
import fr.cotedazur.univ.polytech.startingpoint.*;

public class MoveGardenerAction implements Action{

    Position _position;
    public MoveGardenerAction(Position position){
        _position = position;
    }

    public boolean play(GameEngine gameEngine){
        return gameEngine.moveGardener(_position);
    }

    public boolean verifyObjectiveAfterAction(Game game){
        return game.computeObjectivesGardener();
    }
}
