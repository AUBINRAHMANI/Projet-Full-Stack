package fr.cotedazur.univ.polytech.startingpoint;

public class Bot {



    public Bot() {
    }

    public void play(Game gameEngine, Map map) {
        this.plotToSet(gameEngine, map);
    }

    ;


    public Plot plotToSet(Game gameEngine, Map map) {
        if (map.isPlotFree(gameEngine.pickPlot()) && (gameEngine.askToSetPlot(this.plotToSet()))) {
            gameEngine.play();
        }


        }

}
