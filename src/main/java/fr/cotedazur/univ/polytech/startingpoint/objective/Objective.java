package fr.cotedazur.univ.polytech.startingpoint.objective;

import fr.cotedazur.univ.polytech.startingpoint.GameEngine;
import fr.cotedazur.univ.polytech.startingpoint.Plot;
import fr.cotedazur.univ.polytech.startingpoint.WeatherType;
import fr.cotedazur.univ.polytech.startingpoint.action.Action;
import fr.cotedazur.univ.polytech.startingpoint.action.ActionType;
import fr.cotedazur.univ.polytech.startingpoint.bot.BotProfile;
import fr.cotedazur.univ.polytech.startingpoint.bot.Playable;

import java.util.List;


public abstract class Objective {
    protected int points;

    protected Objective(int points) {
        this.points = points;
    }

    public int getPoint() {
        return points;
    }

    public abstract boolean verifyPlotObj(GameEngine gameEngine, Plot lastPlacedPlot);

    public abstract boolean verifyGardenerObj(GameEngine gameEngine);

    public abstract boolean verifyPandaObj(GameEngine gameEngine, BotProfile botProfil);

    public abstract Action tryToFillObjective(Playable bot, List<ActionType> banActionTypes, WeatherType weather);

    public abstract String toString();

}
