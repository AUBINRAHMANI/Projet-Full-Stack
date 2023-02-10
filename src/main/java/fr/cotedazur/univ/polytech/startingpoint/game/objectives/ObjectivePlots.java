package fr.cotedazur.univ.polytech.startingpoint.game.objectives;

import fr.cotedazur.univ.polytech.startingpoint.game.game_engine.GameEngine;
import fr.cotedazur.univ.polytech.startingpoint.game.game_engine.map.Pattern;
import fr.cotedazur.univ.polytech.startingpoint.game.game_engine.map.Plot;
import fr.cotedazur.univ.polytech.startingpoint.game.game_engine.items.WeatherType;
import fr.cotedazur.univ.polytech.startingpoint.game.action.Action;
import fr.cotedazur.univ.polytech.startingpoint.game.action.ActionType;
import fr.cotedazur.univ.polytech.startingpoint.bots.BotProfile;
import fr.cotedazur.univ.polytech.startingpoint.bots.Playable;
import fr.cotedazur.univ.polytech.startingpoint.statistique_manager.StatisticManager;

import java.util.List;


public class ObjectivePlots extends Objective {

    Pattern pattern;

    public ObjectivePlots(int point, Pattern pattern) {
        super(point);
        this.pattern = pattern;

    }

    @Override
    public boolean verifyPlotObj(GameEngine gameEngine, Plot lastPlacedPlot) {
        return gameEngine.computeObjectivePlot(pattern, lastPlacedPlot);
    }

    @Override
    public boolean verifyGardenerObj(GameEngine gameEngine) {
        return false;
    }

    @Override
    public boolean verifyPandaObj(GameEngine gameEngine, BotProfile botProfile) {
        return false;
    }

    @Override
    public Action tryToFillObjective(Playable bot, List<ActionType> banActionTypes, WeatherType weather) {
        return bot.fillObjectivePlots(pattern, banActionTypes);
    }

    @Override
    public void incrementationObjective(StatisticManager statisticManager, Playable bot) {
        statisticManager.incrementNumberObjectivePlots(bot);

    }

    @Override
    public void incrementationPointsObjective(StatisticManager statisticManager, Playable bot) {
        statisticManager.incrementNumberPointsObjectivePlot(bot, this.getPoint());
    }

    @Override
    public String toString() {
        return "ObjectivePlots{" +
                "points=" + points +
                "pattern=" + pattern +
                '}';
    }
}
