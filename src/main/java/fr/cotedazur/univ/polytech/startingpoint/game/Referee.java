package fr.cotedazur.univ.polytech.startingpoint.game;

import fr.cotedazur.univ.polytech.startingpoint.game.game_engine.items.Bamboo;
import fr.cotedazur.univ.polytech.startingpoint.game.game_engine.map.Plot;
import fr.cotedazur.univ.polytech.startingpoint.game.game_engine.map.Position;
import fr.cotedazur.univ.polytech.startingpoint.action.Action;
import fr.cotedazur.univ.polytech.startingpoint.bot.Playable;
import fr.cotedazur.univ.polytech.startingpoint.objective.Objective;

import java.util.List;

public interface Referee {
    List<Objective> getMyObjectives(Playable bot);

    List<Bamboo> getMyBamboos(Playable bot);

    Position getPandaPosition();

    Position getGardenerPosition();

    boolean computeObjectivesGardener();

    boolean computeObjectivesPlot(Plot lastPlacedPlot);

    boolean computeObjectivesPanda();

    boolean pickObjective(Playable bot);

    List<Plot> pickPlot();

    void addBambooToBot(Playable bot, Bamboo bambou);

    int getNumberOfPlayers();

    List<Action> getPreviousActions();
}
