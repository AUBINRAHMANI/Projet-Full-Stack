package fr.cotedazur.univ.polytech.startingpoint.objective;

import fr.cotedazur.univ.polytech.startingpoint.*;
import fr.cotedazur.univ.polytech.startingpoint.action.Action;
import fr.cotedazur.univ.polytech.startingpoint.action.ActionType;
import fr.cotedazur.univ.polytech.startingpoint.bot.BotProfil;
import fr.cotedazur.univ.polytech.startingpoint.bot.BotMbappe;
import fr.cotedazur.univ.polytech.startingpoint.bot.Playable;

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
    public String toString() {
        return "ObjectivePanda{" +
                "points=" + points +
                "bambouSections=" + bambouSections +
                '}';
    }
}