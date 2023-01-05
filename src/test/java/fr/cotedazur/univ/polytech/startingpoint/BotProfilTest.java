package fr.cotedazur.univ.polytech.startingpoint;

import fr.cotedazur.univ.polytech.startingpoint.objective.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BotProfilTest {

    @Test
    void getBot_() {
        Bot bot = new Bot();
        BotProfil botProfil = new BotProfil(bot);
        assertEquals(bot, botProfil.getBot_());
    }

    @Test
    void getObjectives_() {
        Bot bot = new Bot();
        Objective objective = new ObjectivePlots( 1, (Pattern) null);
        BotProfil botProfil = new BotProfil(bot);
        botProfil.addObjective(objective);
        assertEquals(objective, botProfil.getObjectives_().get(0));
    }

    @Test
    void setObjectiveCompleted() {
        Bot bot = new Bot();
        Objective objective = new ObjectivePlots( 1, (Pattern) null);
        BotProfil botProfil = new BotProfil(bot);
        botProfil.addObjective(objective);
        botProfil.setObjectiveCompleted(objective);
        assertEquals(0, botProfil.getObjectives_().size());
        assertEquals(1, botProfil.getNbCompletedObjective_());
        assertEquals(1, botProfil.getPoints_());
    }

    @Test
    void getNbCompletedObjective_(){
        Bot bot = new Bot();
        Objective objective = new ObjectivePlots( 1, (Pattern) null);
        BotProfil botProfil = new BotProfil(bot);
        botProfil.addObjective(objective);
        botProfil.setObjectiveCompleted(objective);
        assertEquals(1, botProfil.getNbCompletedObjective_());
    }
}