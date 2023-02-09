package fr.cotedazur.univ.polytech.startingpoint;

import fr.cotedazur.univ.polytech.startingpoint.bot.BotProfil;
import fr.cotedazur.univ.polytech.startingpoint.bot.BotSprint;
import fr.cotedazur.univ.polytech.startingpoint.game.Game;
import fr.cotedazur.univ.polytech.startingpoint.logger.Loggeable;
import fr.cotedazur.univ.polytech.startingpoint.statistique_manager.StatisticManager;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

public class ThousandGameLoop implements Loggeable {
    public static void main(String[] args) {

        StatisticManager statisticManager = new StatisticManager();
        CSVManager csvManager = new CSVManager();
        Loggeable.initLogger(Level.CONFIG);

        List<BotProfil> players = new ArrayList<>();
        BotProfil bob1 = new BotProfil(new BotSprint(), "Sprint");
        BotProfil bob2 = new BotProfil(new BotSprint(), "Mbappe");
        players.add(bob1);
        players.add(bob2);

        statisticManager.initBotsStatisticsProfiles(players);

        for (int i = 0; i < 100; ++i) {
            LOGGER.log(Level.CONFIG , "Game {}", i);
            Game game = new Game(statisticManager, players, false);
            game.start();

            for (BotProfil botProfil : players) {
                botProfil.resetPoints();
            }
        }
        Path path = Paths.get(".", "stats", "statistic.csv");
        csvManager.exportData(statisticManager.getBotsStatisticsProfiles(), statisticManager.getMatchNul(), path.toString());
        //LOGGER.config(statisticManager.toString());
    }
}
