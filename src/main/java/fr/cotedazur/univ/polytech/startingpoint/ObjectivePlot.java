package fr.cotedazur.univ.polytech.startingpoint;

public class ObjectivePlot extends Objective{
    private int _nbPlotsAdjacents;
    public ObjectivePlot(int point, ObjectiveType objectiveType, int nbPlotsAdjacents) {
        super(point, objectiveType);
        _nbPlotsAdjacents = nbPlotsAdjacents;
    }

    public int getNbPlotsAdjacents(){
            return this._nbPlotsAdjacents;
    }
}
