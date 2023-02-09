package fr.cotedazur.univ.polytech.startingpoint.objective;

import fr.cotedazur.univ.polytech.startingpoint.GameEngine;
import fr.cotedazur.univ.polytech.startingpoint.Pattern;
import fr.cotedazur.univ.polytech.startingpoint.Plot;
import fr.cotedazur.univ.polytech.startingpoint.WeatherType;
import fr.cotedazur.univ.polytech.startingpoint.action.Action;
import fr.cotedazur.univ.polytech.startingpoint.action.ActionType;
import fr.cotedazur.univ.polytech.startingpoint.bot.BotProfil;
import fr.cotedazur.univ.polytech.startingpoint.bot.Playable;
import fr.cotedazur.univ.polytech.startingpoint.statistique_manager.StatistiqueManager;

import java.util.List;


public class ObjectivePlots extends Objective {

    Pattern pattern;

    public ObjectivePlots(int point, List<Plot> plots) {
        super(point);
        pattern = new Pattern(plots);

    }

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
    public boolean verifyPandaObj(GameEngine gameEngine, BotProfil botProfil) {
        return false;
    }

    @Override
    public Action tryToFillObjective(Playable bot, List<ActionType> banActionTypes, WeatherType weather) {
        return bot.fillObjectivePlots(pattern, banActionTypes);
    }

    @Override
    public void incrementationObjective(StatistiqueManager statistiqueManager, Playable bot) {
        statistiqueManager.incrementNumberObjectivePlots(bot);

    }

    @Override
    public void incrementationPointsObjective(StatistiqueManager statistiqueManager, Playable bot) {
        statistiqueManager.incrementNumberPointsObjectivePlot(bot,this.getPoint());
    }

    @Override
    public String toString() {
        return "ObjectivePlots{" +
                "points=" + points +
                "pattern=" + pattern +
                '}';
    }
}
