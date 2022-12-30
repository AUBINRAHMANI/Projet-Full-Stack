package fr.cotedazur.univ.polytech.startingpoint.Action;
import fr.cotedazur.univ.polytech.startingpoint.Game;
import fr.cotedazur.univ.polytech.startingpoint.GameEngine;
import fr.cotedazur.univ.polytech.startingpoint.Position;

public class MovePandaAction implements Action{

    Position _position;
    public MovePandaAction(Position position){
        _position = position;
    }

    public boolean play(GameEngine gameEngine){

        return gameEngine.movePanda(_position);
    }

    public boolean verifyObjectiveAfterAction(Game game){

        return game.computeObjectivesPanda();
    }
}
