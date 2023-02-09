package fr.cotedazur.univ.polytech.startingpoint.game;

import fr.cotedazur.univ.polytech.startingpoint.Map;
import fr.cotedazur.univ.polytech.startingpoint.*;
import fr.cotedazur.univ.polytech.startingpoint.action.Action;
import fr.cotedazur.univ.polytech.startingpoint.action.ActionType;
import fr.cotedazur.univ.polytech.startingpoint.bot.BotProfile;
import fr.cotedazur.univ.polytech.startingpoint.bot.Playable;
import fr.cotedazur.univ.polytech.startingpoint.logger.Loggeable;
import fr.cotedazur.univ.polytech.startingpoint.objective.Objective;
import fr.cotedazur.univ.polytech.startingpoint.objective.ObjectiveGardener;
import fr.cotedazur.univ.polytech.startingpoint.objective.ObjectivePanda;
import fr.cotedazur.univ.polytech.startingpoint.objective.ObjectivePlots;
import fr.cotedazur.univ.polytech.startingpoint.statistique_manager.StatisticManager;

import java.security.SecureRandom;
import java.util.*;


public class Game implements DeckSignal, Referee, Loggeable {

    static final int MAX_NB_ROUND = 50;
    static final int NB_OBJECTIVE_TO_FINISH = 9;
    static final int NB_ACTIONS_PER_ROUND = 2;
    private final StatisticManager statisticManager;
    Random random;
    GameEngine gameEngine;
    List<BotProfile> botProfiles;
    int nbActions;
    List<Action> previousActions;
    private int timeOutCounter;

    public Game(StatisticManager statisticManager, List<BotProfile> botProfiles) {
        this.random = new SecureRandom();
        previousActions = new ArrayList<>();
        Deck<Objective> objectiveDeck = generateObjectiveDrawPile();
        Deck<Plot> plotDeck = generatePlotDrawPile();
        gameEngine = new GameEngine(objectiveDeck, plotDeck, new Map());
        this.botProfiles = botProfiles;
        this.statisticManager = statisticManager;
        this.timeOutCounter = 0;

        for (BotProfile botProfile : this.botProfiles) {
            botProfile.getBot().setEnvironment(this, gameEngine.getMap());
        }
    }

    public void start() {
        do {
            Long startTime = System.currentTimeMillis();
            ++timeOutCounter;
            statisticManager.addRound();
            for (BotProfile botProfile : botProfiles) {
                nbActions = NB_ACTIONS_PER_ROUND;
                WeatherType weather = gameEngine.drawWeather();
                this.applyChangesDueToWeather(weather);
                doActions(botProfile, nbActions, weather);
            }
        } while (!checkFinishingCondition());
        BotProfile winner = checkWinner();
        statisticManager.addGame();
        printWinner(winner);
    }

    private void saveAction(Action action) {
        previousActions.add(action);
        Collections.rotate(previousActions, 1);
        if (previousActions.size() > 8) {
            previousActions.remove(previousActions.size() - 1);
        }
    }

    public List<Action> getPreviousActions() {
        return previousActions;
    }


    private void doActions(BotProfile botProfile, int nbActions, WeatherType weather) {
        List<ActionType> banActionTypes = new ArrayList<>();
        for (int i = 0; i < nbActions; i++) {
            Action action = botProfile.getBot().play(banActionTypes, weather);
            LOGGER.finer(() -> "Action : " + action);
            if (action != null && !(banActionTypes.contains(action.toType()))) {
                action.play(this, gameEngine);
                banActionTypes.add(action.toType());
                action.verifyObjectiveAfterAction(this, gameEngine.getMap());
                saveAction(action);
                action.incrementAction(statisticManager, botProfile.getBot());
                statisticManager.addCoups(botProfile.getBot());
            }
        }
    }

    private void applyChangesDueToWeather(WeatherType weather) {
        if (weather == WeatherType.SUN) {
            ++this.nbActions;
        }
    }

