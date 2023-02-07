package fr.cotedazur.univ.polytech.startingpoint.objective;

import fr.cotedazur.univ.polytech.startingpoint.*;
import fr.cotedazur.univ.polytech.startingpoint.action.Action;
import fr.cotedazur.univ.polytech.startingpoint.action.ActionType;
import fr.cotedazur.univ.polytech.startingpoint.bot.Bot;

import java.util.List;

public class ObjectiveGardener extends Objective{

    int nbBambou;
    int nbPlot;
    PlotType bambouType;
    boolean improvement;

    public ObjectiveGardener(int point, int nbBambou, PlotType bambouType , boolean improvement, int nbSection) {
        super(point);
        this.nbBambou       = nbBambou;
        this.nbPlot      = nbSection;
        this.bambouType     = bambouType;
        this.improvement    = improvement;
    }

    @Override
    public boolean verifyPlotObj(GameEngine gameEngine, Plot lastPlacedPlot) {
        return false;
    }

    @Override
    public boolean verifyGardenerObj(GameEngine gameEngine) {
        return gameEngine.computeObjectiveGardener(nbBambou, bambouType, improvement, nbPlot);
    }

    @Override
    public boolean verifyPandaObj(GameEngine gameEngine, BotProfil botProfil) {
        return false;
    }

    @Override
    public Action tryToFillObjective(Bot bot, List<ActionType> banActionTypes, Weather weather){
        return bot.fillObjectiveGardener( bambouType , improvement, banActionTypes, weather);
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