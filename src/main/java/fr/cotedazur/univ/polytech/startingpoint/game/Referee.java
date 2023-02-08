package fr.cotedazur.univ.polytech.startingpoint.game;

import fr.cotedazur.univ.polytech.startingpoint.Bambou;
import fr.cotedazur.univ.polytech.startingpoint.action.Action;
import fr.cotedazur.univ.polytech.startingpoint.bot.Playable;
import fr.cotedazur.univ.polytech.startingpoint.Plot;
import fr.cotedazur.univ.polytech.startingpoint.Position;
import fr.cotedazur.univ.polytech.startingpoint.objective.Objective;

import java.util.List;

public interface Referee {
    public List<Objective> getMyObjectives(Playable bot);
    public List<Bambou> getMyBambous(Playable bot);
    public Position getPandaPosition();
    public Position getGardenerPosition();
    public boolean computeObjectivesGardener();
    public boolean computeObjectivesPlot(Plot lastPlacedPlot);
    public boolean computeObjectivesPanda();
    public boolean pickObjective(Playable bot);
    public List<Plot> pickPlot();
    public void addBamboutToBot(Playable bot, Bambou bambou);
    public int getNumberOfPlayers();
    public List<Action> getPreviousActions();
}
