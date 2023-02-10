package fr.cotedazur.univ.polytech.startingpoint.game.game_engine.items;

import fr.cotedazur.univ.polytech.startingpoint.game.game_engine.map.PlotType;

import java.util.Objects;

public class Bamboo {
    PlotType bambooType;

    public Bamboo(PlotType bambooType) {
        this.bambooType = bambooType;
    }

    public PlotType getBambooType() {
        return bambooType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bamboo bamboo = (Bamboo) o;
        return bambooType == bamboo.bambooType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(bambooType);
    }

    @Override
    public String toString() {
        return "Bamboo{" +
                "bambooType=" + bambooType +
                '}';
    }
}
