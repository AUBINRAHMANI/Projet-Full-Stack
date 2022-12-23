package fr.cotedazur.univ.polytech.startingpoint;

public class Plot {

    private PlotType _plotType;
    private Position _position;
    private int bambou;
    public Plot(PlotType plotType){
        _plotType = plotType;
        _position = null;
        bambou=0;
    }
    public Plot(PlotType plotType, Position position){
        _plotType = plotType;
        _position = position;
        bambou =0;
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

    public int getNumberOfBambou(){return this.bambou;}
    public void growBambou() {
        if (this.bambou < 4) {
            this.bambou = bambou + 1;
        }
    }
    public int eatBambou(){
        if(this.bambou>0){
            return this.bambou= bambou-1;
        }
        return 0;
    }

}

