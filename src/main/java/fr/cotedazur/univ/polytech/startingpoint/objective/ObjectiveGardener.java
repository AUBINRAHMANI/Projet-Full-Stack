package fr.cotedazur.univ.polytech.startingpoint.objective;

import fr.cotedazur.univ.polytech.startingpoint.*;

import java.util.ArrayList;

public class ObjectiveGardener extends Objective{

    ArrayList<Plot> _bambouPlots;
    boolean _improvement;

    public ObjectiveGardener(int point, ArrayList<Plot> bambouPlots, boolean improvement) {
        super(point);
        _bambouPlots    = bambouPlots;
        _improvement    = improvement;
    }

    @Override
    public boolean verifyPlotObj(GameEngine gameEngine) {
        return false;
    }

    @Override
    public boolean verifyGardenerObj(GameEngine gameEngine) {
        return gameEngine.computeObjectiveGardener(_bambouPlots, _improvement);
    }

    @Override
    public boolean verifyPandaObj(GameEngine gameEngine) {
        return false;
    }
}