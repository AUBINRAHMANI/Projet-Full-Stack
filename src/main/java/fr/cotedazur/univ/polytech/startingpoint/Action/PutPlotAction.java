package fr.cotedazur.univ.polytech.startingpoint.Action;
import fr.cotedazur.univ.polytech.startingpoint.*;


public class PutPlotAction implements Action{

    Plot _plot;
    public PutPlotAction(Plot plot){
        _plot = plot;
    }

    @Override
    public boolean play(Game game, GameEngine gameEngine) {
        return gameEngine.askToPutPlot(_plot);
    }
    @Override
    public boolean verifyObjectiveAfterAction(Game game){
        return game.computeObjectivesPlot(_plot);
    }

    @Override
    public String toString() {
        return "PutPlotAction{" +
                "_plot=" + _plot +
                '}';
    }
}
