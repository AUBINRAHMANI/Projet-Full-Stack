package fr.cotedazur.univ.polytech.startingpoint.statistique_manager;

import fr.cotedazur.univ.polytech.startingpoint.bot.Playable;

public class BotStatisticProfile {

    String botName;
    Playable bot;
    private int nbVictories;
    private int nbDefeats;

    private int nbOfRounds;
    private int nbOfGame;


    public BotStatisticProfile(Playable bot, String botName) {
        this.bot = bot;
        this.botName = botName;
        this.nbVictories = 0;
        this.nbDefeats = 0;
        this.nbOfRounds = 0;
        this.nbOfGame = 0;
    }

    public void addVictory() {
        nbVictories += 1;
    }

    public void addDefeat() {
        nbDefeats += 1;
    }

    public void addNbOfRounds() {
        ++nbOfRounds;
    }

    public void addNumberOfGames() {
        ++nbOfGame;
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

    public int getNbOfRounds() {
        return nbOfRounds;
    }

    public int getNumberOfGames() {
        return nbOfGame;
    }

    public String toString() {
        return "Number of wins  " + this.getBotName() + ": " + this.getNbVictories() + "\n"
                + "Number of rounds done : " + this.getNbOfRounds() + " for " + this.getNumberOfGames() + " games";

    }
}
