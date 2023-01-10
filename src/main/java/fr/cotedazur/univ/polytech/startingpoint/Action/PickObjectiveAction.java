package fr.cotedazur.univ.polytech.startingpoint.Action;

import fr.cotedazur.univ.polytech.startingpoint.Bot;
import fr.cotedazur.univ.polytech.startingpoint.Game;
import fr.cotedazur.univ.polytech.startingpoint.GameEngine;

public class PickObjectiveAction implements Action{

    Bot bot;

    public PickObjectiveAction(Bot bot){
        this.bot = bot;
    }

    @Override
    public boolean play(Game game, GameEngine gameEngine) {
        return game.pickObjective(bot);
    }

    @Override
    public boolean verifyObjectiveAfterAction(Game game) {
        return false;
    }
}
