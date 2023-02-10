package fr.cotedazur.univ.polytech.startingpoint.game.objectives;

import fr.cotedazur.univ.polytech.startingpoint.game.game_engine.GameEngine;
import fr.cotedazur.univ.polytech.startingpoint.game.game_engine.map.Plot;
import fr.cotedazur.univ.polytech.startingpoint.game.game_engine.map.PlotType;
import fr.cotedazur.univ.polytech.startingpoint.game.game_engine.items.WeatherType;
import fr.cotedazur.univ.polytech.startingpoint.game.action.Action;
import fr.cotedazur.univ.polytech.startingpoint.game.action.ActionType;
import fr.cotedazur.univ.polytech.startingpoint.bots.BotProfile;
import fr.cotedazur.univ.polytech.startingpoint.bots.Playable;
import fr.cotedazur.univ.polytech.startingpoint.statistique_manager.StatisticManager;

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
    public boolean verifyPandaObj(GameEngine gameEngine, BotProfile botProfile) {
        return false;
    }

    @Override
    public Action tryToFillObjective(Playable bot, List<ActionType> banActionTypes, WeatherType weather) {
        return bot.fillObjectiveGardener(bambooType, improvement, banActionTypes, weather);
    }

    @Override
    public void incrementationObjective(StatisticManager statisticManager, Playable bot) {
        statisticManager.incrementNumberObjectiveGardener(bot);
    }

    @Override
    public void incrementationPointsObjective(StatisticManager statisticManager, Playable bot) {
        statisticManager.incrementNumberPointsObjectiveGardener(bot, this.getPoint());
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