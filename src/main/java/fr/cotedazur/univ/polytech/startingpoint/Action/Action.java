package fr.cotedazur.univ.polytech.startingpoint.Action;
import fr.cotedazur.univ.polytech.startingpoint.Game.Game;
import fr.cotedazur.univ.polytech.startingpoint.Game.Referee;
import fr.cotedazur.univ.polytech.startingpoint.GameEngine;

public interface Action {

    boolean play(Referee referee, GameEngine gameEngine);
    boolean verifyObjectiveAfterAction(Referee referee);
    String toString();
}
