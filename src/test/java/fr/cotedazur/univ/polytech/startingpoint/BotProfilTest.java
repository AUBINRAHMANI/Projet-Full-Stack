package fr.cotedazur.univ.polytech.startingpoint;

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
    void getPoints_() {
        Bot bot = new Bot();
        BotProfil botProfil = new BotProfil(bot);
        botProfil.addPoint();
        assertEquals(1, botProfil.getPoints_());
    }

    @Test
    void getObjectives_() {
        Bot bot = new Bot();
        Objective objective = new Objective( 1, ObjectiveType.PLOT);
        BotProfil botProfil = new BotProfil(bot);
        botProfil.addObjective(objective);
        assertEquals(objective, botProfil.getObjectives_().get(0));
    }

    @Test
    void setObjectiveCompleted() {
        Bot bot = new Bot();
        Objective objective = new Objective( 1, ObjectiveType.PLOT);
        BotProfil botProfil = new BotProfil(bot);
        botProfil.addObjective(objective);
        botProfil.setObjectiveCompleted(0);
        assertEquals(0, botProfil.getObjectives_().size());
        assertEquals(1, botProfil.getNbCompletedObjective_());
    }

    @Test
    void getNbCompletedObjective_(){
        Bot bot = new Bot();
        Objective objective = new Objective( 1, ObjectiveType.PLOT);
        BotProfil botProfil = new BotProfil(bot);
        botProfil.addObjective(objective);
        botProfil.setObjectiveCompleted(0);
        assertEquals(1, botProfil.getNbCompletedObjective_());
    }
}