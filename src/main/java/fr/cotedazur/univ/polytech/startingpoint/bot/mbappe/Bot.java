package fr.cotedazur.univ.polytech.startingpoint.bot.mbappe;

import fr.cotedazur.univ.polytech.startingpoint.*;
import fr.cotedazur.univ.polytech.startingpoint.action.*;
import fr.cotedazur.univ.polytech.startingpoint.game.Referee;
import fr.cotedazur.univ.polytech.startingpoint.objective.*;

import java.util.List;
import java.util.Objects;

public class Bot {

    Referee referee;
    Map map;
    String botName;
    List<Bambou> myBambous;

    public Bot(Referee referee, Map map, String botName) {
        this.botName = botName;
        this.referee    = referee;
        this.map     = map;
        myBambous = null;
    }

    public String getBotName() {
        return botName;
    }

    public Action play(List<ActionType> banActionTypes, Weather weather) {
        this.myBambous = referee.getMyBambous(this);
        List<Objective> objectives = referee.getMyObjectives(this);
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

    public Action fillObjectiveGardener(PlotType bambouType, boolean improvement, List<ActionType> banActionTypes, Weather weather) {
        GardenerBotResolver gardenerBotResolver = new GardenerBotResolver(map, referee);
        return gardenerBotResolver.fillObjectiveGardener( bambouType, false, banActionTypes, weather);
    }

    public Action fillObjectivePanda(List<Bambou> bambouSections, List<ActionType> banActionTypes, Weather weather){
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
        Bot bot = (Bot) o;
        return Objects.equals(referee, bot.referee) && Objects.equals(map, bot.map) && Objects.equals(myBambous, bot.myBambous);
    }

    @Override
    public int hashCode() {
        return Objects.hash(referee, map, myBambous);
    }
}
