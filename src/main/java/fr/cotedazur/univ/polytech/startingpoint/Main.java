package fr.cotedazur.univ.polytech.startingpoint;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import fr.cotedazur.univ.polytech.startingpoint.game.Game;
import fr.cotedazur.univ.polytech.startingpoint.logger.Loggeable;

import java.util.logging.Level;

public class Main implements Loggeable {
    @Parameter(names = "--demo", description = "Demo 1 fois avec les logs précis")
    private Boolean demo = false;
    @Parameter(names = "--2thousand", description = "2* 1000 simulations")
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

    public Boolean getDemo() {
        return demo;
    }

    public Boolean getTwoThousand() {
        return twoThousands;
    }

    public Boolean getDemoWarning() {
        return demoWarning;
    }

    public Boolean getDemoFine() {
        return demoFine;
    }

    public Boolean getDemoFiner() {
        return demoFiner;
    }

    public Boolean getDemoFinest() {
        return demoFinest;
    }

    public Boolean getTwoThousandWarning() {
        return twoThousandWarning;
    }

    public Boolean getTwoThousandFine() {
        return twoThousandFine;
    }

    public Boolean getTwoThousandFiner() {
        return twoThousandFiner;
    }

    public Boolean getTwoThousandFinest() {
        return twoThousandFinest;
    }

    @Parameter(names = "--csv", description = "Lancement d’une simulation à plusieurs parties (pas forcément 1000) avec\n" +
            "relecture de \"stats/gamestats.csv” s’il existe et ajout des nouvelles statistiques")
    private Boolean csv = false;

    public Boolean getCsv() {
        return csv;
    }

    public static void main(String... argv) {
        Main main = new Main();
        JCommander.newBuilder().addObject(main).build().parse(argv);
        if (main.getCsv()) {

            for (int i = 0; i < 10; ++i) {
                LOGGER.fine("Game " + i);
                Game game = new Game(false);
                game.start();
            }
        }
        if (main.getDemo()) {
            Loggeable.initLogger(Level.FINEST);
            Game game = new Game(false);
            game.start();
        }
        if (main.getTwoThousand()) {
            Loggeable.initLogger(Level.WARNING);
            for (int i = 0; i < 1000; ++i) {
                LOGGER.fine("Game " + i);
                Game game = new Game(false);
                game.start();
            }
        }
        if (main.getDemoWarning()) {
            Loggeable.initLogger(Level.WARNING);
            Game game = new Game(false);
            game.start();
        }
        if (main.getDemoFine()) {
            Loggeable.initLogger(Level.FINE);
            Game game = new Game(false);
            game.start();
        }
        if (main.getDemoFiner()) {
            Loggeable.initLogger(Level.FINER);
            Game game = new Game(false);
            game.start();
        }
        if (main.getDemoFinest()) {
            Loggeable.initLogger(Level.FINEST);
            Game game = new Game(false);
            game.start();
        }
        if (main.getTwoThousandWarning()) {
            Loggeable.initLogger(Level.WARNING);
            for (int i = 0; i < 1000; ++i) {
                LOGGER.fine("Game " + i);
                Game game = new Game(false);
                game.start();
            }
        }
        if (main.getTwoThousandFine()) {
            Loggeable.initLogger(Level.FINE);
            for (int i = 0; i < 1000; ++i) {
                LOGGER.fine("Game " + i);
                Game game = new Game(false);
                game.start();
            }
        }
        if (main.getTwoThousandFiner()) {
            Loggeable.initLogger(Level.FINER);
            for (int i = 0; i < 1000; ++i) {
                LOGGER.fine("Game " + i);
                Game game = new Game(false);
                game.start();
            }
        }
        if (main.getTwoThousandFinest()) {
            Loggeable.initLogger(Level.FINEST);
            for (int i = 0; i < 1000; ++i) {
                LOGGER.fine("Game " + i);
                Game game = new Game(false);
                game.start();
            }
        }

    }
}