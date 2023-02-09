package fr.cotedazur.univ.polytech.startingpoint.objective;

import fr.cotedazur.univ.polytech.startingpoint.Bambou;
import fr.cotedazur.univ.polytech.startingpoint.GameEngine;
import fr.cotedazur.univ.polytech.startingpoint.Plot;
import fr.cotedazur.univ.polytech.startingpoint.WeatherType;
import fr.cotedazur.univ.polytech.startingpoint.action.Action;
import fr.cotedazur.univ.polytech.startingpoint.action.ActionType;
import fr.cotedazur.univ.polytech.startingpoint.bot.BotProfil;
import fr.cotedazur.univ.polytech.startingpoint.bot.Playable;
import fr.cotedazur.univ.polytech.startingpoint.statistique_manager.StatistiqueManager;

import java.util.List;

public class ObjectivePanda extends Objective {

    List<Bambou> bambouSections;

    public ObjectivePanda(int point, List<Bambou> bambouSections) {
        super(point);
        this.bambouSections = bambouSections;
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
    public boolean verifyPandaObj(GameEngine gameEngine, BotProfil botProfil) {
        return gameEngine.computeObjectivePanda(botProfil, bambouSections);
    }

    @Override
    public Action tryToFillObjective(Playable bot, List<ActionType> banActionTypes, WeatherType weather) {
        return bot.fillObjectivePanda(bambouSections, banActionTypes, weather);
    }

    @Override
    public void incrementationObjective(StatistiqueManager statistiqueManager, Playable bot) {
        statistiqueManager.incrementNumberObjectivePanda(bot);
    }

    @Override
    public void incrementationPointsObjective(StatistiqueManager statistiqueManager, Playable bot) {
        statistiqueManager.incrementNumberPointsObjectivePanda(bot,this.getPoint());
    }


    @Override
    public String toString() {
        return "ObjectivePanda{" +
                "points=" + points +
                "bambouSections=" + bambouSections +
                '}';
    }
}