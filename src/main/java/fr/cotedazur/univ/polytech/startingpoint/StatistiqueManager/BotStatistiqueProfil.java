package fr.cotedazur.univ.polytech.startingpoint.StatistiqueManager;

import fr.cotedazur.univ.polytech.startingpoint.bot.*;

import java.util.List;

public class BotStatistiqueProfil {

    Playable bot;
    String botName;
    private int nbVictories;
    private int nbDefeats;


    public BotStatistiqueProfil(Playable bot, String botName){
        this.bot=bot;
        this.botName=botName;
        this.nbVictories = 0;
        this.nbDefeats = 0;
    }

    public void addVictory(){
        nbVictories+=1;
    }
    public void addDefeat() {
        nbDefeats+=1;
    }

    public Playable getBot() {
        return bot;
    }
    public String getBotName(){
        return this.botName;
    }

    public int getNbVictories() {
        return nbVictories;
    }

    public int getNbDefeats() {
        return nbDefeats;
    }

    public String toString(){
        return "Nombre de victoire de " + this.getBotName() + ": " + this.getNbVictories();
    }

}
