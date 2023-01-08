package fr.cotedazur.univ.polytech.startingpoint;

public class Main {

    public static void main(String[] args) {
        //Game game = new Game(true);
        //game.start();
        Map map = new Map();
        Plot plot = new Plot(PlotType.GREEN, new Position(1,0));
        Plot plot2 = new Plot(PlotType.GREEN, new Position(1,1));
        Plot plot3 = new Plot(PlotType.GREEN, new Position(2,0));
        Plot plot4 = new Plot(PlotType.GREEN, new Position(1,-1));
        map.putPlot(plot);
        map.putPlot(plot2);
        map.putPlot(plot3);
        map.putPlot(plot4);
        System.out.println(map.closestPlot(new Position(1,0)));
        System.out.println(map.closestAvailableSpace(new Position(1,0)));
    }

}
