package fr.cotedazur.univ.polytech.startingpoint.objective;

import fr.cotedazur.univ.polytech.startingpoint.*;

import java.util.ArrayList;


public class ObjectivePlots extends Objective{

    ArrayList<Plot> _pattern;

    public ObjectivePlots(int point, ArrayList<Plot> pattern) {
        super(point);
        _pattern = pattern;

    }

    @Override
    public boolean verifyPlotObj(GameEngine gameEngine) {
        return gameEngine.computeObjectivePlot(_pattern);
    }

    @Override
    public boolean verifyGardenerObj(GameEngine gameEngine) {
        return false;
    }

    @Override
    public boolean verifyPandaObj(GameEngine gameEngine) {
        return false;
    }
}
