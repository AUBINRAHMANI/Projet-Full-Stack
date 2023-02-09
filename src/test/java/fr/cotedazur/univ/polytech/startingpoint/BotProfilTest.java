package fr.cotedazur.univ.polytech.startingpoint;

import fr.cotedazur.univ.polytech.startingpoint.bot.BotMbappe;
import fr.cotedazur.univ.polytech.startingpoint.bot.BotProfil;
import fr.cotedazur.univ.polytech.startingpoint.objective.Objective;
import fr.cotedazur.univ.polytech.startingpoint.objective.ObjectivePanda;
import fr.cotedazur.univ.polytech.startingpoint.objective.ObjectivePlots;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BotProfilTest {

    @Test
    void getBot_() {
        BotMbappe bot = new BotMbappe(null, null);
        BotProfil botProfil = new BotProfil(bot, "");
        assertEquals(bot, botProfil.getBot());
    }

    @Test
    void getObjectives_() {
        BotMbappe bot = new BotMbappe(null, null);
        Objective objective = new ObjectivePlots(1, (Pattern) null);
        BotProfil botProfil = new BotProfil(bot, "");
        botProfil.addObjective(objective);
        assertEquals(objective, botProfil.getObjectives().get(0));
    }

    @Test
    void setObjectiveCompleted() {
        BotMbappe bot = new BotMbappe(null, null);
        Objective objective = new ObjectivePlots(1, (Pattern) null);
        BotProfil botProfil = new BotProfil(bot, "");
        botProfil.addObjective(objective);
        botProfil.setObjectiveCompleted(objective);
        assertEquals(1, botProfil.getNbCompletedObjective());
        assertEquals(1, botProfil.getPoints());
    }

    @Test
    void getNbCompletedObjective_() {
        BotMbappe bot = new BotMbappe(null, null);
        Objective objective = new ObjectivePlots(1, (Pattern) null);
        BotProfil botProfil = new BotProfil(bot, "");
        botProfil.addObjective(objective);
        botProfil.setObjectiveCompleted(objective);
        assertEquals(1, botProfil.getNbCompletedObjective());
    }

    @Test
    void addBanbou() {
        BotProfil botProfil = new BotProfil(new BotMbappe(null, null), "");
        Bambou bambou = new Bambou(PlotType.GREEN);
        botProfil.addBanbou(bambou);
        assertEquals(bambou, botProfil.getBambous().get(0));
    }

    @Test
    void resetPoints(){
        BotProfil botProfil = new BotProfil(null, "");
        botProfil.setObjectiveCompleted(new ObjectivePlots(3, new Pattern()));
        assertEquals(3, botProfil.getPoints());
        assertEquals(1, botProfil.getNbCompletedObjective());

        botProfil.resetPoints();

        assertEquals(0, botProfil.getPoints());
        assertEquals(0, botProfil.getNbCompletedObjective());
    }
}