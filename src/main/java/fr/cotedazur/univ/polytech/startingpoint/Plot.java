package fr.cotedazur.univ.polytech.startingpoint;

import java.util.ArrayList;

public class Plot {

    private PlotType _plotType;
    private Position _position;
    //private int bambou;
    private ArrayList<Bambou> listeBambou;
    private Bambou bambou;
    public Plot(PlotType plotType){
        _plotType = plotType;
        _position = null;
        listeBambou = new ArrayList<>();
    }
    public Plot(PlotType plotType, Position position){
        _plotType = plotType;
        _position = position;
        listeBambou = new ArrayList<>();
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

    public int getNumberOfBambou(){
        return this.listeBambou.size();
    }
    public boolean growBambou() {
        if (this.listeBambou.size()<4) {
            this.listeBambou.add(bambou);
            return true ;
        }
        return false;
    }
    public boolean eatBambou(){
        if (this.listeBambou.size()>0){
            this.listeBambou.remove(bambou);
            return true;
        }
        return false;
    }

}

