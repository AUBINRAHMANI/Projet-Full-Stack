package fr.cotedazur.univ.polytech.startingpoint;

import fr.cotedazur.univ.polytech.startingpoint.StatistiqueManager.StatistiqueManager;
import fr.cotedazur.univ.polytech.startingpoint.bot.BotMbappe;
import fr.cotedazur.univ.polytech.startingpoint.bot.BotProfil;
import fr.cotedazur.univ.polytech.startingpoint.bot.BotSprint;
import fr.cotedazur.univ.polytech.startingpoint.bot.Playable;
import fr.cotedazur.univ.polytech.startingpoint.game.Game;
import fr.cotedazur.univ.polytech.startingpoint.logger.Loggeable;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

public class ThousandGameLoop implements Loggeable {
    public static void main(String[] args) {
        StatistiqueManager statistiqueManager = new StatistiqueManager();
        Loggeable.initLogger(Level.FINE);

        List<BotProfil> players = new ArrayList<>();
        BotProfil bob1 = new BotProfil(new BotSprint(),"bot 1");
        BotProfil bob2 = new BotProfil(new BotMbappe(), "bot2");
        players.add(bob1);
        players.add(bob2);


        statistiqueManager.initBotsStatistiquesProfiles(players);

        for(int i=0; i<5 ;++i){

            LOGGER.config("Game "+i);
            Game game = new Game(statistiqueManager, players , false);
            game.start();
            bob1.resetPoints();
            bob2.resetPoints();
            for(BotProfil botProfil : players){
                botProfil.resetPoints();
            }
        }
        LOGGER.config(statistiqueManager.toString());
    }
}
