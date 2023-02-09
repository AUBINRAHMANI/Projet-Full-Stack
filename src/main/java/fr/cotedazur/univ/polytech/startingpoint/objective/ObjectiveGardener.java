package fr.cotedazur.univ.polytech.startingpoint.objective;

import fr.cotedazur.univ.polytech.startingpoint.GameEngine;
import fr.cotedazur.univ.polytech.startingpoint.Plot;
import fr.cotedazur.univ.polytech.startingpoint.PlotType;
import fr.cotedazur.univ.polytech.startingpoint.WeatherType;
import fr.cotedazur.univ.polytech.startingpoint.action.Action;
import fr.cotedazur.univ.polytech.startingpoint.action.ActionType;
import fr.cotedazur.univ.polytech.startingpoint.bot.BotProfil;
import fr.cotedazur.univ.polytech.startingpoint.bot.Playable;

import java.util.List;

public class ObjectiveGardener extends Objective {

    int nbBamboo;
    int nbPlot;
    PlotType bambooType;
    boolean improvement;

    public ObjectiveGardener(int point, int nbBamboo, PlotType bambooType, boolean improvement, int nbSection) {
        super(point);
        this.nbBamboo = nbBamboo;
        this.nbPlot = nbSection;
        this.bambooType = bambooType;
        this.improvement = improvement;
    }

    @Override
    public boolean verifyPlotObj(GameEngine gameEngine, Plot lastPlacedPlot) {
        return false;
    }

    @Override
    public boolean verifyGardenerObj(GameEngine gameEngine) {
        return gameEngine.computeObjectiveGardener(nbBamboo, bambooType, nbPlot);
    }

    @Override
    public boolean verifyPandaObj(GameEngine gameEngine, BotProfil botProfil) {
        return false;
    }

    @Override
    public Action tryToFillObjective(Playable bot, List<ActionType> banActionTypes, WeatherType weather) {
        return bot.fillObjectiveGardener(bambooType, improvement, banActionTypes, weather);
    }

    @Override
    public String toString() {
        return "ObjectiveGardener{" +
                "points=" + points +
                "nbBamboo=" + nbBamboo +
                ", nbSection=" + nbPlot +
                ", bambooType=" + bambooType +
                ", improvement=" + improvement +
                '}';
    }

}