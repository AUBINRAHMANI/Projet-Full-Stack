package fr.cotedazur.univ.polytech.startingpoint;

import fr.cotedazur.univ.polytech.startingpoint.bot.Bot;
import fr.cotedazur.univ.polytech.startingpoint.objective.*;
import java.util.ArrayList;
import java.util.List;

public class BotProfil {

    private Bot bot;
    private List<Objective> objectives;
    private int numberOfHit;
    private List<Bambou> bambous;
    private int points;
    private int nbCompletedObjective;

    public BotProfil(Bot bot){
        this.bot = bot;
        objectives = new ArrayList<>();
        bambous = new ArrayList<>();
        numberOfHit = 1;
        points = 0;
        nbCompletedObjective = 0;
    }

    public Bot getBot() {
        return bot;
    }
    public int getNumberOfHit(){return numberOfHit;}

    public int getPoints() {
        return points;
    }
    public void addPoints(int nbPoints){
        points +=nbPoints;
    }

    public int getNbCompletedObjective() {return nbCompletedObjective;}

    public List<Objective> getObjectives() {
        return objectives;
    }

    public void addObjective(Objective objective){
        objectives.add(objective);
    }

    public void setObjectiveCompleted(Objective objective){
        ++nbCompletedObjective;
        points += objective.getPoint();
    }

    public void addBanbou(Bambou bambou){
        bambous.add(bambou);
    }

    public void setBambous(List<Bambou> bambous){
        this.bambous = bambous;
    }

    public List<Bambou> getBambous(){
        return bambous;
    }
}
