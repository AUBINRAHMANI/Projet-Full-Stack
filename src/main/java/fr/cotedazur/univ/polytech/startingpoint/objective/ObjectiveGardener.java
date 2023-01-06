package fr.cotedazur.univ.polytech.startingpoint.objective;

import fr.cotedazur.univ.polytech.startingpoint.*;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class ObjectiveGardener extends Objective{

    int nbBambou;
    int nbSection;
    PlotType bambouType;
    boolean improvement;

    public ObjectiveGardener(int point, int nbBambou, PlotType bambouType , boolean improvement, int nbSection) {
        super(point);
        this.nbBambou       = nbBambou;
        this.nbSection      = nbSection;
        this.bambouType     = bambouType;
        this.improvement    = improvement;
    }

    @Override
    public boolean verifyPlotObj(GameEngine gameEngine, Plot lastPlacedPlot) {
        return false;
    }

    @Override
    public boolean verifyGardenerObj(GameEngine gameEngine) {
        return gameEngine.computeObjectiveGardener(nbBambou, bambouType, improvement, nbSection);
    }

    @Override
    public boolean verifyPandaObj(GameEngine gameEngine) {
        return false;
    }
}