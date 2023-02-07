package fr.cotedazur.univ.polytech.startingpoint.bot;

import fr.cotedazur.univ.polytech.startingpoint.Bambou;
import fr.cotedazur.univ.polytech.startingpoint.Pattern;
import fr.cotedazur.univ.polytech.startingpoint.PlotType;
import fr.cotedazur.univ.polytech.startingpoint.WeatherType;
import fr.cotedazur.univ.polytech.startingpoint.action.Action;
import fr.cotedazur.univ.polytech.startingpoint.action.ActionType;

import java.util.List;

public interface Playable {

    public String getBotName();
    public Action play(List<ActionType> banActionTypes, WeatherType weather);

    Action fillObjectiveGardener(PlotType bambouType, boolean improvement, List<ActionType> banActionTypes, WeatherType weather);

    Action fillObjectivePanda(List<Bambou> bambouSections, List<ActionType> banActionTypes, WeatherType weather);

    Action fillObjectivePlots(Pattern pattern, List<ActionType> banActionTypes);
}
