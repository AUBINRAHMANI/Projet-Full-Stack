package fr.cotedazur.univ.polytech.startingpoint.bot;

import fr.cotedazur.univ.polytech.startingpoint.*;
import fr.cotedazur.univ.polytech.startingpoint.action.*;
import fr.cotedazur.univ.polytech.startingpoint.bot.tools.GardenerBotResolver;
import fr.cotedazur.univ.polytech.startingpoint.bot.tools.PandaBotResolver;
import fr.cotedazur.univ.polytech.startingpoint.bot.tools.PatternBotResolver;
import fr.cotedazur.univ.polytech.startingpoint.game.Referee;
import fr.cotedazur.univ.polytech.startingpoint.objective.*;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public class BotMbappe implements fr.cotedazur.univ.polytech.startingpoint.bot.Playable {

    Referee referee;
    Map map;
    String botName;
    List<Bambou> myBambous;

    public BotMbappe() {
        this(null, null);
    }
    public BotMbappe(Referee referee, Map map) {
        this.botName = botName;
        this.referee    = referee;
        this.map     = map;
        myBambous = null;
    }

    public void setEnvirronement(Referee referee, Map map){
        this.referee = referee;
        this.map = map;
        this.botName = botName;
    }

    public String getBotName() {
        return botName;
    }

    public Action play(List<ActionType> banActionTypes, WeatherType weather) {
        this.myBambous = referee.getMyBambous(this);
        List<Objective> objectives = referee.getMyObjectives(this);
        Collections.sort(objectives, Comparator.comparing(Objective::getPoint));
        if (objectives.isEmpty()) return pickObjective();
        for(Objective objective : objectives) {
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

    public Action fillObjectiveGardener(PlotType bambouType, boolean improvement, List<ActionType> banActionTypes, WeatherType weather) {
        GardenerBotResolver gardenerBotResolver = new GardenerBotResolver(map, referee);
        return gardenerBotResolver.fillObjectiveGardener( bambouType, banActionTypes, weather);
    }

    public Action fillObjectivePanda(List<Bambou> bambouSections, List<ActionType> banActionTypes, WeatherType weather){
        PandaBotResolver pandaBotResolver = new PandaBotResolver(map, referee, this);
        return pandaBotResolver.fillObjectivePanda(bambouSections, myBambous, banActionTypes, weather);
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
        return Objects.equals(referee, bot.referee) && Objects.equals(map, bot.map) && Objects.equals(myBambous, bot.myBambous);
    }

    @Override
    public int hashCode() {
        return Objects.hash(referee, map, myBambous);
    }

    @Override
    public String toString() {
        return getBotName();
    }
}
