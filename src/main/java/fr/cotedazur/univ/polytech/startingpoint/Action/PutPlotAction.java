package fr.cotedazur.univ.polytech.startingpoint.Action;
import fr.cotedazur.univ.polytech.startingpoint.*;
import fr.cotedazur.univ.polytech.startingpoint.Game.Game;
import fr.cotedazur.univ.polytech.startingpoint.Game.Referee;

import java.sql.Ref;


public class PutPlotAction implements Action{

    Plot _plot;
    public PutPlotAction(Plot plot){
        _plot = plot;
    }

    @Override
    public boolean play(Referee referee, GameEngine gameEngine) {
        return gameEngine.askToPutPlot(_plot);
    }
    @Override
    public boolean verifyObjectiveAfterAction(Referee referee){
        return referee.computeObjectivesPlot(_plot);
    }

    @Override
    public String toString() {
        return "PutPlotAction{" +
                "_plot=" + _plot +
                '}';
    }
}
