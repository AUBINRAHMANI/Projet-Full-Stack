package fr.cotedazur.univ.polytech.startingpoint;

public class ActionGardener extends Action {
    private int nbStep;
    private Direction direction;

    public ActionGardener(int nbStep, Direction direction, ActionType actionType) {
        super(ActionType.GARDENER);
        this.nbStep = nbStep;
        this.direction = direction;
    }

    public Position getNewPosition(Position position) {

}
