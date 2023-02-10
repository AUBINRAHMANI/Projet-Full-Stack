package fr.cotedazur.univ.polytech.startingpoint.bot;

import fr.cotedazur.univ.polytech.startingpoint.action.Action;
import fr.cotedazur.univ.polytech.startingpoint.action.ActionType;
import fr.cotedazur.univ.polytech.startingpoint.action.PickObjectiveAction;
import fr.cotedazur.univ.polytech.startingpoint.bot.tools.GardenerBotResolver;
import fr.cotedazur.univ.polytech.startingpoint.bot.tools.PandaBotResolver;
import fr.cotedazur.univ.polytech.startingpoint.bot.tools.PatternBotResolver;
import fr.cotedazur.univ.polytech.startingpoint.game.Referee;
import fr.cotedazur.univ.polytech.startingpoint.game.game_engine.items.Bamboo;
import fr.cotedazur.univ.polytech.startingpoint.game.game_engine.items.WeatherType;
import fr.cotedazur.univ.polytech.startingpoint.game.game_engine.map.Map;
import fr.cotedazur.univ.polytech.startingpoint.game.game_engine.map.Pattern;
import fr.cotedazur.univ.polytech.startingpoint.game.game_engine.map.PlotType;
import fr.cotedazur.univ.polytech.startingpoint.objective.Objective;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public class BotMbappe implements fr.cotedazur.univ.polytech.startingpoint.bot.Playable {

    Referee referee;
    Map map;
    List<Bamboo> myBamboos;

    public BotMbappe() {
        this(null, null);
    }

    public BotMbappe(Referee referee, Map map) {
        this.referee = referee;
        this.map = map;
        myBamboos = null;
    }

    public void setEnvironment(Referee referee, Map map) {
        this.referee = referee;
        this.map = map;
    }


    public Action play(List<ActionType> banActionTypes, WeatherType weather) {
        this.myBamboos = referee.getMyBamboos(this);
        List<Objective> objectives = referee.getMyObjectives(this);
        objectives.sort(Comparator.comparing(Objective::getPoint));
        if (objectives.isEmpty()) return pickObjective();
        for (Objective objective : objectives) {
            Action action = objective.tryToFillObjective(this, banActionTypes, weather);
            if (action != null) {
                return action;
            }
        }
        return new PickObjectiveAction(this);
    }

    public Action pickObjective() {
        return new PickObjectiveAction(this);
    }

    public Action fillObjectiveGardener(PlotType bambooType, boolean improvement, List<ActionType> banActionTypes, WeatherType weather) {
        GardenerBotResolver gardenerBotResolver = new GardenerBotResolver(map, referee);
        return gardenerBotResolver.fillObjectiveGardener(bambooType, banActionTypes, weather);
    }

    public Action fillObjectivePanda(List<Bamboo> bambooSections, List<ActionType> banActionTypes, WeatherType weather) {
        PandaBotResolver pandaBotResolver = new PandaBotResolver(map, referee, this);
        return pandaBotResolver.fillObjectivePanda(bambooSections, myBamboos, banActionTypes, weather);
    }

    public Action fillObjectivePlots(Pattern pattern, List<ActionType> banActionTypes) {
        PatternBotResolver patternBotResolver = new PatternBotResolver(map, referee);
        return patternBotResolver.fillObjectivePlots(pattern, banActionTypes);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BotMbappe bot = (BotMbappe) o;
        return Objects.equals(referee, bot.referee) && Objects.equals(map, bot.map) && Objects.equals(myBamboos, bot.myBamboos);
    }

    @Override
    public int hashCode() {
        return Objects.hash(referee, map, myBamboos);
    }
}
