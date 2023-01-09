package fr.cotedazur.univ.polytech.startingpoint.Action;

import fr.cotedazur.univ.polytech.startingpoint.Game;
import fr.cotedazur.univ.polytech.startingpoint.GameEngine;

public class pickObjectiveAction implements Action{

    @Override
    public boolean play(GameEngine gameEngine) {
        return false;
    }

    @Override
    public boolean verifyObjectiveAfterAction(Game game) {
        return false;
    }
}
