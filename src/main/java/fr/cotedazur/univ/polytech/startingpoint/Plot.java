package fr.cotedazur.univ.polytech.startingpoint;

import java.util.ArrayList;
import java.util.Objects;

public class Plot {

    private final PlotType plotType;
    private final ArrayList<Bamboo> listBamboo;
    private Position position;
    private boolean isIrrigated = false;


    public Plot(PlotType plotType) {
        this(plotType, new Position(0, 0));
    }

    public Plot(PlotType plotType, Position position) {
        this.plotType = plotType;
        this.position = position;
        listBamboo = new ArrayList<>();
    }

    public Plot(Plot plot) {
        plotType = plot.getType();
        position = new Position(plot.getPosition());
        listBamboo = plot.getBamboo();
        isIrrigated = plot.isIrrigated;

    }

    public PlotType getType() {
        return plotType;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public void isIrrigatedIsTrue() {
        isIrrigated = true;
    }

    public boolean isIrrigated() {
        return isIrrigated;
    }

    public int getNumberOfBamboo() {
        return this.listBamboo.size();
    }

    public boolean growBamboo() {
        if (this.isIrrigated() && this.listBamboo.size() < 4) {
            this.listBamboo.add(new Bamboo(plotType));
            return true;
        }
        return false;
    }

    public Bamboo eatBamboo() {
        if (!this.listBamboo.isEmpty()) {
            Bamboo bamboo = listBamboo.get(0);
            this.listBamboo.remove(bamboo);
            return bamboo;
        }
        return null;
    }

    private ArrayList<Bamboo> getBamboo() {
        return listBamboo;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Plot plot = (Plot) o;
        return isIrrigated == plot.isIrrigated && plotType == plot.plotType && Objects.equals(position, plot.position);
    }

    @Override
    public int hashCode() {
        return Objects.hash(plotType, position, isIrrigated);
    }

    @Override
    public String toString() {
        return "Plot{" +
                "plotType=" + plotType +
                ", position=" + position +
                ", isIrrigated=" + isIrrigated +
                '}';
    }
}

