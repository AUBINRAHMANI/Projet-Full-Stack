package fr.cotedazur.univ.polytech.startingpoint;

import fr.cotedazur.univ.polytech.startingpoint.game.Game;
import fr.cotedazur.univ.polytech.startingpoint.logger.Loggeable;

import java.util.logging.Level;

public class Main implements Loggeable {

    public static void main(String[] args) {
        Loggeable.initLogger(Level.CONFIG);
        Game game = new Game(false);
        game.start();
    }

}
