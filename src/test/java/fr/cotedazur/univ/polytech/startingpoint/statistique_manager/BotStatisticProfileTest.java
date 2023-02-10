package fr.cotedazur.univ.polytech.startingpoint.statistique_manager;

import fr.cotedazur.univ.polytech.startingpoint.bots.BotSprint;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BotStatisticProfileTest {

    @Test
    void addVictory() {
        BotSprint botSprint = new BotSprint();
        BotStatisticProfile botStatisticProfile = new BotStatisticProfile(botSprint, "BotSprint");
        botStatisticProfile.addVictory();
        assertEquals(1, botStatisticProfile.getNbVictories());
    }

    @Test
    void addDefeat() {
        BotSprint botSprint = new BotSprint();
        BotStatisticProfile botStatisticProfile = new BotStatisticProfile(botSprint, "BotSprint");
        botStatisticProfile.addDefeat();
        assertEquals(1, botStatisticProfile.getNbDefeats());
    }

    @Test
    void addNbOfRounds() {
        BotSprint botSprint = new BotSprint();
        BotStatisticProfile botStatisticProfile = new BotStatisticProfile(botSprint, "BotSprint");
        botStatisticProfile.addNbOfRounds();
        assertEquals(1, botStatisticProfile.getNbOfRounds());
    }

    @Test
    void addNumberOfGames() {
        BotSprint botSprint = new BotSprint();
        BotStatisticProfile botStatisticProfile = new BotStatisticProfile(botSprint, "BotSprint");
        botStatisticProfile.addNumberOfGames();
        assertEquals(1, botStatisticProfile.getNbOfGames());
    }

    @Test
    void getBot() {
        BotSprint botSprint = new BotSprint();
        BotStatisticProfile botStatisticProfile = new BotStatisticProfile(botSprint, "BotSprint");
        assertEquals(botSprint, botStatisticProfile.getBot());
    }

    @Test
    void getBotName() {
        BotSprint botSprint = new BotSprint();
        BotStatisticProfile botStatisticProfile = new BotStatisticProfile(botSprint, "BotSprint");
        assertEquals("BotSprint", botStatisticProfile.getBotName());
    }

    @Test
    void getNbVictories() {
        BotSprint botSprint = new BotSprint();
        BotStatisticProfile botStatisticProfile = new BotStatisticProfile(botSprint, "BotSprint");
        botStatisticProfile.addVictory();
        botStatisticProfile.addVictory();
        assertEquals(2, botStatisticProfile.getNbVictories());
    }

    @Test
    void getNbDefeats() {
        BotSprint botSprint = new BotSprint();
        BotStatisticProfile botStatisticProfile = new BotStatisticProfile(botSprint, "BotSprint");
        botStatisticProfile.addDefeat();
        botStatisticProfile.addDefeat();
        assertEquals(2, botStatisticProfile.getNbDefeats());
    }

    @Test
    void getNbOfRounds() {
        BotSprint botSprint = new BotSprint();
        BotStatisticProfile botStatisticProfile = new BotStatisticProfile(botSprint, "BotSprint");
        botStatisticProfile.addNbOfRounds();
        botStatisticProfile.addNbOfRounds();
        assertEquals(2, botStatisticProfile.getNbOfRounds());
    }

    @Test
    void getNbOfGames() {
        BotSprint botSprint = new BotSprint();
        BotStatisticProfile botStatisticProfile = new BotStatisticProfile(botSprint, "BotSprint");
        botStatisticProfile.addNumberOfGames();
        botStatisticProfile.addNumberOfGames();
        assertEquals(2, botStatisticProfile.getNbOfGames());
    }
}