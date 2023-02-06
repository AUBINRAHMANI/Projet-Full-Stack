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

    @Override
    public boolean equals(Action action) {
        return false;
    }

    @Override
    public String toString() {
        return "PutIrrigationAction{" +
                "irrigation=" + irrigation +
                '}';
    }

    @Override
    public boolean isActionMoveGardener() {
        return false;
    }

    @Override
    public boolean isActionMovePanda() {
        return false;
    }

    @Override
    public boolean isActionPutPlot() {
        return false;
    }

    @Override
    public boolean isActionPickObjective() {
        return false;
    }

    @Override
    public boolean isActionRain() {
        return false;
    }

    @Override
    public boolean isActionThunder() {
        return false;
    }
}
