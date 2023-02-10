package fr.cotedazur.univ.polytech.startingpoint.statistique_manager;

import fr.cotedazur.univ.polytech.startingpoint.bots.BotMbappe;
import fr.cotedazur.univ.polytech.startingpoint.bots.BotProfile;
import fr.cotedazur.univ.polytech.startingpoint.bots.BotSprint;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class StatisticManagerTest {

    @Test
    void addMatchNul() {
        BotSprint botSprint = new BotSprint();
        BotMbappe botMbappe = new BotMbappe();
        BotProfile botProfile = new BotProfile(botSprint, "BotSprint");
        BotProfile botProfile2 = new BotProfile(botMbappe, "BotMbappe");
        List<BotProfile> botStatisticProfiles = List.of(botProfile, botProfile2);
        StatisticManager statisticManager = new StatisticManager();
        statisticManager.initBotsStatisticsProfiles(botStatisticProfiles);
        statisticManager.addDrawGame();
        assertEquals(1, statisticManager.getNbOfDrawGames());
    }

    @Test
    void initBotsStatisticsProfiles() {
        BotSprint botSprint = new BotSprint();
        BotMbappe botMbappe = new BotMbappe();
        BotProfile botProfile = new BotProfile(botSprint, "BotSprint");
        BotProfile botProfile2 = new BotProfile(botMbappe, "BotMbappe");
        List<BotProfile> botStatisticProfiles = List.of(botProfile, botProfile2);
        StatisticManager statisticManager = new StatisticManager();
        statisticManager.initBotsStatisticsProfiles(botStatisticProfiles);
        assertEquals(2, statisticManager.getBotsStatisticsProfiles().size());
    }

    @Test
    void addWinner() {
        BotSprint botSprint = new BotSprint();
        BotMbappe botMbappe = new BotMbappe();
        BotProfile botProfile1 = new BotProfile(botSprint, "BotSprint");
        BotProfile botProfile2 = new BotProfile(botMbappe, "BotMbappe");
        List<BotProfile> botStatisticProfiles = List.of(botProfile1, botProfile2);
        StatisticManager statisticManager = new StatisticManager();
        statisticManager.initBotsStatisticsProfiles(botStatisticProfiles);
        statisticManager.addWinner(botSprint);
        assertEquals(1, statisticManager.getBotsStatisticsProfiles().get(0).getNbVictories());

    }

    @Test
    void addRound() {
        BotSprint botSprint = new BotSprint();
        BotMbappe botMbappe = new BotMbappe();
        BotProfile botProfile1 = new BotProfile(botSprint, "BotSprint");
        BotProfile botProfile2 = new BotProfile(botMbappe, "BotMbappe");
        List<BotProfile> listOfBotProfiles = List.of(botProfile1, botProfile2);
        StatisticManager statisticManager = new StatisticManager();
        statisticManager.initBotsStatisticsProfiles(listOfBotProfiles);
        statisticManager.addRound();
        assertEquals(1, statisticManager.getBotsStatisticsProfiles().get(0).getNbOfRounds());
    }

    @Test
    void addLoser() {
        BotSprint botSprint = new BotSprint();
        BotMbappe botMbappe = new BotMbappe();
        BotProfile botProfile1 = new BotProfile(botSprint, "BotSprint");
        BotProfile botProfile2 = new BotProfile(botMbappe, "BotMbappe");
        List<BotProfile> listOfBotProfiles = List.of(botProfile1, botProfile2);
        StatisticManager statisticManager = new StatisticManager();
        statisticManager.initBotsStatisticsProfiles(listOfBotProfiles);
        statisticManager.addLoser(botSprint);
        assertEquals(1, statisticManager.getBotsStatisticsProfiles().get(0).getNbDefeats());
    }

    @Test
    void addGame() {
        BotSprint botSprint = new BotSprint();
        BotMbappe botMbappe = new BotMbappe();
        BotProfile botProfile1 = new BotProfile(botSprint, "BotSprint");
        BotProfile botProfile2 = new BotProfile(botMbappe, "BotMbappe");
        List<BotProfile> listOfBotProfiles = List.of(botProfile1, botProfile2);
        StatisticManager statisticManager = new StatisticManager();
        statisticManager.initBotsStatisticsProfiles(listOfBotProfiles);
        statisticManager.addGame();
        assertEquals(1, statisticManager.getBotsStatisticsProfiles().get(0).getNbOfGames());
    }

    @Test
    void getNbOfDrawGames() {
        BotSprint botSprint = new BotSprint();
        BotMbappe botMbappe = new BotMbappe();
        BotProfile botProfile1 = new BotProfile(botSprint, "BotSprint");
        BotProfile botProfile2 = new BotProfile(botMbappe, "BotMbappe");
        List<BotProfile> listOfBotProfiles = List.of(botProfile1, botProfile2);
        StatisticManager statisticManager = new StatisticManager();
        statisticManager.initBotsStatisticsProfiles(listOfBotProfiles);
        statisticManager.addDrawGame();
        assertEquals(1, statisticManager.getNbOfDrawGames());
    }

    @Test
    void getBotsStatisticsProfiles() {
        BotSprint botSprint = new BotSprint();
        BotMbappe botMbappe = new BotMbappe();
        BotProfile botProfile1 = new BotProfile(botSprint, "BotSprint");
        BotProfile botProfile2 = new BotProfile(botMbappe, "BotMbappe");
        List<BotProfile> listOfBotProfiles = List.of(botProfile1, botProfile2);
        StatisticManager statisticManager = new StatisticManager();
        statisticManager.initBotsStatisticsProfiles(listOfBotProfiles);
        assertEquals(2, statisticManager.getBotsStatisticsProfiles().size());
        assertEquals(botSprint, statisticManager.getBotsStatisticsProfiles().get(0).getBot());
    }
}