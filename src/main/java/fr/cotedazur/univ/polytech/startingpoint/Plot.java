package fr.cotedazur.univ.polytech.startingpoint;

public class Plot {

    private PlotType _plotType;
    private Position _position;

    private boolean isIrrigated = false;
    public Plot(PlotType plotType){
        _plotType = plotType;
        _position = null;
    }
    public Plot(PlotType plotType, Position position){
        _plotType = plotType;
        _position = position;
    }

    public void setPosition(Position _position) {
        this._position = _position;
    }
    public PlotType getType() {
        return _plotType;
    }
    public Position getPosition() {
        return _position;
    }
}

