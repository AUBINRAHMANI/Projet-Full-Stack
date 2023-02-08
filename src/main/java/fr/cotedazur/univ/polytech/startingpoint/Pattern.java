package fr.cotedazur.univ.polytech.startingpoint;


import java.security.SecureRandom;
import java.util.*;

public class Pattern {

    List<Plot> plots;
    private Random rand;

    public Pattern() {
        plots = new ArrayList<>();
        this.rand = new SecureRandom();
        generateRandomPattern();
    }

    public Pattern(Pattern plots) {
        this.plots = new ArrayList<>();
        this.rand = new SecureRandom();
        for (Plot plot : plots.getPlots()) {
            this.plots.add(new Plot(plot));
        }
    }

    public Pattern(List<Plot> plots) {
        this.plots = plots;
    }

    public void applyMask(Position position) {
        for (Plot plot : plots) {
            plot.setPosition(plot.getPosition().plus(position));
        }
    }

    public void add(Plot plot) {
        plots.add(plot);
    }

    public void rotate60Right() {
        for (Plot plot : plots) {
            plot.getPosition().rotate60Right();
        }
    }

    public void translateLeft() {
        for (Plot plot : plots) {
            plot.getPosition().translateLeft();
        }
    }

    public void translateRight() {
        for (Plot plot : plots) {
            plot.getPosition().translateRight();
        }
    }

    public void translateUp() {
        for (Plot plot : plots) {
            plot.getPosition().translateUP();
        }
    }

    public void translateDown() {
        for (Plot plot : plots) {
            plot.getPosition().translateDown();
        }
    }

    public List<Plot> getPlots() {
        return plots;
    }

    public int size() {
        if (plots != null) return plots.size();
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
        Position gap = new Position(0, 0).minus(position);
        for (Plot patternPlot : plots) {
            Position newPosition = patternPlot.getPosition().plus(gap);
            patternPlot.setPosition(newPosition);
        }
    }

    private void generateRandomPattern() {
        List<Position> patternPositions = new ArrayList<>(List.of(new Position(0, 0)));
        for (int i = 0; i < rand.nextInt(2, 4); i++) {
            Position position = patternPositions.get(rand.nextInt(patternPositions.size()));
            List<Position> neighboursPosition = position.closestPositions();
            do {
                position = neighboursPosition.get(rand.nextInt(neighboursPosition.size()));
            } while (patternPositions.contains(position));
            patternPositions.add(position);
        }
        for (Position position : patternPositions) {
            plots.add(new Plot(PlotType.values()[rand.nextInt(3) + 1], position));
        }
    }
}
