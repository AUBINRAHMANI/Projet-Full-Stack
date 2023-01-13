package fr.cotedazur.univ.polytech.startingpoint;

import com.sun.source.tree.ArrayAccessTree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.Random;

public class Pattern {

    ArrayList<Plot> pattern;

    public Pattern(){
        pattern = new ArrayList<>(Arrays.asList(new Plot(PlotType.GREEN, new Position(0,0))));
        generateRandomPattern();

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
            plot.setPosition(plot.getPosition().plus(position));
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

    public void setAncerPoint(Position position) {
        Position gap = new Position(0,0).minus(position);
        for(Plot patternPlot : pattern){
            Position newPosition = patternPlot.getPosition().plus(gap);
            patternPlot.setPosition(newPosition);
        }
    }

    private void generateRandomPattern(){
        Random rand = new Random();
        for(int i=0; i<rand.nextInt(2,4) ; i++){
            Plot plot = pattern.get(rand.nextInt(pattern.size()));
            ArrayList<Position> neighboursPosition = plot.getPosition().closestPositions();
            out:
            for(int j=0; i<neighboursPosition.size() ; ++i){
                Position position = neighboursPosition.get(rand.nextInt(neighboursPosition.size()));
                for(Plot tempPlot : pattern){
                    if(tempPlot.getPosition().equals(position)){
                        neighboursPosition.remove(position);
                        break out;
                    }
                }
                pattern.add(new Plot(PlotType.values()[rand.nextInt(3)+1], position));
                break;
            }


        }
    }
}
