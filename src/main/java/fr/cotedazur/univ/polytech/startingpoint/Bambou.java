package fr.cotedazur.univ.polytech.startingpoint;

import java.util.Objects;

public class Bambou {
    PlotType bambouType;

    public Bambou(PlotType bambouType){
        this.bambouType=bambouType;
    }

    public PlotType getBambouType() {
        return bambouType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bambou bambou = (Bambou) o;
        return bambouType == bambou.bambouType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(bambouType);
    }

    @Override
    public String toString() {
        return "Bambou{" +
                "bambouType=" + bambouType +
                '}';
    }
}
