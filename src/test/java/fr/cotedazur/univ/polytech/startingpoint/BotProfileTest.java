package fr.cotedazur.univ.polytech.startingpoint;

import fr.cotedazur.univ.polytech.startingpoint.bot.BotMbappe;
import fr.cotedazur.univ.polytech.startingpoint.bot.BotProfile;
import fr.cotedazur.univ.polytech.startingpoint.bot.Playable;
import fr.cotedazur.univ.polytech.startingpoint.objective.Objective;
import fr.cotedazur.univ.polytech.startingpoint.objective.ObjectivePlots;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BotProfileTest {

    @Test
    void getBot_() {
        BotMbappe bot = new BotMbappe(null, null);
        BotProfile botProfil = new BotProfile(bot, "");
        assertEquals(bot, botProfil.getBot());
    }

    @Test
    void getObjectives_() {
        BotMbappe bot = new BotMbappe(null, null);
        Objective objective = new ObjectivePlots(1, null);
        BotProfile botProfil = new BotProfile(bot, "");
        botProfil.addObjective(objective);
        assertEquals(objective, botProfil.getObjectives().get(0));
    }

    @Test
    void setObjectiveCompleted() {
        BotMbappe bot = new BotMbappe(null, null);
        Objective objective = new ObjectivePlots(1, null);
        BotProfile botProfil = new BotProfile(bot, "");
        botProfil.addObjective(objective);
        botProfil.setObjectiveCompleted(objective);
        assertEquals(1, botProfil.getNbCompletedObjective());
        assertEquals(1, botProfil.getPoints());
    }

    @Test
    void getNbCompletedObjective_() {
        BotMbappe bot = new BotMbappe(null, null);
        Objective objective = new ObjectivePlots(1, null);
        BotProfile botProfil = new BotProfile(bot, "");
        botProfil.addObjective(objective);
        botProfil.setObjectiveCompleted(objective);
        assertEquals(1, botProfil.getNbCompletedObjective());
    }

    @Test
    void addBamboo() {
        BotProfile botProfil = new BotProfile(new BotMbappe(null, null), "");
        Bamboo bamboo = new Bamboo(PlotType.GREEN);
        botProfil.addBamboo(bamboo);
        assertEquals(bamboo, botProfil.getBamboos().get(0));
    }

    @Test
    void resetPoints(){
        BotProfile botProfil = new BotProfile(null, "");
        botProfil.setObjectiveCompleted(new ObjectivePlots(3, new Pattern()));
        assertEquals(3, botProfil.getPoints());
        assertEquals(1, botProfil.getNbCompletedObjective());

        botProfil.resetPoints();

        assertEquals(0, botProfil.getPoints());
        assertEquals(0, botProfil.getNbCompletedObjective());
    }
}