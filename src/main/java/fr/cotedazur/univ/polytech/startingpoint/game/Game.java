package fr.cotedazur.univ.polytech.startingpoint.game;

import fr.cotedazur.univ.polytech.startingpoint.Map;
import fr.cotedazur.univ.polytech.startingpoint.*;
import fr.cotedazur.univ.polytech.startingpoint.action.Action;
import fr.cotedazur.univ.polytech.startingpoint.action.ActionType;
import fr.cotedazur.univ.polytech.startingpoint.bot.BotProfile;
import fr.cotedazur.univ.polytech.startingpoint.bot.Playable;
import fr.cotedazur.univ.polytech.startingpoint.debug_interface.MapInterface;
import fr.cotedazur.univ.polytech.startingpoint.logger.Loggeable;
import fr.cotedazur.univ.polytech.startingpoint.objective.Objective;
import fr.cotedazur.univ.polytech.startingpoint.objective.ObjectiveGardener;
import fr.cotedazur.univ.polytech.startingpoint.objective.ObjectivePanda;
import fr.cotedazur.univ.polytech.startingpoint.objective.ObjectivePlots;
import fr.cotedazur.univ.polytech.startingpoint.statistique_manager.StatisticManager;

import java.security.SecureRandom;
import java.util.*;


public class Game implements DeckSignal, Referee, Loggeable {

    static final int MAX_NB_ROUND = 100;
    static final int NB_OBJECTIVE_TO_FINISH = 9;
    static final int NB_ACTIONS_PER_ROUND = 2;
    private final StatisticManager statistiqueManager;
    Random random;
    GameEngine gameEngine;
    List<BotProfile> botProfiles;
    MapInterface mapInterface;
    int nbActions;
    List<Action> previousActions;
    private int timeOutCounter;
    private int nombreObjectifNull;

    public Game() {
        this(null, List.of(), false);
    }

    public Game(StatisticManager statistiqueManager, List<BotProfile> botProfiles, boolean debug) {
        this.random = new SecureRandom();
        previousActions = new ArrayList<>();
        Deck<Objective> objectiveDeck = generateObjectiveDrawPile();
        Deck<Plot> plotDeck = generatePlotDrawPile();
        gameEngine = new GameEngine(objectiveDeck, plotDeck, new Map());
        this.botProfiles = botProfiles;
        this.statistiqueManager = statistiqueManager;
        this.timeOutCounter = 0;

        for (BotProfile botProfil : this.botProfiles) {
            botProfil.getBot().setEnvironment(this, gameEngine.getMap());
        }

        if (debug) {
            mapInterface = new MapInterface();
            mapInterface.drawMap(gameEngine.getMap(), gameEngine.getGardenerPosition(), gameEngine.getPandaPosition());
        } else {
            mapInterface = null;
        }
    }

    public void start() {
        do {
            ++timeOutCounter;
            statistiqueManager.addRound();
            for (BotProfile botProfil : botProfiles) {
                nbActions = NB_ACTIONS_PER_ROUND;
                WeatherType weather = gameEngine.drawWeather();
                LOGGER.finest("Tour de " + botProfil.getBotName() + " : ");
                this.applyChangesDueToWeather(weather);
                doActions(botProfil, nbActions, weather);
            }
            LOGGER.finest(() -> "Nombre de tours :" + this.nombreObjectifNull);
        } while (!checkFinishingCondition());
        BotProfile winner = checkWinner();

        statistiqueManager.addGame();


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


    public void doActions(BotProfile botProfil, int nbActions, WeatherType weather) {
        List<ActionType> banActionTypes = new ArrayList<>();
        for (int i = 0; i < nbActions; i++) {
            Action action = botProfil.getBot().play(banActionTypes, weather);
            LOGGER.finer(() -> "Action : " + action);
            if (action != null && !(banActionTypes.contains(action.toType()))) {
                if(mapInterface!=null) {
                    while (mapInterface.next() == false) ;
                }
                action.play(this, gameEngine);
                banActionTypes.add(action.toType());
                action.verifyObjectiveAfterAction(this);
                saveAction(action);
                if(mapInterface!=null){
                    mapInterface.drawMap(gameEngine.getMap(), gameEngine.getGardenerPosition(), gameEngine.getPandaPosition());
                }
            }
        }
    }

    public void applyChangesDueToWeather(WeatherType weather) {
        if (weather == WeatherType.SUN) {
            ++this.nbActions;
        }
    }

    public boolean checkFinishingCondition() {
        if (this.timeOutCounter >= MAX_NB_ROUND) return true;
        for (BotProfile botProfil : botProfiles) {
            if (botProfil.getNbCompletedObjective() >= NB_OBJECTIVE_TO_FINISH) return true;
        }
        return false;
    }

    @Override
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
            int nbBambous = 4;
            objectiveDeck.addCard(new ObjectiveGardener(random.nextInt(4) + 1, nbBambous, PlotType.values()[random.nextInt(upperRandForPlotType) + 1], false, 1));

        }

