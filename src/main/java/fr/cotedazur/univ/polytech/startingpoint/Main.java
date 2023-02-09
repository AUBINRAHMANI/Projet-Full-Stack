package fr.cotedazur.univ.polytech.startingpoint;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import fr.cotedazur.univ.polytech.startingpoint.bot.BotMbappe;
import fr.cotedazur.univ.polytech.startingpoint.bot.BotProfile;
import fr.cotedazur.univ.polytech.startingpoint.bot.BotSprint;
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
    private Boolean DEMO = false;
    @Parameter(names = "--2thousand", description = "2* 1000 simulations")
    private Boolean TWO_THOUSANDS = false;
    @Parameter(names = "--demo --warning", description = "seul les logs liés a des problemes sont affichés")
    private Boolean DEMO_WARNING = false;
    @Parameter(names = "--demo --fine", description = "affiche les logs du resultat de la game ")
    private Boolean DEMO_FINE = false;
    @Parameter(names = "--demo --finer", description = "affiche les logs du resultat et objectif complété")
    private Boolean DEMO_FINER = false;
    @Parameter(names = "--demo --finest", description = "affiche tous les logs")
    private Boolean DEMO_FINEST = false;
    @Parameter(names = "--2thousand --warning", description = "seul les logs liés a des problemes sont affichés")
    private Boolean TWO_THOUSAND_WARNING = false;
    @Parameter(names = "--2thousand --fine", description = "affiche les logs du resultat de la game ")
    private Boolean TWO_THOUSAND_FINE = false;
    @Parameter(names = "--2thousand --finer", description = "affiche les logs du resultat et objectif complété")
    private Boolean TWO_THOUSAND_FINER = false;
    @Parameter(names = "--2thousand --finest", description = "affiche tous les logs")
    private Boolean TWO_THOUSAND_FINEST = false;
    @Parameter(names = "--2thousand --config", description = "affiche le nombre de parties")
    private Boolean TWO_THOUSAND_CONFIG = false;
    @Parameter(names = "--csv", description = "Lancement d’une simulation à plusieurs parties")

    private Boolean CSV = false;

    public static void main(String... argv) {
        Main main = new Main();
        StatisticManager statisticManager = new StatisticManager();
        CSVManager csvManager = new CSVManager();
        List<BotProfile> players = new ArrayList<>();
        players.add(new BotProfile(new BotMbappe(), "bot 1"));
        players.add(new BotProfile(new BotSprint(), "bot 2"));
        statisticManager.initBotsStatisticsProfiles(players);
        JCommander.newBuilder().addObject(main).build().parse(argv);

        if (Boolean.TRUE.equals(main.getCSV())) {
            startNGame(statisticManager, players, Level.CONFIG, 10);
            Path path = Paths.get(".", "stats", "statistic.csv");
            csvManager.exportData(statisticManager.getBotsStatisticsProfiles(), statisticManager.getNbOfDrawGames(), path.toString());
        }
        else if (Boolean.TRUE.equals(main.getTwoThousandConfig())) {
            startNGame(statisticManager, players, Level.CONFIG, 2000);
        }
        else if (Boolean.TRUE.equals(main.getDemo())) {
            startNGame(statisticManager, players, Level.FINEST, 1);
        }
        else if (Boolean.TRUE.equals(main.getTwoThousand())) {
            startNGame(statisticManager, players, Level.WARNING, 2000);
        }
        else if (Boolean.TRUE.equals(main.getDemoWarning())) {
            startNGame(statisticManager, players, Level.WARNING, 1);
        }
        else if (Boolean.TRUE.equals(main.getDemoFine())) {
            startNGame(statisticManager, players, Level.FINE, 1);
        }
        else if (Boolean.TRUE.equals(main.getDemoFiner())) {
            startNGame(statisticManager, players, Level.FINER, 1);
        }
        else if (Boolean.TRUE.equals(main.getDemoFinest())) {
            startNGame(statisticManager, players, Level.FINEST, 1);
        }
        else if (Boolean.TRUE.equals(main.getTwoThousandWarning())) {
            startNGame(statisticManager, players, Level.WARNING, 2000);
        }
        else if (Boolean.TRUE.equals(main.getTwoThousandFine())) {
            startNGame(statisticManager, players, Level.FINE, 2000);
        }
        else if (Boolean.TRUE.equals(main.getTwoThousandFiner())) {
            startNGame(statisticManager, players, Level.FINER, 2000);
        }
        else if (Boolean.TRUE.equals(main.getTwoThousandFinest())) {
            startNGame(statisticManager, players, Level.FINEST, 2000);
        }
    }
    private static void startNGame(StatisticManager statisticManager, List<BotProfile> players, Level level, int n) {
        Loggeable.initLogger(level);
        for (int i = 0; i < n; ++i) {
            LOGGER.log(level, "Game {}", i);
            Game game = new Game(statisticManager, players, false);
            game.start();
            for (BotProfile botProfile : players) {
                botProfile.resetPoints();
            }
        }
    }

    public Boolean getTwoThousandConfig() {
        return TWO_THOUSAND_CONFIG;
    }

    public Boolean getDemo() {
        return DEMO;
    }

    public Boolean getTwoThousand() {
        return TWO_THOUSANDS;
    }

    public Boolean getDemoWarning() {
        return DEMO_WARNING;
    }

    public Boolean getDemoFine() {
        return DEMO_FINE;
    }

    public Boolean getDemoFiner() {
        return DEMO_FINER;
    }

    public Boolean getDemoFinest() {
        return DEMO_FINEST;
    }

    public Boolean getTwoThousandWarning() {
        return TWO_THOUSAND_WARNING;
    }

    public Boolean getTwoThousandFine() {
        return TWO_THOUSAND_FINE;
    }

    public Boolean getTwoThousandFiner() {
        return TWO_THOUSAND_FINER;
    }

    public Boolean getTwoThousandFinest() {
        return TWO_THOUSAND_FINEST;
    }

    public Boolean getCSV() {
        return CSV;
    }
}
