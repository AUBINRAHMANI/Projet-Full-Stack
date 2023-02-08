package fr.cotedazur.univ.polytech.startingpoint;

import fr.cotedazur.univ.polytech.startingpoint.StatistiqueManager.StatistiqueManager;
import fr.cotedazur.univ.polytech.startingpoint.bot.BotMbappe;
import fr.cotedazur.univ.polytech.startingpoint.bot.BotProfil;
import fr.cotedazur.univ.polytech.startingpoint.bot.BotSprint;
import fr.cotedazur.univ.polytech.startingpoint.game.Game;
import fr.cotedazur.univ.polytech.startingpoint.logger.Loggeable;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

public class ThousandGameLoop implements Loggeable {
    public static void main(String[] args) {

        StatistiqueManager statistiqueManager = new StatistiqueManager();
        CSVManager csvManager = new CSVManager();
        Loggeable.initLogger(Level.CONFIG);

        List<BotProfil> players = new ArrayList<>();
        players.add(new BotProfil( new  BotMbappe(), "bot 1"));
        players.add(new BotProfil( new  BotSprint(), "bot 2"));

        statistiqueManager.initBotsStatistiquesProfiles(players);

        for(int i=0; i<1000 ;++i){
            LOGGER.config("Game "+i);
            Game game = new Game(statistiqueManager, players , false);
            game.start();
        }
        Path path = Paths.get(".", "stats", "statistique.csv");
        csvManager.exportData(statistiqueManager.getBotStatistiqueProfils(), statistiqueManager.getMatchNul(), path.toString());
        LOGGER.config(statistiqueManager.toString());
    }
}
