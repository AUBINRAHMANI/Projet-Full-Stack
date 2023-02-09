package fr.cotedazur.univ.polytech.startingpoint.statistique_manager;

import fr.cotedazur.univ.polytech.startingpoint.bot.BotProfile;
import fr.cotedazur.univ.polytech.startingpoint.bot.Playable;
import fr.cotedazur.univ.polytech.startingpoint.logger.Loggeable;

import java.util.ArrayList;
import java.util.List;

public class StatisticManager implements Loggeable {

    private final List<BotStatisticProfile> botsStatisticsProfiles;
    private int nbOfDrawGames;


    public StatisticManager() {
        this.botsStatisticsProfiles = new ArrayList<>();
        this.nbOfDrawGames = 0;
    }

    public void addDrawGame() {
        this.nbOfDrawGames += 1;
    }

    public void initBotsStatisticsProfiles(List<BotProfile> botProfiles) {
        for (BotProfile botProfile : botProfiles) {
            botsStatisticsProfiles.add(new BotStatisticProfile(botProfile.getBot(), botProfile.getBotName()));
        }
    }

    public void addWinner(Playable bot) {
        for (BotStatisticProfile botStatisticProfile : botsStatisticsProfiles) {
            if (botStatisticProfile.getBot() == bot) {
                botStatisticProfile.addVictory();
            }
        }
    }

    public void addRound() {
        for (BotStatisticProfile botStatisticProfile : botsStatisticsProfiles) {
            botStatisticProfile.addNbOfRounds();
        }

    }

    public void addLoser(Playable bot) {
        for (BotStatisticProfile botStatisticProfile : botsStatisticsProfiles)
            if (botStatisticProfile.getBot() == bot) {
                botStatisticProfile.addDefeat();
            }

    }

    public void addGame() {
        for (BotStatisticProfile botStatisticProfile : botsStatisticsProfiles) {
            botStatisticProfile.addNumberOfGames();
        }
    }

    public int getNbOfDrawGames() {
        return this.nbOfDrawGames;
    }
    public List<BotStatisticProfile> getBotsStatisticsProfiles() {
        return this.botsStatisticsProfiles;
    }

    @Override
    public String toString() {
        return "StatisticManager{" +
                "botsStatisticsProfiles=" + botsStatisticsProfiles + "\n"
                + ", NumberOfDrawGames=" + nbOfDrawGames +
                '}';
    }
}
