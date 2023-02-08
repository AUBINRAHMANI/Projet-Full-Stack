package fr.cotedazur.univ.polytech.startingpoint;

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

    public static void main(String[] args) {
        StatistiqueManager statistiqueManager = new StatistiqueManager();
        Loggeable.initLogger(Level.FINEST);

        List<BotProfil> players = new ArrayList<>();
        players.add(new BotProfil(new BotMbappe(), "bot 1"));
        players.add(new BotProfil(new BotSprint(), "bot 2"));

        statistiqueManager.initBotsStatistiquesProfiles(players);

        Game game = new Game(statistiqueManager, players, false);
        game.start();

    }

}