        for (int i = 0; i < 20; ++i) {
            ArrayList<Bamboo> bambous = new ArrayList<>();
            for (int j = 0; j < (random.nextInt(2) + 2); ++j) {
                bambous.add(new Bamboo(PlotType.values()[random.nextInt(upperRandForPlotType) + 1]));
            }
            objectiveDeck.addCard(new ObjectivePanda(random.nextInt(4) + 1, bambous));
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
        for (BotProfile botProfil : botProfiles) {
            if (bot == botProfil.getBot()) {
                botProfil.addObjective(objective);
                LOGGER.finer(() -> botProfil.getBotName() + " a prix un objectif :" + objective);
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
        for (BotProfile botProfil : botProfiles) {
            for (Objective objective : botProfil.getObjectives()) {
                if (objective.verifyPlotObj(gameEngine, lastPlacedPlot)) {
                    String botName = botProfil.getBotName();
                    validatedObjective.add(objective);
                    botProfil.setObjectiveCompleted(objective);
                    timeOutCounter = 0;
                    LOGGER.finer(()-> "L'objectif suivant a été validé : " + objective);
                    LOGGER.finer(()-> botName + " gagne " + objective.getPoint() + " points");
                    LOGGER.finer(()-> "Le score de " + botName + " = " + botProfil.getPoints() + " points");
                    logValidatedObjective(objective, botName, botProfil);
                }
            }
            botProfil.getObjectives().removeAll(validatedObjective);
            if (!validatedObjective.isEmpty()) result = true;
        }
        return result;
    }

    public boolean computeObjectivesGardener() {
        boolean result = false;
        ArrayList<Objective> validatedObjective = new ArrayList<>();
        for (BotProfile botProfil : botProfiles) {
            for (Objective objective : botProfil.getObjectives()) {
                if (objective.verifyGardenerObj(gameEngine)) {
                    String botName = botProfil.getBotName();
                    validatedObjective.add(objective);
                    botProfil.setObjectiveCompleted(objective);
                    logValidatedObjective(objective, botName, botProfil);
                }
            }
            botProfil.getObjectives().removeAll(validatedObjective);
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

        for (BotProfile botProfil : botProfiles) {
            for (Objective objective : botProfil.getObjectives()) {
                if (objective.verifyPandaObj(gameEngine, botProfil)) {
                    String botName = botProfil.getBotName();
                    validatedObjective.add(objective);
                    botProfil.setObjectiveCompleted(objective);
                    logValidatedObjective(objective, botName, botProfil);
                }
            }
            botProfil.getObjectives().removeAll(validatedObjective);
            if (!validatedObjective.isEmpty()) result = true;
        }
        return result;
    }

    public BotProfile checkWinner() {
        BotProfile winner = null;
        if (this.timeOutCounter < MAX_NB_ROUND) {
            winner = botProfiles.get(0);
            for (BotProfile botProfil : botProfiles) {
                if (botProfil.getPoints() > winner.getPoints()) {
                    winner = botProfil;
                }
            }
            for (BotProfile botProfil : botProfiles) {
                if (winner != botProfil) {
                    statistiqueManager.addLoser(botProfil.getBot());
                }
            }
            statistiqueManager.addWinner(winner.getBot());
        }

        return winner;
    }

    public List<Objective> getMyObjectives(Playable bot) {

        for (BotProfile botProfil : botProfiles) {
            if (bot == botProfil.getBot()) {
                return botProfil.getObjectives();
            }
        }
        return List.of();
    }

    public void printWinner(BotProfile botProfil) {
        if (botProfil == null) {
            LOGGER.fine("Match nul ! Aucun bot n'a su completer un objectif pendant " + MAX_NB_ROUND + " tours");
            statistiqueManager.addDrawGame();

        } else {
            LOGGER.fine(botProfil.getBotName() + " gagne avec : " + botProfil.getPoints() + " points");
        }
    }

    public List<Bamboo> getMyBambous(Playable bot) {
        for (BotProfile botProfil : botProfiles) {
            if (botProfil.getBot() == bot) return botProfil.getBamboos();
        }
        return List.of();
    }

    public void addBamboutToBot(Playable bot, Bamboo bambou) {
        for (BotProfile botProfil : botProfiles) {
            if (botProfil.getBot() == bot) {
                botProfil.addBamboo(bambou);
            }
        }
    }

    @Override
    public int getNumberOfPlayers() {
        return botProfiles.size();
    }

    private void logValidatedObjective(Objective objective, String botName, BotProfile botProfil) {
        LOGGER.finer(() -> "L'objectif suivant a été validé : " + objective);
        LOGGER.finer(() -> botName + " gagne " + objective.getPoint() + " points");
        LOGGER.finer(() -> "Le score de " + botName + " = " + botProfil.getPoints() + " points");
    }
}
