package fr.cotedazur.univ.polytech.startingpoint.objective;

import fr.cotedazur.univ.polytech.startingpoint.*;


public class ObjectivePlots extends Objective{
    private int _nbPlotsAdjacents;
    public ObjectivePlots(int point, int nbPlotsAdjacents) {
        super(point);
        _nbPlotsAdjacents = nbPlotsAdjacents;
    }

    public int getNbPlotsAdjacents(){
            return this._nbPlotsAdjacents;
    }

    @Override
    public boolean isCompleted(GameEngine gameEngine, BotProfil botProfil){
        return gameEngine.isObjectivePlotCompleted(this, botProfil);
    }
}
