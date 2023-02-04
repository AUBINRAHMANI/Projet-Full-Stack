package fr.cotedazur.univ.polytech.startingpoint.action;

import fr.cotedazur.univ.polytech.startingpoint.GameEngine;
import fr.cotedazur.univ.polytech.startingpoint.Irrigation;
import fr.cotedazur.univ.polytech.startingpoint.game.Referee;

public class PutIrrigationAction implements Action {

    Irrigation irrigation;

    public PutIrrigationAction(Irrigation irrigation){
        this.irrigation = irrigation;
    }

    @Override
    public boolean play(Referee referee, GameEngine gameEngine) {
        return gameEngine.askToPutIrrigation(irrigation);
    }

    @Override
    public boolean verifyObjectiveAfterAction(Referee referee) {
        return false;
    }
}
