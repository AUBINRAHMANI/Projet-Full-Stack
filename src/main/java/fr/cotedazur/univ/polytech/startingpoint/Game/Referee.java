package fr.cotedazur.univ.polytech.startingpoint.Game;

import fr.cotedazur.univ.polytech.startingpoint.Bambou;
import fr.cotedazur.univ.polytech.startingpoint.Bot;
import fr.cotedazur.univ.polytech.startingpoint.Plot;
import fr.cotedazur.univ.polytech.startingpoint.Position;
import fr.cotedazur.univ.polytech.startingpoint.objective.Objective;

import java.util.ArrayList;
import java.util.List;

public interface Referee {
    public ArrayList<Objective> getMyObjectives(Bot bot);
    public ArrayList<Bambou> getMyBambous(Bot bot);
    public Position getPandaPosition();
    public Position getGardenerPosition();
    public boolean computeObjectivesGardener();
    public boolean computeObjectivesPlot(Plot lastPlacedPlot);
    public boolean computeObjectivesPanda();
    public boolean pickObjective(Bot bot);
    public List<Plot> pickPlot();
    public void addBamboutToBot(Bot bot, Bambou bambou);
}
