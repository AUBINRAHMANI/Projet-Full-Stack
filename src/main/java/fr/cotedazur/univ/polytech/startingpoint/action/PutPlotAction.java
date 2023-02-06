package fr.cotedazur.univ.polytech.startingpoint.action;
import fr.cotedazur.univ.polytech.startingpoint.*;
import fr.cotedazur.univ.polytech.startingpoint.game.Referee;


public class PutPlotAction implements Action{

    Plot plot;
    public PutPlotAction(Plot plot){
        this.plot = plot;
    }

    @Override
    public boolean play(Referee referee, GameEngine gameEngine) {return gameEngine.askToPutPlot(plot);}
    @Override
    public boolean verifyObjectiveAfterAction(Referee referee){
        return referee.computeObjectivesPlot(plot);
    }

    @Override
    public String toString() {
        return "PutPlotAction{" +
                "_plot=" + plot +
                '}';
    }
}
