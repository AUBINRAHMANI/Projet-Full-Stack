package fr.cotedazur.univ.polytech.startingpoint;

public abstract class Objective {
    private ObjectiveType _objective;
    private int _point;
    public Objective(int point, ObjectiveType objetive){
        this._point = point;
        _objective = objetive.PLOT;

    }
    public int getPoint(){
        return _point;
    }

    public ObjectiveType getType(){
        return _objective;
    }

    public abstract boolean verifyPlotObj(GameEngine gameEngine);
    public abstract boolean verifyGardenerObj(GameEngine gameEngine);
    public abstract boolean verifyPandaObj(GameEngine gameEngine);
}
