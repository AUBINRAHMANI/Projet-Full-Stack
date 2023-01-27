package fr.cotedazur.univ.polytech.startingpoint;

import fr.cotedazur.univ.polytech.startingpoint.objective.*;
import java.util.ArrayList;

public class BotProfil {

    private Bot bot_;
    private ArrayList<Objective> objectives_;

    private ArrayList<Bambou> bambous;
    private int points_;
    private int nbCompletedObjective_;
    private int numberOfHit;

    public BotProfil(Bot bot){
        bot_ = bot;
        objectives_ = new ArrayList<>();
        bambous = new ArrayList<>();
        points_ = 0;
        nbCompletedObjective_ = 0;
        numberOfHit = 1;
    }

    public Bot getBot_() {
        return bot_;
    }
    public int getNumberOfHit(){return numberOfHit;}

    public int getPoints_() {
        return points_;
    }
    public void addPoints_(int nbPoints){
        points_+=nbPoints;
    }

    public int getNbCompletedObjective_() {return nbCompletedObjective_;}

    public ArrayList<Objective> getObjectives_() {
        return objectives_;
    }

    public void addObjective(Objective objective){
        objectives_.add(objective);
    }

    public void setObjectiveCompleted(Objective objective){
        ++nbCompletedObjective_;
        points_ += objective.getPoint();
    }

    public void addBanbou(Bambou bambou){
        bambous.add(bambou);
    }

    public void setBambous(ArrayList<Bambou> bambous){
        this.bambous = bambous;
    }

    public ArrayList<Bambou> getBambous(){
        return bambous;
    }
}
