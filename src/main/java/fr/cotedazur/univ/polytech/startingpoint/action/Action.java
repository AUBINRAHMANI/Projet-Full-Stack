package fr.cotedazur.univ.polytech.startingpoint.action;

import fr.cotedazur.univ.polytech.startingpoint.GameEngine;
import fr.cotedazur.univ.polytech.startingpoint.Position;
import fr.cotedazur.univ.polytech.startingpoint.game.Referee;

public interface Action {

    boolean play(Referee referee, GameEngine gameEngine);

    boolean verifyObjectiveAfterAction(Referee referee);

    String toString();

    boolean isActionMoveGardener();

    boolean isActionMovePanda();

    boolean isActionPutPlot();

    boolean isActionPickObjective();

    boolean isActionRain();

    boolean isActionThunder();

    boolean isActionPutIrrigation();

    Position getPosition();

    ActionType toType();
}
