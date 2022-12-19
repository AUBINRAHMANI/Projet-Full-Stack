package fr.cotedazur.univ.polytech.startingpoint.objective;

public class ObjectiveGardener extends Objective{

    int     _nbBambouSection;
    boolean _improvement;

    public ObjectiveGardener(int point, int nbBambouSection, boolean improvement) {
        super(point, ObjectiveType.GARDENER);
        _nbBambouSection    = nbBambouSection;
        _improvement        = improvement;
    }

    public int getNbBambouSections() {
        return _nbBambouSection;
    }

    public boolean needImprovement() {
        return _improvement;
    }
}