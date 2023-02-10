package fr.cotedazur.univ.polytech.startingpoint.statistique_manager;

import fr.cotedazur.univ.polytech.startingpoint.action.Action;
import fr.cotedazur.univ.polytech.startingpoint.bot.BotProfile;
import fr.cotedazur.univ.polytech.startingpoint.bot.Playable;
import fr.cotedazur.univ.polytech.startingpoint.logger.Loggeable;

import java.util.ArrayList;
import java.util.List;

public class StatisticManager implements Loggeable {

    private final List<BotStatisticProfile> botsStatisticsProfiles;

    Action actions;

    private int nbOfDrawGames;


    public StatisticManager() {
        this.botsStatisticsProfiles = new ArrayList<>();
        actions = null;
        this.nbOfDrawGames = 0;
    }

    public void addDrawGame() {
        ++this.nbOfDrawGames;
    }

    public int getNbOfDrawGames() {
        return this.nbOfDrawGames;
    }

    public float percentageOfDrawGames() {
        BotStatisticProfile bot;
        float nbOfGames = 0;
        float percentage;
        for (BotStatisticProfile botStatisticProfile : botsStatisticsProfiles) {
            bot = botStatisticProfile;
            nbOfGames = bot.getNbOfGames();
        }
        if(nbOfGames == 0)
            return 0;
        percentage = ((this.getNbOfDrawGames() * 100) / nbOfGames);
        return percentage;
    }

    public void initBotsStatisticsProfiles(List<BotProfile> botProfiles) {
        for (BotProfile botProfile : botProfiles) {
            botsStatisticsProfiles.add(new BotStatisticProfile(botProfile.getBot(), botProfile.getBotName()));
        }
    }

    public void addWinner(Playable bot) {
        for (BotStatisticProfile botStatisticProfile : botsStatisticsProfiles) {
            if (botStatisticProfile.getBot() == bot) {
                botStatisticProfile.addVictory();
            }
        }
    }

    public void addRound() {
        for (BotStatisticProfile botStatisticProfile : botsStatisticsProfiles) {
            botStatisticProfile.addNbOfRounds();
        }

    }

    public void addLoser(Playable bot) {
        for (BotStatisticProfile botStatisticProfile : botsStatisticsProfiles)
            if (botStatisticProfile.getBot() == bot) {
                botStatisticProfile.addDefeat();
            }

    }

    public void addGame() {
        for (BotStatisticProfile botStatisticProfile : botsStatisticsProfiles) {
            botStatisticProfile.addNumberOfGames();
        }
    }

    public void addCoups(Playable bot) {
        for (BotStatisticProfile botStatisticProfile : botsStatisticsProfiles) {
            if (botStatisticProfile.getBot() == bot) {
                botStatisticProfile.addNumberDealPerGames();
            }
        }
    }


    public List<BotStatisticProfile> getBotsStatisticsProfiles() {
        return this.botsStatisticsProfiles;
    }


    public void incrementGardenerAction(Playable bot) {
        for (BotStatisticProfile botStatisticProfile : botsStatisticsProfiles) {
            if (botStatisticProfile.getBot() == bot) {
                botStatisticProfile.addDealMoveGardener();
            }
        }
    }

    public void incrementPandaAction(Playable bot) {
        for (BotStatisticProfile botStatisticProfile : botsStatisticsProfiles) {
            if (botStatisticProfile.getBot() == bot) {
                botStatisticProfile.addDealMovePanda();
            }
        }
    }

    public void incrementObjectiveAction(Playable bot) {
        for (BotStatisticProfile botStatisticProfile : botsStatisticsProfiles) {
            if (botStatisticProfile.getBot() == bot) {
                botStatisticProfile.addDealPickObjective();
            }
        }
    }

    public void incrementIrrigationAction(Playable bot) {
        for (BotStatisticProfile botStatisticProfile : botsStatisticsProfiles) {
            if (botStatisticProfile.getBot() == bot) {
                botStatisticProfile.addDealPutIrrigation();
            }
        }
    }

    public void incrementPlotAction(Playable bot) {
        for (BotStatisticProfile botStatisticProfile : botsStatisticsProfiles) {
            if (botStatisticProfile.getBot() == bot) {
                botStatisticProfile.addDealPutPlot();
            }
        }
    }

    public void incrementRainAction(Playable bot) {
        for (BotStatisticProfile botStatisticProfile : botsStatisticsProfiles) {
            if (botStatisticProfile.getBot() == bot) {
                botStatisticProfile.addDealRain();
            }
        }
    }

    public void incrementThunderAction(Playable bot) {
        for (BotStatisticProfile botStatisticProfile : botsStatisticsProfiles) {
            if (botStatisticProfile.getBot() == bot) {
                botStatisticProfile.addDealThunder();
            }
        }
    }

    public void incrementNumberObjectiveGardener(Playable bot) {
        for (BotStatisticProfile botStatisticProfile : botsStatisticsProfiles) {
            if (botStatisticProfile.getBot() == bot) {
                botStatisticProfile.addObjectiveGardener();
            }
        }
    }

    public void incrementNumberObjectivePanda(Playable bot) {
        for (BotStatisticProfile botStatisticProfile : botsStatisticsProfiles) {
            if (botStatisticProfile.getBot() == bot) {
                botStatisticProfile.addObjectivePanda();
            }
        }
    }

    public void incrementNumberObjectivePlots(Playable bot) {
        for (BotStatisticProfile botStatisticProfile : botsStatisticsProfiles) {
            if (botStatisticProfile.getBot() == bot) {
                botStatisticProfile.addObjectivePlots();
            }
        }
    }

    public void incrementNumberPointsObjectiveGardener(Playable bot, int points) {
        for (BotStatisticProfile botStatisticProfile : botsStatisticsProfiles) {
            if (botStatisticProfile.getBot() == bot) {
                botStatisticProfile.addNumberPointsObjectiveGardener(points);
            }
        }
    }

    public void incrementNumberPointsObjectivePanda(Playable bot, int points) {
        for (BotStatisticProfile botStatisticProfile : botsStatisticsProfiles) {
            if (botStatisticProfile.getBot() == bot) {
                botStatisticProfile.addNumberPointsObjectivePanda(points);
            }
        }
    }

    public void incrementNumberPointsObjectivePlot(Playable bot, int points) {
        for (BotStatisticProfile botStatisticProfile : botsStatisticsProfiles) {
            if (botStatisticProfile.getBot() == bot) {
                botStatisticProfile.addNumberPointsObjectivePlot(points);
            }
        }
    }

    @Override
    public String toString() {
        return "StatisticManager{" +
                "botStatisticsProfiles=" + botsStatisticsProfiles + "\n"
                + ", NumberOfDrawGames=" + nbOfDrawGames + " \n "
                + "PercentageOfDrawGames" + this.percentageOfDrawGames() + "% of draw games" +
                '}';
    }
}
