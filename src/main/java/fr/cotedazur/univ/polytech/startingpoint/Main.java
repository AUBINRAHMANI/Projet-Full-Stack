package fr.cotedazur.univ.polytech.startingpoint;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import fr.cotedazur.univ.polytech.startingpoint.game.Game;
import fr.cotedazur.univ.polytech.startingpoint.logger.Loggeable;

import java.util.logging.Level;

public class Main implements Loggeable {
    @Parameter(names = "--demo", description = "Demo 1 fois avec les logs précis")
    public Boolean demo = false;
    @Parameter(names = "--2thousands", description = "2* 1000 simulations")
    public Boolean twoThousands = false;
    @Parameter(names = "--warning", description = "seul les logs liés a des problemes sont affichés")
    public Boolean warning = false;
    @Parameter(names = "--fine", description = "affiche les logs du resultat de la game ")
    public Boolean fine = false;
    @Parameter(names = "--finer", description = "affiche les logs du resultat et objectif complété")
    public Boolean finer = false;
    @Parameter(names = "--finest", description = "affiche tous les logs")
    public Boolean finest = false;

    public Boolean getDemo() {
        return demo;
    }

    public Boolean getWarning() {
        return warning;
    }

    public Boolean getFine() {
        return fine;
    }

    public Boolean getFiner() {
        return finer;
    }

    public Boolean getFinest() {
        return finest;
    }


    public Boolean getTwoThousands() {
        return twoThousands;
    }

    public static void main(String... argv) {
        Main main = new Main();
        JCommander.newBuilder().addObject(main).build().parse(argv);
        if (main.getDemo()) {
            if (main.getWarning()) {
                Loggeable.initLogger(Level.WARNING);
                Game game = new Game(false);
                game.start();
            }
            else if (main.getFine()) {
                Loggeable.initLogger(Level.FINE);
                Game game = new Game(false);
                game.start();
            }
            else if (main.getFiner()) {
                Loggeable.initLogger(Level.FINER);
                Game game = new Game(false);
                game.start();
            }
            else if (main.getFinest()) {
                Loggeable.initLogger(Level.FINEST);
                Game game = new Game(false);
                game.start();
            }
        }
        else if (main.getTwoThousands()) {
            if (main.getWarning()) {
                Loggeable.initLogger(Level.WARNING);
                for (int i = 0; i < 1000; ++i) {
                    LOGGER.fine("Game " + i);
                    Game game = new Game(false);
                    game.start();
                }
            }
            else if (main.getFine()) {
                Loggeable.initLogger(Level.FINE);
                for (int i = 0; i < 1000; ++i) {
                    LOGGER.fine("Game " + i);
                    Game game = new Game(false);
                    game.start();
                }
            }
            else if (main.getFiner()) {
                Loggeable.initLogger(Level.FINER);
                for (int i = 0; i < 1000; ++i) {
                    LOGGER.fine("Game " + i);
                    Game game = new Game(false);
                    game.start();
                }
            }
            else if (main.getFinest()) {
                Loggeable.initLogger(Level.FINEST);
                for (int i = 0; i < 1000; ++i) {
                    LOGGER.fine("Game " + i);
                    Game game = new Game(false);
                    game.start();
                }

            }
        }

    }
}