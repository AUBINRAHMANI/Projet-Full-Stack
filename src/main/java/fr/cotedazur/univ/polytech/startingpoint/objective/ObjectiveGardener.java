package fr.cotedazur.univ.polytech.startingpoint.objective;

import fr.cotedazur.univ.polytech.startingpoint.GameEngine;
import fr.cotedazur.univ.polytech.startingpoint.Plot;
import fr.cotedazur.univ.polytech.startingpoint.PlotType;
import fr.cotedazur.univ.polytech.startingpoint.WeatherType;
import fr.cotedazur.univ.polytech.startingpoint.action.Action;
import fr.cotedazur.univ.polytech.startingpoint.action.ActionType;
import fr.cotedazur.univ.polytech.startingpoint.bot.BotProfil;
import fr.cotedazur.univ.polytech.startingpoint.bot.Playable;
import fr.cotedazur.univ.polytech.startingpoint.statistique_manager.StatistiqueManager;

import java.util.List;

public class ObjectiveGardener extends Objective {

    int nbBambou;
    int nbPlot;
    PlotType bambouType;
    boolean improvement;

    public ObjectiveGardener(int point, int nbBambou, PlotType bambouType, boolean improvement, int nbSection) {
        super(point);
        this.nbBambou = nbBambou;
        this.nbPlot = nbSection;
        this.bambouType = bambouType;
        this.improvement = improvement;
    }

    @Override
    public boolean verifyPlotObj(GameEngine gameEngine, Plot lastPlacedPlot) {
        return false;
    }

    @Override
    public boolean verifyGardenerObj(GameEngine gameEngine) {
        return gameEngine.computeObjectiveGardener(nbBambou, bambouType, nbPlot);
    }

    @Override
    public boolean verifyPandaObj(GameEngine gameEngine, BotProfil botProfil) {
        return false;
    }

    @Override
    public Action tryToFillObjective(Playable bot, List<ActionType> banActionTypes, WeatherType weather) {
        return bot.fillObjectiveGardener(bambouType, improvement, banActionTypes, weather);
    }

    @Override
    public void incrementationObjective(StatistiqueManager statistiqueManager, Playable bot){
        statistiqueManager.incrementNumberObjectiveGardener(bot);
    }

    @Override
    public String toString() {
        return "ObjectiveGardener{" +
                "points=" + points +
                "nbBambou=" + nbBambou +
                ", nbSection=" + nbPlot +
                ", bambouType=" + bambouType +
                ", improvement=" + improvement +
                '}';
    }

}