package fr.cotedazur.univ.polytech.startingpoint.bot;

import fr.cotedazur.univ.polytech.startingpoint.*;
import fr.cotedazur.univ.polytech.startingpoint.action.Action;
import fr.cotedazur.univ.polytech.startingpoint.action.ActionType;
import fr.cotedazur.univ.polytech.startingpoint.game.Referee;

import java.util.List;

public interface Playable {

    Action play(List<ActionType> banActionTypes, WeatherType weather);

    Action fillObjectiveGardener(PlotType bambouType, boolean improvement, List<ActionType> banActionTypes, WeatherType weather);

    Action fillObjectivePanda(List<Bambou> bambouSections, List<ActionType> banActionTypes, WeatherType weather);

    Action fillObjectivePlots(Pattern pattern, List<ActionType> banActionTypes);

    void setEnvirronement(Referee referee, Map map);
}
