package fr.cotedazur.univ.polytech.startingpoint.action;

import fr.cotedazur.univ.polytech.startingpoint.Bot;
import fr.cotedazur.univ.polytech.startingpoint.GameEngine;
import fr.cotedazur.univ.polytech.startingpoint.Position;
import fr.cotedazur.univ.polytech.startingpoint.game.Referee;

public class ThunderAction implements Action{
    Bot bot;
    Position position;
    public ThunderAction(Bot bot, Position position){
        this.bot = bot;
        this.position = position;
    }

    @Override
    public boolean play(Referee referee, GameEngine gameEngine) {
        return gameEngine.thunderAction(position);
    }

    @Override
    public boolean verifyObjectiveAfterAction(Referee referee){
        return referee.computeObjectivesPanda();
    }

    @Override
    public boolean equals(Action action) {
        return isActionThunder();
    }

    @Override
    public String toString() {
        return "ThunderAction{" +
                "_position=" + position +
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
        return true;
    }
}
