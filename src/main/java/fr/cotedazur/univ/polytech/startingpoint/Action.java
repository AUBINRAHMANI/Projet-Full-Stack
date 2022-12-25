package fr.cotedazur.univ.polytech.startingpoint;

public class Action {
    private ActionType _actionType;

    public Action(ActionType actionType) {
        _actionType = actionType;
    }

    public ActionType getActionType() {
        return _actionType;
    }
}
