package fr.cotedazur.univ.polytech.startingpoint;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import fr.cotedazur.univ.polytech.startingpoint.bots.BotMbappe;
import fr.cotedazur.univ.polytech.startingpoint.bots.BotProfile;
import fr.cotedazur.univ.polytech.startingpoint.bots.BotSprint;
import fr.cotedazur.univ.polytech.startingpoint.game.Game;
import fr.cotedazur.univ.polytech.startingpoint.logger.Loggeable;
import fr.cotedazur.univ.polytech.startingpoint.statistique_manager.StatisticManager;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

public class Main implements Loggeable {
    @Parameter(names = "--demo", description = "Demo 1 fois avec les logs précis")
    private Boolean demo = false;
    @Parameter(names = "--2thousands", description = "2* 1000 simulations")
    private Boolean twoThousands = false;
    @Parameter(names = "--demo --warning", description = "seul les logs liés a des problemes sont affichés")
    private Boolean demoWarning = false;
    @Parameter(names = "--demo --fine", description = "affiche les logs du resultat de la game ")
    private Boolean demoFine = false;
    @Parameter(names = "--demo --finer", description = "affiche les logs du resultat et objectif complété")
    private Boolean demoFiner = false;
    @Parameter(names = "--demo --finest", description = "affiche tous les logs")
    private Boolean demoFinest = false;
    @Parameter(names = "--2thousand --warning", description = "seul les logs liés a des problemes sont affichés")
    private Boolean twoThousandWarning = false;
    @Parameter(names = "--2thousand --fine", description = "affiche les logs du resultat de la game ")
    private Boolean twoThousandFine = false;
    @Parameter(names = "--2thousand --finer", description = "affiche les logs du resultat et objectif complété")
    private Boolean twoThousandFiner = false;
    @Parameter(names = "--2thousand --finest", description = "affiche tous les logs")
    private Boolean twoThousandFinest = false;
    @Parameter(names = "--2thousand --config", description = "affiche le nombre de parties")
    private Boolean twoThousandConfig = false;
    @Parameter(names = "--csv", description = "Lancement d’une simulation à plusieurs parties")
    private Boolean csv = false;

    public static void main(String... argv) {
        Main main = new Main();
        StatisticManager statisticManager = new StatisticManager();
        CSVManager csvManager = new CSVManager();
        List<BotProfile> players = new ArrayList<>();
        players.add(new BotProfile(new BotMbappe(), "bot 1"));
        players.add(new BotProfile(new BotSprint(), "bot 2"));
        statisticManager.initBotsStatisticsProfiles(players);
        JCommander.newBuilder().addObject(main).build().parse(argv);

        if (Boolean.TRUE.equals(main.csv)) {
            startNGame(statisticManager, players, Level.CONFIG, 10);
            Path path = Paths.get(".", "stats", "statistic.csv");
            csvManager.exportData(statisticManager.getBotsStatisticsProfiles(), statisticManager.getNbOfDrawGames(), path.toString());
        } else if (main.twoThousandConfig) {
            startNGame(statisticManager, players, Level.CONFIG, 2000);
        } else if (main.demo) {
            startNGame(statisticManager, players, Level.FINEST, 1);
        } else if (main.twoThousands) {
            startNGame(statisticManager, players, Level.WARNING, 2000);
        } else if (main.demoWarning) {
            startNGame(statisticManager, players, Level.WARNING, 1);
        } else if (main.demoFine) {
            startNGame(statisticManager, players, Level.FINE, 1);
        } else if (main.demoFiner) {
            startNGame(statisticManager, players, Level.FINER, 1);
        } else if (main.demoFinest) {
            startNGame(statisticManager, players, Level.FINEST, 1);
        } else if (main.twoThousandWarning) {
            startNGame(statisticManager, players, Level.WARNING, 2000);
        } else if (main.twoThousandFine) {
            startNGame(statisticManager, players, Level.FINE, 2000);
        } else if (main.twoThousandFiner) {
            startNGame(statisticManager, players, Level.FINER, 2000);
        } else if (main.twoThousandFinest) {
            startNGame(statisticManager, players, Level.FINEST, 2000);
        }
    }

    private static void startNGame(StatisticManager statisticManager, List<BotProfile> players, Level level, int n) {
        Loggeable.initLogger(level);
        for (int i = 0; i < n; ++i) {
            LOGGER.log(Level.CONFIG, "Game {0}", i);
            Game game = new Game(statisticManager, players);
            game.start();
            for (BotProfile botProfile : players) {
                botProfile.resetPoints();
            }
        }
        System.out.println();
        LOGGER.config(statisticManager.toString());
    }
}


