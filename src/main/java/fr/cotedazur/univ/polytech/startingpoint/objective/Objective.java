package fr.cotedazur.univ.polytech.startingpoint.objective;

import fr.cotedazur.univ.polytech.startingpoint.*;

import java.util.ArrayList;


public abstract class Objective {
    protected int _points;

    protected Objective(int points){
        _points = points;
    }
    public int getPoint(){
        return _points;
    }

    public abstract boolean verifyPlotObj(GameEngine gameEngine, Plot lastPlacedPlot);
    public abstract boolean verifyGardenerObj(GameEngine gameEngine);
    public abstract boolean verifyPandaObj(GameEngine gameEngine, BotProfil botProfil);
    public abstract String toString();

}
