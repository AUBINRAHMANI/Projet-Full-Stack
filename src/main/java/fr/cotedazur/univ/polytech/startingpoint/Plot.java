package fr.cotedazur.univ.polytech.startingpoint;

import java.util.ArrayList;
import java.util.Objects;

public class Plot {

    private PlotType _plotType;
    private Position _position;
    private boolean isIrrigated = false;
    //private int bambou;
    private ArrayList<Bambou> listeBambou;
    private Bambou bambou;


    public Plot(PlotType plotType) {
        this(plotType, new Position(0,0));
    }

    public Plot(PlotType plotType, Position position) {
        _plotType = plotType;
        _position = position;
        listeBambou = new ArrayList<>();
    }
    public Plot(Plot plot){
        _plotType = plot.getType();
        _position = new Position(plot.getPosition());
        listeBambou = plot.getBambou();

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

    public void isIrrigatedIsTrue() {
        isIrrigated = true;
    }

    public boolean isIrrigated() {
        return isIrrigated;
    }

    public int getNumberOfBambou () {
        return this.listeBambou.size();
    }
    public boolean growBambou () {
        if (this.isIrrigated() && this.listeBambou.size() < 4) {
            this.listeBambou.add(bambou);
            return true;
        }
        return false;
    }
    public boolean eatBambou () {
        if (this.listeBambou.size() > 0) {
            this.listeBambou.remove(bambou);
            return true;
        }
        return false;
    }

    private ArrayList<Bambou> getBambou(){
        return listeBambou;
    }


    @Override
    public boolean equals (Object o){
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Plot plot = (Plot) o;
        return _plotType == plot._plotType && _position.equals(plot._position) && listeBambou.equals(plot.listeBambou) && Objects.equals(bambou, plot.bambou);
    }

    @Override
    public int hashCode () {
        return Objects.hash(_plotType, _position, listeBambou, bambou);
    }

    @Override
    public String toString() {
        return "Plot{" +
                "_plotType=" + _plotType +
                ", _position=" + _position +
                '}';
    }
}

