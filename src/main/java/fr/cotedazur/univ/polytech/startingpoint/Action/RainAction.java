package fr.cotedazur.univ.polytech.startingpoint.Action;

import fr.cotedazur.univ.polytech.startingpoint.*;


public class RainAction implements Action{
    Position _position;
    public RainAction(Position position){
        _position = position;
    }

    @Override
    public boolean play(Game game, GameEngine gameEngine) {
        return gameEngine.rainAction(_position);
    }
    @Override
    public boolean verifyObjectiveAfterAction(Game game){
        return game.computeObjectivesGardener();
    }

    @Override
    public String toString() {
        return "RainAction{" +
                "_position=" + _position +
                '}';
    }
}
