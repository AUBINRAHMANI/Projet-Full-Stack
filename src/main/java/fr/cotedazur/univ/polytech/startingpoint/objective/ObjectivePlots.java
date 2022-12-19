package fr.cotedazur.univ.polytech.startingpoint.objective;

public class ObjectivePlots extends Objective{
    private int _nbPlotsAdjacents;
    public ObjectivePlots(int point, int nbPlotsAdjacents) {
        super(point, ObjectiveType.PLOTS);
        _nbPlotsAdjacents = nbPlotsAdjacents;
    }

    public int getNbPlotsAdjacents(){
            return this._nbPlotsAdjacents;
    }
}
