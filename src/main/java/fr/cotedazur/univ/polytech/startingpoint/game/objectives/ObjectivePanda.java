package fr.cotedazur.univ.polytech.startingpoint.game.objectives;

import fr.cotedazur.univ.polytech.startingpoint.game.game_engine.items.Bamboo;
import fr.cotedazur.univ.polytech.startingpoint.game.game_engine.GameEngine;
import fr.cotedazur.univ.polytech.startingpoint.game.game_engine.map.Plot;
import fr.cotedazur.univ.polytech.startingpoint.game.game_engine.items.WeatherType;
import fr.cotedazur.univ.polytech.startingpoint.game.action.Action;
import fr.cotedazur.univ.polytech.startingpoint.game.action.ActionType;
import fr.cotedazur.univ.polytech.startingpoint.bots.BotProfile;
import fr.cotedazur.univ.polytech.startingpoint.bots.Playable;
import fr.cotedazur.univ.polytech.startingpoint.statistique_manager.StatisticManager;

import java.util.List;

public class ObjectivePanda extends Objective {

    List<Bamboo> bambooSections;

    public ObjectivePanda(int point, List<Bamboo> bambooSections) {
        super(point);
        this.bambooSections = bambooSections;
    }

    @Override
    public boolean verifyPlotObj(GameEngine gameEngine, Plot lastPlacedPlot) {
        return false;
    }

    @Override
    public boolean verifyGardenerObj(GameEngine gameEngine) {
        return false;
    }

    @Override
    public boolean verifyPandaObj(GameEngine gameEngine, BotProfile botProfile) {
        return gameEngine.computeObjectivePanda(botProfile, bambooSections);
    }

    @Override
    public Action tryToFillObjective(Playable bot, List<ActionType> banActionTypes, WeatherType weather) {
        return bot.fillObjectivePanda(bambooSections, banActionTypes, weather);
    }

    @Override
    public void incrementationObjective(StatisticManager statisticManager, Playable bot) {
        statisticManager.incrementNumberObjectivePanda(bot);
    }

    @Override
    public void incrementationPointsObjective(StatisticManager statisticManager, Playable bot) {
        statisticManager.incrementNumberPointsObjectivePanda(bot, this.getPoint());
    }


    @Override
    public String toString() {
        return "ObjectivePanda{" +
                "points=" + points +
                "bambooSections=" + bambooSections +
                '}';
    }
}