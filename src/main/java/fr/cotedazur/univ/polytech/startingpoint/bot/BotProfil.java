package fr.cotedazur.univ.polytech.startingpoint.bot;

import fr.cotedazur.univ.polytech.startingpoint.Bambou;
import fr.cotedazur.univ.polytech.startingpoint.objective.Objective;

import java.util.ArrayList;
import java.util.List;

public class BotProfil {

    private final Playable bot;
    String botName;
    private List<Objective> objectives;
    private List<Bambou> bambous;
    private int points;
    private int nbCompletedObjective;

    public BotProfil(Playable bot, String botName) {
        this.bot = bot;
        this.botName = botName;
        objectives = new ArrayList<>();
        bambous = new ArrayList<>();
        points = 0;
        nbCompletedObjective = 0;
    }

    public Playable getBot() {
        return bot;
    }

    public String getBotName() {
        return botName;
    }

    public int getPoints() {
        return points;
    }

    public void resetPoints() {
        points = 0;
        nbCompletedObjective = 0;
        bambous = new ArrayList<>();
        objectives = new ArrayList<>();
    }

    public int getNbCompletedObjective() {
        return nbCompletedObjective;
    }

    public List<Objective> getObjectives() {
        return objectives;
    }

    public void addObjective(Objective objective) {
        objectives.add(objective);
    }

    public void setObjectiveCompleted(Objective objective) {
        ++nbCompletedObjective;
        points += objective.getPoint();
    }

    public void addBanbou(Bambou bambou) {
        bambous.add(bambou);
    }

    public List<Bambou> getBambous() {
        return bambous;
    }

    public void setBambous(List<Bambou> bambous) {
        this.bambous = bambous;
    }
}
