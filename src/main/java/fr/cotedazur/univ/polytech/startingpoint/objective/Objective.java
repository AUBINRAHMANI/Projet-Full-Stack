package fr.cotedazur.univ.polytech.startingpoint.objective;

import fr.cotedazur.univ.polytech.startingpoint.BotProfil;
import fr.cotedazur.univ.polytech.startingpoint.GameEngine;
import fr.cotedazur.univ.polytech.startingpoint.Position;

public abstract class Objective {
    private int _points;

    protected Objective(int points){
        _points = points;
    }
    public int getPoint(){
        return _points;
    }

    public abstract boolean verifyPlotObj(GameEngine gameEngine);
    public abstract boolean verifyGardenerObj(GameEngine gameEngine);
    public abstract boolean verifyPandaObj(GameEngine gameEngine);
}
