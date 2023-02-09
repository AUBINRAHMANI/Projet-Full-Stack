package fr.cotedazur.univ.polytech.startingpoint;

import fr.cotedazur.univ.polytech.startingpoint.bot.BotProfile;
import fr.cotedazur.univ.polytech.startingpoint.bot.Playable;
import fr.cotedazur.univ.polytech.startingpoint.game.Referee;
import fr.cotedazur.univ.polytech.startingpoint.logger.Loggeable;
import fr.cotedazur.univ.polytech.startingpoint.objective.Objective;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static fr.cotedazur.univ.polytech.startingpoint.WeatherType.values;

public class GameEngine implements Loggeable {

    private final Map map;
    private final Gardener gardener;
    private final Panda panda;
    Random random;
    private Deck<Objective> objectiveDeck;
    private Deck<Plot> plotDeck;


    public GameEngine(Deck<Objective> objectiveDeck, Deck<Plot> plotDeck, Map map) {
        this.random = new SecureRandom();
        this.objectiveDeck = objectiveDeck;
        this.plotDeck = plotDeck;
        this.map = map;
        this.panda = new Panda();
        this.gardener = new Gardener();
    }

    public void regenerateDecks(Deck<Objective> objectiveDeck, Deck<Plot> plotDeck) {
        this.objectiveDeck = objectiveDeck;
        this.plotDeck = plotDeck;
    }

    public Objective pickObjective() {
        return objectiveDeck.getNextCard();
    }

    public List<Plot> pickPlot() {
        return new ArrayList<>(Arrays.asList(plotDeck.getNextCard(), plotDeck.getNextCard(), plotDeck.getNextCard()));
    }

    public boolean askToPutPlot(Plot plot) {
        return map.putPlot(plot);
    }

    public boolean askToPutIrrigation(Irrigation irrigation) {
        return map.putIrrigation(irrigation);
    }

    public Map getMap() {
        return map;
    }

    public Position getGardenerPosition() {
        return gardener.getPosition();
    }

    public Position getPandaPosition() {
        return panda.getPosition();
    }

    public boolean moveGardener(Position position) {
        if (!map.isSpaceFree(position) && position.isMovementALine(gardener.getPosition())) {
            gardener.setPosition(position);
            growBamboo();
            return true;
        }
        return false;
    }

    public void growBamboo() {
        Plot gardenerPlot = map.findPlot(gardener.getPosition());
        if (gardenerPlot.getPosition().isCenter()) gardenerPlot.growBamboo();
        for (Plot plot : map.getNeighbours(gardener.getPosition())) {
            if ((plot.getType() == gardenerPlot.getType()) && plot.isIrrigated() && plot.getPosition().isCenter()) {
                plot.growBamboo();
            }
        }
    }

    public boolean movePanda(Referee referee, Playable bot, Position position) {
        if (!map.isSpaceFree(position) && position.isMovementALine(panda.getPosition())) {
            panda.setPosition(position);
            eatBamboo(referee, bot, position);
            return true;
        }
        return false;
    }

    public void eatBamboo(Referee referee, Playable bot, Position position) {
        Plot plot = map.findPlot(position);
        Bamboo bamboo = plot.eatBamboo();
        if (bamboo != null && referee != null) referee.addBamboutToBot(bot, bamboo);
    }


    public boolean computeObjectivePlot(Pattern pattern, Plot lastPLacedPlot) {
        List<List<Plot>> result = map.checkIfPossibleToPlacePattern(pattern, lastPLacedPlot.getPosition());
        if (result == null) {
            return false;
        }
        List<Plot> missingPlots = result.get(0);
        List<Plot> nonIrrigatedPlot = result.get(1);
        return missingPlots.isEmpty() && nonIrrigatedPlot.isEmpty();
    }


    public boolean computeObjectiveGardener(int nbBamboo, PlotType bambooType, int nbPlot) {
        Plot plot = map.findPlot(gardener.getPosition());
        if (nbBamboo > 3) {
            return plot.getNumberOfBamboo() <= nbBamboo && plot.getType() == bambooType;
        } else {
            if (plot.getNumberOfBamboo() <= nbBamboo || plot.getType() != bambooType) return false;
            int nbValidatedPlots = 0;
            for (Plot neighbour : map.getNeighbours(plot.getPosition())) {
                if (neighbour.getNumberOfBamboo() >= nbBamboo && neighbour.getType() == bambooType) {
                    nbValidatedPlots++;
                }
            }
            return nbValidatedPlots >= nbPlot - 1;
        }
    }

    public boolean computeObjectivePanda(BotProfile botProfil, List<Bamboo> bamboosToHave) {
        List<Bamboo> playerBamboos = new ArrayList<>(botProfil.getBamboos());
        for (Bamboo bamboo : bamboosToHave) {
            if (playerBamboos.contains(bamboo)) {
                playerBamboos.remove(bamboo);
            } else {
                return false;
            }
        }
        botProfil.setBamboos(playerBamboos);
        return true;
    }


    public WeatherType drawWeather() {
        int choseNumber = 1 + random.nextInt(7 - 1);
        WeatherType weather = values()[choseNumber];
        LOGGER.finest(() -> "Face : " + weather);
        return weather;
    }

    public boolean rainAction(Position position) {
        if (getMap().findPlot(position).isIrrigated()) {
            return getMap().findPlot(position).growBamboo();
        }
        return false;
    }

    public boolean thunderAction(Position position) {
        if (!getMap().isSpaceFree(position)) {
            panda.setPosition(position);
            return true;
        }
        return false;
    }
}
