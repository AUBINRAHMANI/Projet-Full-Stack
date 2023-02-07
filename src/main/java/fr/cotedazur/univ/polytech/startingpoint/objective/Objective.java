package fr.cotedazur.univ.polytech.startingpoint.objective;

import fr.cotedazur.univ.polytech.startingpoint.*;
import fr.cotedazur.univ.polytech.startingpoint.action.Action;
import fr.cotedazur.univ.polytech.startingpoint.action.ActionType;
import fr.cotedazur.univ.polytech.startingpoint.bot.Bot;

import java.util.List;


public abstract class Objective {
    protected int points;

    protected Objective(int points){
        this.points = points;
    }
    public int getPoint(){
        return points;
    }

    public abstract boolean verifyPlotObj(GameEngine gameEngine, Plot lastPlacedPlot);
    public abstract boolean verifyGardenerObj(GameEngine gameEngine);
    public abstract boolean verifyPandaObj(GameEngine gameEngine, BotProfil botProfil);
    public abstract Action tryToFillObjective(Bot bot, List<ActionType> banActionTypes, Weather weather);
    public abstract String toString();

}
