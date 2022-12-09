package fr.cotedazur.univ.polytech.startingpoint;

public class Plot {

    private PlotType _plotType;
    private Position _position;
    public Plot(PlotType plotType){
        _plotType = plotType;
        _position = null;
    }
    public Plot(PlotType plotType, Position position){
        _plotType = plotType;
        _position = position;
    }
    public void set_position(Position _position) {
        this._position = _position;
    }
    public PlotType getType() {
        return _plotType;
    }
    public Position get_position() {
        return _position;
    }
}

