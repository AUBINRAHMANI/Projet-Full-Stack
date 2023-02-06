package fr.cotedazur.univ.polytech.startingpoint;


import java.util.*;

public class Pattern {

    private Random rand;
    List<Plot> plots;

    public Pattern(){
        plots = new ArrayList<>(Arrays.asList(new Plot(PlotType.GREEN, new Position(0,0))));
        this.rand = new Random();
        generateRandomPattern();
    }
    public Pattern(Pattern plots){
        this.plots = new ArrayList<>();
        this.rand = new Random();
        for(Plot plot : plots.getPlots()){
            this.plots.add(new Plot(plot));
        }
    }
    public Pattern(List<Plot> plots){
            this.plots = plots;
    }

    public void applyMask(Position position){
        for (Plot plot : plots){
            plot.setPosition(plot.getPosition().plus(position));
        }
    }

    public void add(Plot plot){
        plots.add( plot);
    }

    public void rotate60Right(){
        for (Plot plot : plots){
            plot.getPosition().rotate60Right();
        }
    }

    public void translateLeft(){
        for (Plot plot : plots){
            plot.getPosition().translateLeft();
        }
    }
    public void translateRight(){
        for (Plot plot : plots){
            plot.getPosition().translateRight();
        }
    }
    public void translateUp(){
        for (Plot plot : plots){
            plot.getPosition().translateUP();
        }
    }
    public void translateDown(){
        for (Plot plot : plots){
            plot.getPosition().translateDown();
        }
    }

    public List<Plot> getPlots(){
        return plots;
    }

    public  int size(){
        if(plots !=null)return plots.size();
        return 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pattern pattern1 = (Pattern) o;
        return plots.equals(pattern1.plots);
    }

    @Override
    public int hashCode() {
        return Objects.hash(plots);
    }

    @Override
    public String toString() {
        return "Pattern{" +
                "pattern=" + plots +
                '}';
    }

    public void setAncerPoint(Position position) {
        Position gap = new Position(0,0).minus(position);
        for(Plot patternPlot : plots){
            Position newPosition = patternPlot.getPosition().plus(gap);
            patternPlot.setPosition(newPosition);
        }
    }

    private void generateRandomPattern(){
        for(int i=0; i<rand.nextInt(2,4) ; i++){
            Plot plot = plots.get(rand.nextInt(plots.size()));
            List<Position> neighboursPosition = plot.getPosition().closestPositions();

            for(int j=0; j<neighboursPosition.size() ; ++j){
                Position position = neighboursPosition.get(rand.nextInt(neighboursPosition.size()));
                for(Plot tempPlot : plots){
                    if(tempPlot.getPosition().equals(position)){
                        neighboursPosition.remove(position);
                        break;
                    }
                }
                Plot plotToAdd = new Plot(PlotType.values()[rand.nextInt(3)+1], position);
                plotToAdd.isIrrigatedIsTrue();
                plots.add(plotToAdd);
                break;
            }


        }
    }
}
