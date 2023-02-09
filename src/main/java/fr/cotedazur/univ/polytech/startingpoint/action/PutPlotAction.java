package fr.cotedazur.univ.polytech.startingpoint.action;

import fr.cotedazur.univ.polytech.startingpoint.GameEngine;
import fr.cotedazur.univ.polytech.startingpoint.Map;
import fr.cotedazur.univ.polytech.startingpoint.Plot;
import fr.cotedazur.univ.polytech.startingpoint.Position;
import fr.cotedazur.univ.polytech.startingpoint.bot.Playable;
import fr.cotedazur.univ.polytech.startingpoint.game.Referee;
import fr.cotedazur.univ.polytech.startingpoint.statistique_manager.StatisticManager;

import java.util.Objects;


public class PutPlotAction implements Action {

    Plot plot;

    public PutPlotAction(Plot plot) {
        this.plot = plot;
    }

    @Override
    public boolean play(Referee referee, GameEngine gameEngine) {
        return gameEngine.askToPutPlot(plot);
    }

    @Override
    public boolean verifyObjectiveAfterAction(Referee referee, Map map) {
        return referee.computeObjectivesPlot(plot);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        return ActionType.PUT_PLOT.equals(toType());
    }

    @Override
    public int hashCode() {
        return Objects.hash(toType());
    }

    @Override
    public String toString() {
        return "PutPlotAction{" +
                "_plot=" + plot +
                '}';
    }

    @Override
    public Position getPosition() {
        return plot.getPosition();
    }

    @Override
    public ActionType toType() {
        return ActionType.PUT_PLOT;
    }

    public void incrementAction(StatisticManager statistiqueManager, Playable bot){
        statistiqueManager.incrementPlotAction(bot);
    }
}
