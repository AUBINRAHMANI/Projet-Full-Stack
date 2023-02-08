package fr.cotedazur.univ.polytech.startingpoint;

import fr.cotedazur.univ.polytech.startingpoint.game.Game;
import fr.cotedazur.univ.polytech.startingpoint.logger.Loggeable;

import java.util.logging.Level;

public class ThousandGameLoop implements Loggeable {

    public static void main(String[] args) {
        Loggeable.initLogger(Level.CONFIG);
        for(int i=0; i<10000 ;++i){
            LOGGER.config("Game "+i);
            Game game = new Game(false);
            game.start();
        }
    }
}
