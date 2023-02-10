package fr.cotedazur.univ.polytech.startingpoint.bot;

import fr.cotedazur.univ.polytech.startingpoint.game.game_engine.items.Bamboo;
import fr.cotedazur.univ.polytech.startingpoint.objective.Objective;

import java.util.ArrayList;
import java.util.List;

public class BotProfile {

    private final Playable bot;
    String botName;
    private List<Objective> objectives;
    private List<Bamboo> bamboos;
    private int points;
    private int nbCompletedObjective;

    public BotProfile(Playable bot, String botName) {
        this.bot = bot;
        this.botName = botName;
        objectives = new ArrayList<>();
        bamboos = new ArrayList<>();
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
        bamboos = new ArrayList<>();
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

    public void addBamboo(Bamboo bamboo) {
        bamboos.add(bamboo);
    }

    public List<Bamboo> getBamboos() {
        return bamboos;
    }

    public void setBamboos(List<Bamboo> bamboos) {
        this.bamboos = bamboos;
    }
}
