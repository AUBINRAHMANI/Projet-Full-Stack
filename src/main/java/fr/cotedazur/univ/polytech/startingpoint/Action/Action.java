package fr.cotedazur.univ.polytech.startingpoint.Action;
import fr.cotedazur.univ.polytech.startingpoint.Game;
import fr.cotedazur.univ.polytech.startingpoint.GameEngine;

public interface Action {
    boolean play(GameEngine gameEngine);
    boolean verifyObjectiveAfterAction(Game game);
}
