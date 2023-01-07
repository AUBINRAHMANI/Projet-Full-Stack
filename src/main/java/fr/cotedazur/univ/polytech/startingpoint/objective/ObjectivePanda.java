package fr.cotedazur.univ.polytech.startingpoint.objective;

import fr.cotedazur.univ.polytech.startingpoint.*;
import java.util.ArrayList;

public class ObjectivePanda extends Objective {

    ArrayList<Bambou> bambouSections;

    public ObjectivePanda(int point, ArrayList<Bambou> bambouSections) {
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
}