package fr.cotedazur.univ.polytech.startingpoint;

import java.util.Objects;

public class Bambou {
    PlotType bambouType_;

    public Bambou(PlotType bambouType){
        bambouType_=bambouType;
    }

    public PlotType getBambouType() {
        return bambouType_;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bambou bambou = (Bambou) o;
        return bambouType_ == bambou.bambouType_;
    }

    @Override
    public int hashCode() {
        return Objects.hash(bambouType_);
    }

    @Override
    public String toString() {
        return "Bambou{" +
                "bambouType_=" + bambouType_ +
                '}';
    }
}
