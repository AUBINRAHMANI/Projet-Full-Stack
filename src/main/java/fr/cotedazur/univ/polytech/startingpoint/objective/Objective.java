package fr.cotedazur.univ.polytech.startingpoint.objective;

import fr.cotedazur.univ.polytech.startingpoint.BotProfil;
import fr.cotedazur.univ.polytech.startingpoint.GameEngine;
import fr.cotedazur.univ.polytech.startingpoint.Position;

public abstract class Objective {
    private int _points;
    public Objective(int points){
        _points = points;
    }
    public int getPoint(){
        return _points;
    }
    public abstract boolean isCompleted(GameEngine gameEngine, BotProfil botProfil);
}
