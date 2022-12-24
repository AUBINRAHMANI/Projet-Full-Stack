package fr.cotedazur.univ.polytech.startingpoint.objective;

import fr.cotedazur.univ.polytech.startingpoint.*;

public class ObjectiveGardener extends Objective{

    int     _nbBambouSection;
    int     _nbDifferentPLot;
    boolean _improvement;

    public ObjectiveGardener(int point, int nbBambouSection, boolean improvement, int nbDifferentPLot) {
        super(point);
        _nbBambouSection    = nbBambouSection;
        _improvement        = improvement;
        _nbDifferentPLot    = nbDifferentPLot;
    }

    public int getNbBambouSections() {
        return _nbBambouSection;
    }

    public boolean needImprovement() {
        return _improvement;
    }

    public int get_nbDifferentPLot() { return _nbDifferentPLot; }

    @Override
    public boolean isCompleted(GameEngine gameEngine, BotProfil botProfil){
        return gameEngine.isObjectiveGardenerCompleted(this, botProfil);
    }
}