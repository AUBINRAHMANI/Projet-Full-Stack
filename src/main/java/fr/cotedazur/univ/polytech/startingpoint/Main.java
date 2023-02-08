package fr.cotedazur.univ.polytech.startingpoint;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import fr.cotedazur.univ.polytech.startingpoint.bot.BotMbappe;
import fr.cotedazur.univ.polytech.startingpoint.bot.BotProfil;
import fr.cotedazur.univ.polytech.startingpoint.bot.BotSprint;
import fr.cotedazur.univ.polytech.startingpoint.game.Game;
import fr.cotedazur.univ.polytech.startingpoint.logger.Loggeable;
import fr.cotedazur.univ.polytech.startingpoint.statistique_manager.StatistiqueManager;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

public class Main implements Loggeable {
    @Parameter(names = "--demo", description = "Demo 1 fois avec les logs précis")
    private static final Boolean DEMO = false;
    @Parameter(names = "--2thousand", description = "2* 1000 simulations")
    private static final Boolean TWO_THOUSANDS = false;
    @Parameter(names = "--demo --warning", description = "seul les logs liés a des problemes sont affichés")
    private static final Boolean DEMOWARNING = false;
    @Parameter(names = "--demo --fine", description = "affiche les logs du resultat de la game ")
    private static final Boolean DEMOFINE = false;
    @Parameter(names = "--demo --finer", description = "affiche les logs du resultat et objectif complété")
    private static final Boolean DEMOFINER = false;
    @Parameter(names = "--demo --finest", description = "affiche tous les logs")
    private static final Boolean DEMOFINEST = false;
    @Parameter(names = "--2thousand --warning", description = "seul les logs liés a des problemes sont affichés")
    private static final Boolean TWO_THOUSANDS_WARNING = false;
    @Parameter(names = "--2thousand --fine", description = "affiche les logs du resultat de la game ")
    private static final Boolean TWO_THOUSANDS_FINE = false;
    @Parameter(names = "--2thousand --finer", description = "affiche les logs du resultat et objectif complété")
    private static final Boolean TWO_THOUSANDS_FINER = false;
    @Parameter(names = "--2thousand --finest", description = "affiche tous les logs")
    private static final Boolean TWO_THOUSANDS_FINEST = false;
    @Parameter(names = "--2thousand --config", description = "affiche le nombre de parties")
    private static final Boolean TWO_THOUSANDS_CONFIG = false;
    @Parameter(names = "--csv", description = "Lancement d’une simulation à plusieurs parties")
    private static final Boolean CSV = false;

    public static void main(String... argv) {
        Main main = new Main();
        StatistiqueManager statistiqueManager = new StatistiqueManager();
        List<BotProfil> players = new ArrayList<>();
        players.add(new BotProfil(new BotMbappe(), "bot 1"));
        players.add(new BotProfil(new BotSprint(), "bot 2"));
        statistiqueManager.initBotsStatistiquesProfiles(players);
        JCommander.newBuilder().addObject(main).build().parse(argv);

        if (main.getCsv()) {
            for (int i = 0; i < 10; ++i) {
                LOGGER.log(Level.CONFIG,"Game {}", i);
                Game game = new Game(statistiqueManager, players, false);
                game.start();
            }
        }
        if (main.getTwoThousandConfig()) {
            Loggeable.initLogger(Level.CONFIG);
            for (int i = 0; i < 2000; ++i) {
                LOGGER.log(Level.CONFIG,"Game {}", i);
                Game game = new Game(statistiqueManager, players, false);
                game.start();
                for (BotProfil botProfil : players) {
                    botProfil.resetPoints();
                }
            }
        }
        if (main.getDemo()) {
            Loggeable.initLogger(Level.FINEST);
            Game game = new Game(statistiqueManager, players, false);
            game.start();
        }
        if (main.getTwoThousand()) {
            Loggeable.initLogger(Level.WARNING);
            for (int i = 0; i < 2000; ++i) {
                LOGGER.log(Level.CONFIG,"Game {}" , i);
                Game game = new Game(statistiqueManager, players, false);
                game.start();
                for (BotProfil botProfil : players) {
                    botProfil.resetPoints();
                }
            }
        }
        if (main.getDemoWarning()) {
            Loggeable.initLogger(Level.WARNING);
            Game game = new Game(statistiqueManager, players, false);
            game.start();
        }
        if (main.getDemoFine()) {
            Loggeable.initLogger(Level.FINE);
            Game game = new Game(statistiqueManager, players, false);
            game.start();
        }
        if (main.getDemoFiner()) {
            Loggeable.initLogger(Level.FINER);
            Game game = new Game(statistiqueManager, players, false);
            game.start();
        }
        if (main.getDemoFinest()) {
            Loggeable.initLogger(Level.FINEST);
            Game game = new Game(statistiqueManager, players, false);
            game.start();
        }
        if (main.getTwoThousandWarning()) {
            Loggeable.initLogger(Level.WARNING);
            for (int i = 0; i < 2000; ++i) {
                LOGGER.log(Level.CONFIG,"Game {}", i);
                Game game = new Game(statistiqueManager, players, false);
                game.start();
                for (BotProfil botProfil : players) {
                    botProfil.resetPoints();
                }
            }
        }
        if (main.getTwoThousandFine()) {
            Loggeable.initLogger(Level.FINE);
            for (int i = 0; i < 2000; ++i) {
                LOGGER.log(Level.CONFIG,"Game {}", i);
                Game game = new Game(statistiqueManager, players, false);
                game.start();
                for (BotProfil botProfil : players) {
                    botProfil.resetPoints();
                }
            }
        }
        if (main.getTwoThousandFiner()) {
            Loggeable.initLogger(Level.FINER);
            for (int i = 0; i < 2000; ++i) {
                LOGGER.log(Level.CONFIG,"Game {}", i);
                Game game = new Game(statistiqueManager, players, false);
                game.start();
                for (BotProfil botProfil : players) {
                    botProfil.resetPoints();
                }

            }
        }
        if (main.getTwoThousandFinest()) {
            Loggeable.initLogger(Level.FINEST);
            for (int i = 0; i < 2000; ++i) {
                LOGGER.log(Level.CONFIG,"Game {}", i);
                Game game = new Game(statistiqueManager, players, false);
                game.start();
                for (BotProfil botProfil : players) {
                    botProfil.resetPoints();
                }
            }
        }
    }

    public Boolean getTwoThousandConfig() {
        return TWO_THOUSANDS_CONFIG;
    }

    public Boolean getDemo() {
        return DEMO;
    }

    public Boolean getTwoThousand() {
        return TWO_THOUSANDS;
    }

    public Boolean getDemoWarning() {
        return DEMOWARNING;
    }

    public Boolean getDemoFine() {
        return DEMOFINE;
    }

    public Boolean getDemoFiner() {
        return DEMOFINER;
    }

    public Boolean getDemoFinest() {
        return DEMOFINEST;
    }

    public Boolean getTwoThousandWarning() {
        return TWO_THOUSANDS_WARNING;
    }

    public Boolean getTwoThousandFine() {
        return TWO_THOUSANDS_FINE;
    }

    public Boolean getTwoThousandFiner() {
        return TWO_THOUSANDS_FINER;
    }

    public Boolean getTwoThousandFinest() {
        return TWO_THOUSANDS_FINEST;
    }

    public Boolean getCsv() {
        return CSV;
    }
}