    private boolean checkFinishingCondition() {
        if (this.timeOutCounter >= MAX_NB_ROUND) return true;
        for (BotProfile botProfile : botProfiles) {
            if (botProfile.getNbCompletedObjective() >= NB_OBJECTIVE_TO_FINISH) return true;
        }
        return false;
    }
    public void emptyDeck() {
        gameEngine.regenerateDecks(generateObjectiveDrawPile(), generatePlotDrawPile());
    }

    private Deck<Objective> generateObjectiveDrawPile() {
        Deck<Objective> objectiveDeck = new Deck<>(this);
        int upperRandForPlotType = 3;

        for (int i = 0; i < 20; ++i) {
            objectiveDeck.addCard(new ObjectivePlots(random.nextInt(4) + 1, new Pattern()));
        }
        for (int i = 0; i < 20; ++i) {
            int nbBamboos = 4;
            objectiveDeck.addCard(new ObjectiveGardener(random.nextInt(4) + 1, nbBamboos, PlotType.values()[random.nextInt(upperRandForPlotType) + 1], false, 1));

        }

        for (int i = 0; i < 20; ++i) {
            ArrayList<Bamboo> bamboos = new ArrayList<>();
            for (int j = 0; j < (random.nextInt(2) + 2); ++j) {
                bamboos.add(new Bamboo(PlotType.values()[random.nextInt(upperRandForPlotType) + 1]));
            }
            objectiveDeck.addCard(new ObjectivePanda(random.nextInt(4) + 1, bamboos));
        }
        objectiveDeck.shuffle();
        return objectiveDeck;
    }

    private Deck<Plot> generatePlotDrawPile() {
        Deck<Plot> plotDeck = new Deck<>(this);
        int upperRandForPlotType = 3;

        for (int i = 0; i < 60; ++i) {
            plotDeck.addCard(new Plot(PlotType.values()[random.nextInt(upperRandForPlotType) + 1]));
        }
        plotDeck.shuffle();
        return plotDeck;
    }

    public boolean pickObjective(fr.cotedazur.univ.polytech.startingpoint.bot.Playable bot) {
        Objective objective = gameEngine.pickObjective();
        for (BotProfile botProfile : botProfiles) {
            if (bot == botProfile.getBot()) {
                botProfile.addObjective(objective);
                LOGGER.finer(() -> botProfile.getBotName() + " take an objective : " + objective);
                return true;
            }
        }
        return false;
    }

    public List<Plot> pickPlot() {
        return gameEngine.pickPlot();
    }

    public boolean computeObjectivesPlot(Plot lastPlacedPlot) {
        boolean result = false;
        ArrayList<Objective> validatedObjective = new ArrayList<>();
        for (BotProfile botProfile : botProfiles) {
            for (Objective objective : botProfile.getObjectives()) {
                if (objective.verifyPlotObj(gameEngine, lastPlacedPlot)) {
                    String botName = botProfile.getBotName();
                    validatedObjective.add(objective);
                    botProfile.setObjectiveCompleted(objective);
                    timeOutCounter = 0;
                    LOGGER.finer(()-> "L'objectif suivant a été validé : " + objective);
                    LOGGER.finer(()-> botName + " gagne " + objective.getPoint() + " points");
                    LOGGER.finer(()-> "Le score de " + botName + " = " + botProfile.getPoints() + " points");
                    logValidatedObjective(objective, botName, botProfile);
                    objective.incrementationObjective(statisticManager, botProfile.getBot());
                    objective.incrementationPointsObjective(statisticManager, botProfile.getBot());
                }
            }
            botProfile.getObjectives().removeAll(validatedObjective);
            if (!validatedObjective.isEmpty()) result = true;
        }
        return result;
    }

