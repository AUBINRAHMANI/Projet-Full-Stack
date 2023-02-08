package fr.cotedazur.univ.polytech.startingpoint.game;

import fr.cotedazur.univ.polytech.startingpoint.Bambou;
import fr.cotedazur.univ.polytech.startingpoint.Plot;
import fr.cotedazur.univ.polytech.startingpoint.Position;
import fr.cotedazur.univ.polytech.startingpoint.action.Action;
import fr.cotedazur.univ.polytech.startingpoint.bot.Playable;
import fr.cotedazur.univ.polytech.startingpoint.objective.Objective;

import java.util.List;

public interface Referee {
    List<Objective> getMyObjectives(Playable bot);

    List<Bambou> getMyBambous(Playable bot);

    Position getPandaPosition();

    Position getGardenerPosition();

    boolean computeObjectivesGardener();

    boolean computeObjectivesPlot(Plot lastPlacedPlot);

    boolean computeObjectivesPanda();

    boolean pickObjective(Playable bot);

    List<Plot> pickPlot();

    void addBamboutToBot(Playable bot, Bambou bambou);

    int getNumberOfPlayers();

    List<Action> getPreviousActions();
}
