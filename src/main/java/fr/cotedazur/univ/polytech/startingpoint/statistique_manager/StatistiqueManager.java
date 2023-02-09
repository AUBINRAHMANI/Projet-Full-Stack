package fr.cotedazur.univ.polytech.startingpoint.statistique_manager;

import fr.cotedazur.univ.polytech.startingpoint.action.Action;
import fr.cotedazur.univ.polytech.startingpoint.bot.BotProfil;
import fr.cotedazur.univ.polytech.startingpoint.bot.Playable;
import fr.cotedazur.univ.polytech.startingpoint.logger.Loggeable;

import java.util.ArrayList;
import java.util.List;

public class StatistiqueManager implements Loggeable {

    private final List<BotStatistiqueProfil> botStatistiqueProfils;
    private int NombreMatchNul = 0;
    Action actions;


    public StatistiqueManager() {
        this.botStatistiqueProfils = new ArrayList<>();
        this.NombreMatchNul = 0;
        actions = null;
    }

    public void addMatchNul() {
        this.NombreMatchNul += 1;
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

    public void addNumberOfGame(){
        for(BotStatistiqueProfil botStatistiqueProfil : botStatistiqueProfils){
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

    public void setActions(Action action){
        this.actions = action;
    }

    public Action getActions(){
        return this.actions;
    }

    public void addActionSpecific(Playable bot){
        for(BotStatistiqueProfil botStatistiqueProfil : botStatistiqueProfils){
            if (botStatistiqueProfil.getBot()==bot){
                switch(this.actions.toType()) {
                    case MOVE_GARDENER :
                        botStatistiqueProfil.addDealMoveGardener();
                        break;
                    case MOVE_PANDA:
                        botStatistiqueProfil.addDealMovePanda();
                        break;

                    case PICK_OBJECTIVE:
                        botStatistiqueProfil.addDealPickObjective();
                        break;

                    case PUT_IRRIGATION:
                        botStatistiqueProfil.addDealPutIrrigation();
                        break;

                    case PUT_PLOT:
                        botStatistiqueProfil.addDealPutPlot();
                        break;

                    case RAIN:
                        botStatistiqueProfil.addDealRain();
                        break;

                    case THUNDER :
                        botStatistiqueProfil.addDealThunder();
                        break;
                }
            }
        }

    }
    @Override
    public String toString() {
        return "StatistiqueManager{" +
                "botStatistiqueProfils=" + botStatistiqueProfils + "\n"
                + ", NombreMatchNul=" + NombreMatchNul +
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
}
