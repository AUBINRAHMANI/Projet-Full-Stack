package fr.cotedazur.univ.polytech.startingpoint.action;

import fr.cotedazur.univ.polytech.startingpoint.Bot;
import fr.cotedazur.univ.polytech.startingpoint.game.Referee;
import fr.cotedazur.univ.polytech.startingpoint.GameEngine;

public class PickObjectiveAction implements Action{

    Bot bot;

    public PickObjectiveAction(Bot bot){
        this.bot = bot;
    }

    @Override
    public boolean play(Referee referee, GameEngine gameEngine) {
        return referee.pickObjective(bot);
    }

    @Override
    public boolean verifyObjectiveAfterAction(Referee referee) {
        return false;
    }

    @Override
    public boolean equals(Action action) {
        return isActionPickObjective();
    }

    @Override
    public String toString() {
        return "PickObjectiveAction{" +
                "bot=" + bot +
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
        return true;
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
