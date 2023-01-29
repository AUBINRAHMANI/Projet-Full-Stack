package fr.cotedazur.univ.polytech.startingpoint.action;
import fr.cotedazur.univ.polytech.startingpoint.*;
import fr.cotedazur.univ.polytech.startingpoint.game.Referee;

public class MoveGardenerAction implements Action{

    Position position;
    public MoveGardenerAction(Position position){
        this.position = position;
    }

    @Override
    public boolean play(Referee referee, GameEngine gameEngine) {
        return gameEngine.moveGardener(position);
    }
    @Override
    public boolean verifyObjectiveAfterAction(Referee referee){
        return referee.computeObjectivesGardener();
    }

    @Override
    public String toString() {
        return "MoveGardenerAction{" +
                "_position=" + position +
                '}';
    }
}
