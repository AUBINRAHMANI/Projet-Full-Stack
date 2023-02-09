package fr.cotedazur.univ.polytech.startingpoint;

import fr.cotedazur.univ.polytech.startingpoint.bot.BotMbappe;
import fr.cotedazur.univ.polytech.startingpoint.bot.BotProfil;
import fr.cotedazur.univ.polytech.startingpoint.objective.Objective;
import fr.cotedazur.univ.polytech.startingpoint.objective.ObjectivePlots;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BotProfileTest {

    @Test
    void getBot_() {
        BotMbappe bot = new BotMbappe(null, null);
        BotProfil botProfil = new BotProfil(bot, "");
        assertEquals(bot, botProfil.getBot());
    }

    @Test
    void getObjectives_() {
        BotMbappe bot = new BotMbappe(null, null);
        Objective objective = new ObjectivePlots(1, null);
        BotProfil botProfil = new BotProfil(bot, "");
        botProfil.addObjective(objective);
        assertEquals(objective, botProfil.getObjectives().get(0));
    }

    @Test
    void setObjectiveCompleted() {
        BotMbappe bot = new BotMbappe(null, null);
        Objective objective = new ObjectivePlots(1, null);
        BotProfil botProfil = new BotProfil(bot, "");
        botProfil.addObjective(objective);
        botProfil.setObjectiveCompleted(objective);
        assertEquals(1, botProfil.getNbCompletedObjective());
        assertEquals(1, botProfil.getPoints());
    }

    @Test
    void getNbCompletedObjective_() {
        BotMbappe bot = new BotMbappe(null, null);
        Objective objective = new ObjectivePlots(1, null);
        BotProfil botProfil = new BotProfil(bot, "");
        botProfil.addObjective(objective);
        botProfil.setObjectiveCompleted(objective);
        assertEquals(1, botProfil.getNbCompletedObjective());
    }

    @Test
    void addBamboo() {
        BotProfil botProfil = new BotProfil(new BotMbappe(null, null), "");
        Bamboo bamboo = new Bamboo(PlotType.GREEN);
        botProfil.addBanbou(bamboo);
        assertEquals(bamboo, botProfil.getBambous().get(0));
    }
}