package fr.cotedazur.univ.polytech.startingpoint.action;

import fr.cotedazur.univ.polytech.startingpoint.bot.Bot;
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
    public String toString() {
        return "PickObjectiveAction{" +
                "bot=" + bot +
                '}';
    }
}
