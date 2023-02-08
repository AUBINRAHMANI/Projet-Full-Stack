package fr.cotedazur.univ.polytech.startingpoint.action;

import fr.cotedazur.univ.polytech.startingpoint.GameEngine;
import fr.cotedazur.univ.polytech.startingpoint.Plot;
import fr.cotedazur.univ.polytech.startingpoint.Position;
import fr.cotedazur.univ.polytech.startingpoint.game.Referee;

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
    public boolean verifyObjectiveAfterAction(Referee referee) {
        return referee.computeObjectivesPlot(plot);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        return isActionPutPlot();
    }

    @Override
    public int hashCode() {
        return Objects.hash(isActionPutPlot());
    }

    @Override
    public String toString() {
        return "PutPlotAction{" +
                "_plot=" + plot +
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
        return true;
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

    @Override
    public boolean isActionPutIrrigation() {
        return false;
    }

    @Override
    public Position getPosition() {
        return plot.getPosition();
    }

    @Override
    public ActionType toType() {
        return ActionType.PUT_PLOT;
    }
}
