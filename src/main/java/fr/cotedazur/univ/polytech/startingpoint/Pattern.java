package fr.cotedazur.univ.polytech.startingpoint;

import java.util.ArrayList;
import java.util.Objects;

public class Pattern {

    ArrayList<Plot> pattern;

    public Pattern(){
        pattern = new ArrayList<>();
    }
    public Pattern(Pattern pattern){
        this.pattern = new ArrayList<>();
        for(Plot plot : pattern.getPlots()){
            this.pattern.add(new Plot(plot));
        }
    }
    public Pattern(ArrayList<Plot> pattern){
            this.pattern = pattern;
    }

    public void applyMask(Position position){
        for (Plot plot : pattern){
            plot.getPosition().plus(position);
        }
    }

    public void add(Plot plot){
        pattern.add( plot);
    }

    public void rotate60Right(){
        for (Plot plot : pattern){
            plot.getPosition().rotate60Right();
        }
    }
    public void translateLeft(){
        for (Plot plot : pattern){
            plot.getPosition().translateLeft();
        }
    }
    public void translateRight(){
        for (Plot plot : pattern){
            plot.getPosition().translateRight();
        }
    }
    public void translateUp(){
        for (Plot plot : pattern){
            plot.getPosition().translateUP();
        }
    }
    public void translateDown(){
        for (Plot plot : pattern){
            plot.getPosition().translateDown();
        }
    }

    public ArrayList<Plot> getPlots(){
        return pattern;
    }

    public  int size(){
        if(pattern !=null)return pattern.size();
        return 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pattern pattern1 = (Pattern) o;
        return pattern.equals(pattern1.pattern);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pattern);
    }

    @Override
    public String toString() {
        return "Pattern{" +
                "pattern=" + pattern +
                '}';
    }
}