    public boolean computeObjectivesGardener() {
        boolean result = false;
        ArrayList<Objective> validatedObjective = new ArrayList<>();
        for (BotProfile botProfile : botProfiles) {
            for (Objective objective : botProfile.getObjectives()) {
                if (objective.verifyGardenerObj(gameEngine)) {
                    String botName = botProfile.getBotName();
                    validatedObjective.add(objective);
                    botProfile.setObjectiveCompleted(objective);
                    logValidatedObjective(objective, botName, botProfile);
                    objective.incrementationObjective(statisticManager, botProfile.getBot());
                    objective.incrementationPointsObjective(statisticManager, botProfile.getBot());
                }
            }
            botProfile.getObjectives().removeAll(validatedObjective);
            if (!validatedObjective.isEmpty()) result = true;
        }
        return result;
    }

    public Position getGardenerPosition() {
        return gameEngine.getGardenerPosition();
    }

    public Position getPandaPosition() {
        return gameEngine.getPandaPosition();
    }

    public boolean computeObjectivesPanda() {
        boolean result = false;
        ArrayList<Objective> validatedObjective = new ArrayList<>();

        for (BotProfile botProfile : botProfiles) {
            for (Objective objective : botProfile.getObjectives()) {
                if (objective.verifyPandaObj(gameEngine, botProfile)) {
                    String botName = botProfile.getBotName();
                    validatedObjective.add(objective);
                    botProfile.setObjectiveCompleted(objective);
                    logValidatedObjective(objective, botName, botProfile);
                    objective.incrementationObjective(statisticManager,botProfile.getBot());
                    objective.incrementationPointsObjective(statisticManager, botProfile.getBot());
                }
            }
            botProfile.getObjectives().removeAll(validatedObjective);
            if (!validatedObjective.isEmpty()) result = true;
        }
        return result;
    }

    public BotProfile checkWinner() {
        BotProfile winner = null;
        if (this.timeOutCounter < MAX_NB_ROUND) {
            winner = botProfiles.get(0);
            for (BotProfile botProfile : botProfiles) {
                if (botProfile.getPoints() > winner.getPoints()) {
                    winner = botProfile;
                }
            }
            for (BotProfile botProfile : botProfiles) {
                if (winner != botProfile) {
                    statisticManager.addLoser(botProfile.getBot());
                }
            }
            statisticManager.addWinner(winner.getBot());
        }

        return winner;
    }

    public List<Objective> getMyObjectives(Playable bot) {

        for (BotProfile botProfile : botProfiles) {
            if (bot == botProfile.getBot()) {
                return botProfile.getObjectives();
            }
        }
        return List.of();
    }

    private void printWinner(BotProfile botProfile) {
        if (botProfile == null) {
            LOGGER.fine("Match nul ! Aucun bot n'a su completer un objectif pendant " + MAX_NB_ROUND + " tours");
            statisticManager.addDrawGame();

        } else {
            LOGGER.fine(botProfile.getBotName() + " gagne avec : " + botProfile.getPoints() + " points");
        }
    }

    public List<Bamboo> getMyBamboos(Playable bot) {
        for (BotProfile botProfile : botProfiles) {
            if (botProfile.getBot() == bot) return botProfile.getBamboos();
        }
        return List.of();
    }

    public void addBambooToBot(Playable bot, Bamboo bamboo) {
        for (BotProfile botProfile : botProfiles) {
            if (botProfile.getBot() == bot) {
                botProfile.addBamboo(bamboo);
            }
        }
    }

    @Override
    public int getNumberOfPlayers() {
        return botProfiles.size();
    }

    private void logValidatedObjective(Objective objective, String botName, BotProfile botProfile) {
        LOGGER.finer(() -> "L'objectif suivant a été validé : " + objective);
        LOGGER.finer(() -> botName + " gagne " + objective.getPoint() + " points");
        LOGGER.finer(() -> "Le score de " + botName + " = " + botProfile.getPoints() + " points");
    }
}
