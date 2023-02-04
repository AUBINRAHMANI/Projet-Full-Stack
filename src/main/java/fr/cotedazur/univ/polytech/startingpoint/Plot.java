package fr.cotedazur.univ.polytech.startingpoint;

import java.util.ArrayList;
import java.util.Objects;

public class Plot {

    private PlotType plotType;
    private Position position;
    private boolean isIrrigated = false;
    private ArrayList<Bambou> listeBambou;


    public Plot(PlotType plotType) {
        this(plotType, new Position(0,0));
    }

    public Plot(PlotType plotType, Position position) {
        this.plotType = plotType;
        this.position = position;
        listeBambou = new ArrayList<>();
    }
    public Plot(Plot plot){
        plotType = plot.getType();
        position = new Position(plot.getPosition());
        listeBambou = plot.getBambou();

    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public PlotType getType() {
        return plotType;
    }

    public Position getPosition() {
        return position;
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
            this.listeBambou.add(new Bambou(plotType));
            return true;
        }
        return false;
    }
    public Bambou eatBambou () {
        if (this.listeBambou.isEmpty() ==false) {
            Bambou bambou = listeBambou.get(0);
            this.listeBambou.remove(bambou);
            return bambou;
        }
        return null;
    }

    private ArrayList<Bambou> getBambou(){
        return listeBambou;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Plot plot = (Plot) o;
        return Objects.equals(position, plot.position);
    }

    @Override
    public int hashCode() {
        return Objects.hash(position);
    }

    @Override
    public String toString() {
        return "{" +
                plotType +
                " - " + position +
                "/"+ getNumberOfBambou() +
                '}';
    }
}

