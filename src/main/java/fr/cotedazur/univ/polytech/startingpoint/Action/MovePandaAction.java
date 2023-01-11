package fr.cotedazur.univ.polytech.startingpoint.Action;
import fr.cotedazur.univ.polytech.startingpoint.Game;
import fr.cotedazur.univ.polytech.startingpoint.GameEngine;
import fr.cotedazur.univ.polytech.startingpoint.Position;

public class MovePandaAction implements Action{

    Position _position;
    public MovePandaAction(Position position){
        _position = position;
    }

    @Override
    public boolean play(Game game, GameEngine gameEngine) {
        return gameEngine.movePanda(_position);
    }

    @Override
    public boolean verifyObjectiveAfterAction(Game game){

        return game.computeObjectivesPanda();
    }

    @Override
    public String toString() {
        return "MovePandaAction{" +
                "_position=" + _position +
                '}';
    }
}
