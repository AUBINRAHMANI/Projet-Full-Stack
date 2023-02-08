package fr.cotedazur.univ.polytech.startingpoint.statistique_manager;

import fr.cotedazur.univ.polytech.startingpoint.bot.Playable;

public class BotStatistiqueProfil {

    String botName;
    Playable bot;
    private int nbVictories;
    private int nbDefeats;

    private int nombredeTours;
    private int nombredeParties;


    public BotStatistiqueProfil(Playable bot, String botName) {
        this.bot = bot;
        this.botName = botName;
        this.nbVictories = 0;
        this.nbDefeats = 0;
        this.nombredeTours = 0;
        this.nombredeParties = 0;
    }

    public void addVictory() {
        nbVictories += 1;
    }

    public void addDefeat() {
        nbDefeats += 1;
    }

    public void addNombredeTours() {
        ++nombredeTours;
    }

    public void addNumberOfGames() {
        ++nombredeParties;
    }

    public Playable getBot() {
        return bot;
    }

    public String getBotName() {
        return this.botName;
    }

    public int getNbVictories() {
        return nbVictories;
    }

    public int getNbDefeats() {
        return nbDefeats;
    }

    public int getNbdeTours() {
        return nombredeTours;
    }

    public int getNumberOfGames() {
        return nombredeParties;
    }

    public String toString() {
        return "Nombre de victoire de  " + this.getBotName() + ": " + this.getNbVictories() + "\n"
                + "Nombre de tours effectué : " + this.getNbdeTours() + " pour " + this.getNumberOfGames() + " de parties";

    }
}
