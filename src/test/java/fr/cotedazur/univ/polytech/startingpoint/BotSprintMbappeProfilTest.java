package fr.cotedazur.univ.polytech.startingpoint;

import fr.cotedazur.univ.polytech.startingpoint.bot.BotProfil;
import fr.cotedazur.univ.polytech.startingpoint.bot.BotMbappe;
import fr.cotedazur.univ.polytech.startingpoint.objective.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BotSprintMbappeProfilTest {

    @Test
    void getBot_() {
        BotMbappe bot = new BotMbappe(null, null);
        BotProfil botProfil = new BotProfil(bot, "");
        assertEquals(bot, botProfil.getBot());
    }

    @Test
    void getObjectives_() {
        BotMbappe bot = new BotMbappe(null, null);
        Objective objective = new ObjectivePlots( 1, (Pattern) null);
        BotProfil botProfil = new BotProfil(bot, "");
        botProfil.addObjective(objective);
        assertEquals(objective, botProfil.getObjectives().get(0));
    }

    @Test
    void setObjectiveCompleted() {
        BotMbappe bot = new BotMbappe(null, null);
        Objective objective = new ObjectivePlots( 1, (Pattern) null);
        BotProfil botProfil = new BotProfil(bot, "");
        botProfil.addObjective(objective);
        botProfil.setObjectiveCompleted(objective);
        assertEquals(1, botProfil.getNbCompletedObjective());
        assertEquals(1, botProfil.getPoints());
    }

    @Test
    void getNbCompletedObjective_(){
        BotMbappe bot = new BotMbappe(null, null);
        Objective objective = new ObjectivePlots( 1, (Pattern) null);
        BotProfil botProfil = new BotProfil(bot, "");
        botProfil.addObjective(objective);
        botProfil.setObjectiveCompleted(objective);
        assertEquals(1, botProfil.getNbCompletedObjective());
    }
    @Test
    void addBanbou(){
        BotProfil botProfil = new BotProfil(new BotMbappe(null, null), "");
        Bambou bambou = new Bambou(PlotType.GREEN);
        botProfil.addBanbou(bambou);
        assertEquals(bambou , botProfil.getBambous().get(0));
    }
}