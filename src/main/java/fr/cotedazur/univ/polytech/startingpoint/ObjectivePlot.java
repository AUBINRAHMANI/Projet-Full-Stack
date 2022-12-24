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

    @Override
    public boolean verifyPlotObj(GameEngine gameEngine) {
        return gameEngine.computeObjectivePlot();
    }

    @Override
    public boolean verifyGardenerObj(GameEngine gameEngine) {
        return false;
    }

    @Override
    public boolean verifyPandaObj(GameEngine gameEngine) {
        return false;
    }
}
