package fr.cotedazur.univ.polytech.startingpoint;

import fr.cotedazur.univ.polytech.startingpoint.StatistiqueManager.StatistiqueManager;
import fr.cotedazur.univ.polytech.startingpoint.game.Game;
import fr.cotedazur.univ.polytech.startingpoint.logger.Loggeable;

import java.util.logging.Level;

public class ThousandGameLoop implements Loggeable {
    public static void main(String[] args) {
        StatistiqueManager statistiqueManager = new StatistiqueManager();
        Loggeable.initLogger(Level.FINE);
        for(int i=0; i<1000 ;++i){
            LOGGER.config("Game "+i);
            Game game = new Game(statistiqueManager,false);
            game.start();
        }
    }
}
