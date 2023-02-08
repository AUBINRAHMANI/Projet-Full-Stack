package fr.cotedazur.univ.polytech.startingpoint.statistique_manager;

import fr.cotedazur.univ.polytech.startingpoint.bot.BotProfil;
import fr.cotedazur.univ.polytech.startingpoint.bot.Playable;
import fr.cotedazur.univ.polytech.startingpoint.logger.Loggeable;

import java.util.ArrayList;
import java.util.List;

public class StatistiqueManager implements Loggeable {

    private final List<BotStatistiqueProfil> botStatistiqueProfils;
    private int nombreMatchNul = 0;


    public StatistiqueManager() {
        this.botStatistiqueProfils = new ArrayList<>();
        this.nombreMatchNul = 0;
    }

    public void addMatchNul() {
        this.nombreMatchNul += 1;
    }

    public void initBotsStatistiquesProfiles(List<BotProfil> botProfils) {
        for (BotProfil botProfil : botProfils) {
            botStatistiqueProfils.add(new BotStatistiqueProfil(botProfil.getBot(), botProfil.getBotName()));
        }
    }

    public void addWinner(Playable bot) {
        for (BotStatistiqueProfil botStatistiqueProfil : botStatistiqueProfils) {
            if (botStatistiqueProfil.getBot() == bot) {
                botStatistiqueProfil.addVictory();
            }
        }
    }

    public void addTours() {
        for (BotStatistiqueProfil botStatistiqueProfil : botStatistiqueProfils) {
            botStatistiqueProfil.addNombredeTours();
        }

    }

    public void addLoser(Playable bot) {
        for (BotStatistiqueProfil botStatistiqueProfil : botStatistiqueProfils)
            if (botStatistiqueProfil.getBot() == bot) {
                botStatistiqueProfil.addDefeat();
            }

    }

    public void addNumberOfGame() {
        for (BotStatistiqueProfil botStatistiqueProfil : botStatistiqueProfils) {
            botStatistiqueProfil.addNumberOfGames();
        }
    }

    public int getMatchNul() {
        return this.nombreMatchNul;
    }
    public List<BotStatistiqueProfil> getBotStatistiqueProfils() {
        return this.botStatistiqueProfils;
    }

    @Override
    public String toString() {
        return "StatistiqueManager{" +
                "botStatistiqueProfils=" + botStatistiqueProfils + "\n"
                + ", NombreMatchNul=" + nombreMatchNul +
                '}';
    }
}
