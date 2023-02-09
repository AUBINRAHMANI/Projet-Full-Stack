package fr.cotedazur.univ.polytech.startingpoint.statistique_manager;

import fr.cotedazur.univ.polytech.startingpoint.action.Action;
import fr.cotedazur.univ.polytech.startingpoint.bot.BotProfil;
import fr.cotedazur.univ.polytech.startingpoint.bot.Playable;
import fr.cotedazur.univ.polytech.startingpoint.logger.Loggeable;

import java.util.ArrayList;
import java.util.List;

public class StatistiqueManager implements Loggeable {

    private final List<BotStatistiqueProfil> botStatistiqueProfils;

    Action actions;

    private int nombreMatchNul = 0;


    public StatistiqueManager() {
        this.botStatistiqueProfils = new ArrayList<>();
        actions = null;
        this.nombreMatchNul = 0;
    }

    public void addMatchNul() {
        ++this.nombreMatchNul;
    }

    public int getMatchNul() {
        return this.nombreMatchNul;
    }

    public void initBotsStatistiquesProfiles(List<BotProfil> botProfils) {
        for (BotProfil botProfil : botProfils) {
            botStatistiqueProfils.add(new BotStatistiqueProfil(botProfil.getBot(), botProfil.getBotName()));
        }
    }

    public void addWinner(Playable bot) {
        for (BotStatistiqueProfil botStatistiqueProfil : botStatistiqueProfils) {
            if (botStatistiqueProfil.getBot() == bot) {
                botStatistiqueProfil.addVictory();
            }
        }
    }

    public void addTours(){
        for(BotStatistiqueProfil botStatistiqueProfil : botStatistiqueProfils){
            botStatistiqueProfil.addnumberOfRounds();
        }

    }

    public void addLoser(Playable bot) {
        for (BotStatistiqueProfil botStatistiqueProfil : botStatistiqueProfils)
            if (botStatistiqueProfil.getBot() == bot) {
                botStatistiqueProfil.addDefeat();
            }

    }

    public void addNumberOfGame() {
        for (BotStatistiqueProfil botStatistiqueProfil : botStatistiqueProfils) {
            botStatistiqueProfil.addNumberOfGames();
        }
    }

    public void addCoups(Playable bot){
        for(BotStatistiqueProfil botStatistiqueProfil : botStatistiqueProfils){
            if(botStatistiqueProfil.getBot()==bot){
                botStatistiqueProfil.addNumberDealPerGames();
            }
        }
    }



    public List<BotStatistiqueProfil> getBotStatistiqueProfils() {
        return this.botStatistiqueProfils;
    }

    @Override
    public String toString() {
        return "StatistiqueManager{" +
                "botStatistiqueProfils=" + botStatistiqueProfils + "\n"
                + ", NombreMatchNul=" + nombreMatchNul +
                '}';
    }

    public void incrementGardenerAction(Playable bot) {
        for(BotStatistiqueProfil botStatistiqueProfil : botStatistiqueProfils){
            if(botStatistiqueProfil.getBot()==bot){
                botStatistiqueProfil.addDealMoveGardener();
            }
        }
    }

    public void incrementPandaAction(Playable bot){
        for(BotStatistiqueProfil botStatistiqueProfil : botStatistiqueProfils){
            if(botStatistiqueProfil.getBot()==bot){
                botStatistiqueProfil.addDealMovePanda();
            }
        }
    }
    public void incrementObjectiveAction(Playable bot){
        for(BotStatistiqueProfil botStatistiqueProfil : botStatistiqueProfils){
            if(botStatistiqueProfil.getBot()==bot){
                botStatistiqueProfil.addDealPickObjective();
            }
        }
    }

    public void incrementIrrigationAction(Playable bot){
        for(BotStatistiqueProfil botStatistiqueProfil : botStatistiqueProfils){
            if(botStatistiqueProfil.getBot()==bot){
                botStatistiqueProfil.addDealPutIrrigation();
            }
        }
    }

    public void incrementPlotAction(Playable bot){
        for(BotStatistiqueProfil botStatistiqueProfil : botStatistiqueProfils){
            if(botStatistiqueProfil.getBot()==bot){
                botStatistiqueProfil.addDealPutPlot();
            }
        }
    }

    public void incrementRainAction(Playable bot){
        for(BotStatistiqueProfil botStatistiqueProfil : botStatistiqueProfils){
            if(botStatistiqueProfil.getBot()==bot){
                botStatistiqueProfil.addDealRain();
            }
        }
    }
    public void incrementThunderAction(Playable bot){
        for(BotStatistiqueProfil botStatistiqueProfil : botStatistiqueProfils){
            if(botStatistiqueProfil.getBot()==bot){
                botStatistiqueProfil.addDealThunder();
            }
        }
    }

    public void incrementNumberObjectiveGardener(Playable bot){
        for(BotStatistiqueProfil botStatistiqueProfil : botStatistiqueProfils){
            if(botStatistiqueProfil.getBot()==bot){
                botStatistiqueProfil.addObjectiveGardener();
            }
        }
    }

    public void incrementNumberObjectivePanda(Playable bot){
        for(BotStatistiqueProfil botStatistiqueProfil : botStatistiqueProfils){
            if(botStatistiqueProfil.getBot()==bot){
                botStatistiqueProfil.addObjectivePanda();
            }
        }
    }

    public void incrementNumberObjectivePlots(Playable bot){
        for(BotStatistiqueProfil botStatistiqueProfil : botStatistiqueProfils){
            if(botStatistiqueProfil.getBot() == bot){
                botStatistiqueProfil.addObjectivePlots();
            }
        }
    }

    public void resetPointsObjective() {
        for (BotStatistiqueProfil botStatistiqueProfil : botStatistiqueProfils) {
            botStatistiqueProfil.resetNumberPointsObjective();
        }
    }

    public void incrementNumberPointsObjectiveGardener(Playable bot, int points){
        for(BotStatistiqueProfil botStatistiqueProfil : botStatistiqueProfils){
            if(botStatistiqueProfil.getBot()==bot){
                botStatistiqueProfil.addNumberPointsObjectiveGardener(points);
            }
        }
    }

    public void incrementNumberPointsObjectivePanda(Playable bot, int points){
        for(BotStatistiqueProfil botStatistiqueProfil : botStatistiqueProfils){
            if(botStatistiqueProfil.getBot()==bot){
                botStatistiqueProfil.addNumberPointsObjectivePanda(points);
            }
        }
    }

    public void incrementNumberPointsObjectivePlot(Playable bot,int points){
        for(BotStatistiqueProfil botStatistiqueProfil : botStatistiqueProfils){
            if(botStatistiqueProfil.getBot()==bot){
                botStatistiqueProfil.addNumberPointsObjectivePlot(points);
            }
        }
    }
}
