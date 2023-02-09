package fr.cotedazur.univ.polytech.startingpoint.action;

import fr.cotedazur.univ.polytech.startingpoint.GameEngine;
import fr.cotedazur.univ.polytech.startingpoint.Position;
import fr.cotedazur.univ.polytech.startingpoint.bot.Playable;
import fr.cotedazur.univ.polytech.startingpoint.game.Referee;
import fr.cotedazur.univ.polytech.startingpoint.statistique_manager.StatisticManager;

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
    void incrementAction(StatisticManager statisticManager, Playable bot);

    Position getPosition();

    ActionType toType();
}
