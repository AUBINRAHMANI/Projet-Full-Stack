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
        switch (direction) {
            case NORTH_EAST:
                return new Position(position.getX_() + nbStep, position.getY_() + nbStep);
            case NORTH_WEST:
                return new Position(position.getX_() - nbStep, position.getY_() + nbStep);
            case SOUTH_EAST:
                return new Position(position.getX_() + nbStep, position.getY_() - nbStep);
            case SOUTH_WEST:
                return new Position(position.getX_() - nbStep, position.getY_() - nbStep);
            case EAST:
                return new Position(position.getX_() + nbStep, position.getY_());
            case WEST:
                return new Position(position.getX_() - nbStep, position.getY_());
        }
        return position;
    }
}
