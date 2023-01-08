package fr.cotedazur.univ.polytech.startingpoint.objective;

import fr.cotedazur.univ.polytech.startingpoint.*;

import java.util.ArrayList;


public class ObjectivePlots extends Objective{

    Pattern pattern;

    public ObjectivePlots(int point, ArrayList<Plot> plots) {
        super(point);
        pattern = new Pattern(plots);

    }
    public ObjectivePlots(int point, Pattern pattern) {
        super(point);
        this.pattern = pattern;

    }

    @Override
    public boolean verifyPlotObj(GameEngine gameEngine, Plot lastPlacedPlot) {
        return gameEngine.computeObjectivePlot(pattern, lastPlacedPlot);
    }

    @Override
    public boolean verifyGardenerObj(GameEngine gameEngine) {
        return false;
    }

    @Override
    public boolean verifyPandaObj(GameEngine gameEngine, BotProfil botProfil) {
        return false;
    }

    @Override
    public String toString() {
        return "ObjectivePlots{" +
                "points=" + _points +
                "pattern=" + pattern +
                '}';
    }
}
