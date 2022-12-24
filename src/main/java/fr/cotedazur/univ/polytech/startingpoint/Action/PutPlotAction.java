package fr.cotedazur.univ.polytech.startingpoint.Action;
import fr.cotedazur.univ.polytech.startingpoint.*;


public class PutPlotAction implements Action{

    Plot _plot;
    public PutPlotAction(Plot plot){
        _plot = plot;
    }

    public boolean play(GameEngine gameEngine){
        return gameEngine.askToPutPlot(_plot);
    }

    public boolean verifyObjectiveAfterAction(Game game){
        return game.computeObjectivesPlot();
    }
}
