package fr.cotedazur.univ.polytech.startingpoint.bot.tools;

import fr.cotedazur.univ.polytech.startingpoint.action.Action;
import fr.cotedazur.univ.polytech.startingpoint.action.ActionType;
import fr.cotedazur.univ.polytech.startingpoint.action.MovePandaAction;
import fr.cotedazur.univ.polytech.startingpoint.bot.Playable;
import fr.cotedazur.univ.polytech.startingpoint.game.Referee;
import fr.cotedazur.univ.polytech.startingpoint.game.game_engine.items.Bamboo;
import fr.cotedazur.univ.polytech.startingpoint.game.game_engine.items.WeatherType;
import fr.cotedazur.univ.polytech.startingpoint.game.game_engine.map.Map;
import fr.cotedazur.univ.polytech.startingpoint.game.game_engine.map.Plot;
import fr.cotedazur.univ.polytech.startingpoint.game.game_engine.map.PlotType;
import fr.cotedazur.univ.polytech.startingpoint.game.game_engine.map.Position;

import java.util.ArrayList;
import java.util.List;

public class PandaBotResolver {

    Map map;
    Referee referee;
    Playable bot;

    public PandaBotResolver(Map map, Referee referee, Playable bot) {
        this.map = map;
        this.referee = referee;
        this.bot = bot;
    }


    public Action fillObjectivePanda(List<Bamboo> bambooSections, List<Bamboo> myBamboos, List<ActionType> banActionTypes, WeatherType weather) {
        ArrayList<Bamboo> missingBamboos = new ArrayList<>(bambooSections);
        for (Bamboo bamboo : myBamboos) removeBamboo(missingBamboos, bamboo);
        if (!missingBamboos.isEmpty() && !banActionTypes.contains(ActionType.MOVE_PANDA)) {
            for (Bamboo bamboo : missingBamboos) {
                MovePandaAction action = tryEatBambooOfType(bamboo.getBambooType());
                if (action != null) return action;
            }
            for (Plot plot : map.getMapPlots()) {
                if (plot.getType() == missingBamboos.get(missingBamboos.size() - 1).getBambooType() && plot.getNumberOfBamboo() > 0) {
                    return movePandaToUnlock(referee.getPandaPosition());
                }
            }
            GardenerBotResolver gardenerBotResolver = new GardenerBotResolver(map, referee);
            return gardenerBotResolver.fillObjectiveGardener(missingBamboos.get(missingBamboos.size() - 1).getBambooType(), banActionTypes, weather);
        }
        PatternBotResolver patternBotResolver = new PatternBotResolver(map, referee);
        return patternBotResolver.putRandomlyAPLot(bambooSections.get(0).getBambooType(), banActionTypes);
    }

    private MovePandaAction tryEatBambooOfType(PlotType bambooType) {
        for (Plot plot : map.getMapPlots()) {
            if (plot.getType() == bambooType && plot.getNumberOfBamboo() > 0) {
                MovePandaAction action = movePandaOnPlantation(plot.getPosition());
                if (action != null) {
                    return action;
                }
            }
        }
        return null;
    }

    private void removeBamboo(ArrayList<Bamboo> bamboos, Bamboo bambooToRemove) {
        for (int i = 0; i < bamboos.size(); ++i) {
            if (bamboos.get(i).getBambooType() == bambooToRemove.getBambooType()) {
                bamboos.remove(i);
                break;
            }
        }
    }

    private MovePandaAction movePandaToUnlock(Position pandaPosition) {
        List<Plot> potentialPlot = map.getNeighbours(pandaPosition);
        if (potentialPlot != null) {
            return new MovePandaAction(bot, potentialPlot.get(0).getPosition());
        }
        return null;
    }

    public MovePandaAction movePandaOnPlantation(Position position) {
        if (position.isMovementALine(referee.getPandaPosition())) {
            return new MovePandaAction(bot, position);
        }
        return null;
    }
}
