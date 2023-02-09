package fr.cotedazur.univ.polytech.startingpoint.objective;

import fr.cotedazur.univ.polytech.startingpoint.Bamboo;
import fr.cotedazur.univ.polytech.startingpoint.GameEngine;
import fr.cotedazur.univ.polytech.startingpoint.Plot;
import fr.cotedazur.univ.polytech.startingpoint.WeatherType;
import fr.cotedazur.univ.polytech.startingpoint.action.Action;
import fr.cotedazur.univ.polytech.startingpoint.action.ActionType;
import fr.cotedazur.univ.polytech.startingpoint.bot.BotProfile;
import fr.cotedazur.univ.polytech.startingpoint.bot.Playable;

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
    public boolean verifyPandaObj(GameEngine gameEngine, BotProfile botProfil) {
        return gameEngine.computeObjectivePanda(botProfil, bambooSections);
    }

    @Override
    public Action tryToFillObjective(Playable bot, List<ActionType> banActionTypes, WeatherType weather) {
        return bot.fillObjectivePanda(bambooSections, banActionTypes, weather);
    }

    @Override
    public String toString() {
        return "ObjectivePanda{" +
                "points=" + points +
                "bambooSections=" + bambooSections +
                '}';
    }
}