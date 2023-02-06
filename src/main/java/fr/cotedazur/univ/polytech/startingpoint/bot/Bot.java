package fr.cotedazur.univ.polytech.startingpoint.bot;

import fr.cotedazur.univ.polytech.startingpoint.*;
import fr.cotedazur.univ.polytech.startingpoint.action.*;
import fr.cotedazur.univ.polytech.startingpoint.game.Referee;
import fr.cotedazur.univ.polytech.startingpoint.objective.*;

import java.util.ArrayList;
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

    public Action play() {
        this.myBambous = referee.getMyBambous(this);
        List<Objective> objectives = referee.getMyObjectives(this);
        if (objectives == null) {
            assert false;
            return null;
        } else {
            if (objectives.isEmpty()) return pickObjective();
            else {
                return objectives.get(0).tryToFillObjective(this);
            }
        }
    }

    public Action pickObjective() {
        return new PickObjectiveAction(this);
    }

    public Action fillObjectiveGardener(PlotType bambouType, boolean improvement) {
        GardenerBotResolver gardenerBotResolver = new GardenerBotResolver(map, referee);
        return gardenerBotResolver.fillObjectiveGardener( bambouType, false);
    }

    public Action fillObjectivePanda(List<Bambou> bambouSections) {
        PandaBotResolver pandaBotResolver = new PandaBotResolver(map, referee, this);
        return pandaBotResolver.fillObjectivePanda(bambouSections, myBambous);
    }

    public Action fillObjectivePlots(Pattern pattern) {
        PatternBotResolver patternBotResolver = new PatternBotResolver(map, referee);
        return patternBotResolver.fillObjectivePlots(pattern);
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
