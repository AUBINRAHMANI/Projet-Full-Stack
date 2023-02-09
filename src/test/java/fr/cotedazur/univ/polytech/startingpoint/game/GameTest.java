package fr.cotedazur.univ.polytech.startingpoint.game;

import fr.cotedazur.univ.polytech.startingpoint.*;
import fr.cotedazur.univ.polytech.startingpoint.bot.BotMbappe;
import fr.cotedazur.univ.polytech.startingpoint.bot.BotProfile;
import fr.cotedazur.univ.polytech.startingpoint.bot.BotSprint;
import fr.cotedazur.univ.polytech.startingpoint.logger.Loggeable;
import fr.cotedazur.univ.polytech.startingpoint.objective.Objective;
import fr.cotedazur.univ.polytech.startingpoint.statistique_manager.BotStatisticProfile;
import fr.cotedazur.univ.polytech.startingpoint.statistique_manager.StatisticManager;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {

    @Test
    void start() {
        StatisticManager statisticManager = new StatisticManager();
        List<BotProfile> players = new ArrayList<>();
        BotProfile bob1 = new BotProfile(new BotSprint(), "Sprint");
        BotProfile bob2 = new BotProfile(new BotMbappe(), "Mbappe");
        players.add(bob1);
        players.add(bob2);
        statisticManager.initBotsStatisticsProfiles(players);
        Game game = new Game(statisticManager, players);
        game.start();
        if (game.checkWinner() == null) {
            assertEquals(1, statisticManager.getNbOfDrawGames());
        }
        else {
            for(BotStatisticProfile botProfile : statisticManager.getBotsStatisticsProfiles()){
                if(botProfile.getNbVictories() == 1){
                    assertEquals(botProfile.getBotName(), game.checkWinner().getBotName());
                } else if (botProfile.getNbVictories() == 0){
                    assertNotEquals(botProfile.getBotName(), game.checkWinner().getBotName());
                }
            }
            assertTrue((bob1.getNbCompletedObjective() >= 9) ^ (bob2.getNbCompletedObjective() >= 9));
        }
    }

    @Test
    void getPreviousActions() {
        StatisticManager statisticManager = new StatisticManager();
        List<BotProfile> players = new ArrayList<>();
        BotProfile bob1 = new BotProfile(new BotSprint(), "Sprint");
        BotProfile bob2 = new BotProfile(new BotMbappe(), "Mbappe");
        players.add(bob1);
        players.add(bob2);
        statisticManager.initBotsStatisticsProfiles(players);
        Game game = new Game(statisticManager, players);
        game.start();
        assertTrue(game.getPreviousActions() != null);
    }

    /*@Test
    void emptyDeck() {
        //Deck<Objective> objectiveDeck, Deck<Plot> plotDeck, Map map
        Deck<Objective> objectiveDeck = new Deck<>();
        Deck<Plot> plotDeck = new Deck<>();
        Map map = new Map();
        GameEngine gameEngine = new GameEngine(objectiveDeck, plotDeck, map);
    }*/

    @Test
    void pickObjective() {
    }

    @Test
    void pickPlot() {
    }

    @Test
    void computeObjectivesPlot() {
    }

    @Test
    void computeObjectivesGardener() {
    }

    @Test
    void getGardenerPosition() {
    }

    @Test
    void getPandaPosition() {
    }

    @Test
    void computeObjectivesPanda() {
    }

    @Test
    void checkWinner() {
    }

    @Test
    void getMyObjectives() {
    }

    @Test
    void printWinner() {
    }

    @Test
    void getMyBamboos() {
    }

    @Test
    void addBambooToBot() {
    }

    @Test
    void getNumberOfPlayers() {
    }
}