package fr.cotedazur.univ.polytech.startingpoint.bot;

import fr.cotedazur.univ.polytech.startingpoint.action.Action;
import fr.cotedazur.univ.polytech.startingpoint.action.ActionType;
import fr.cotedazur.univ.polytech.startingpoint.game.Referee;
import fr.cotedazur.univ.polytech.startingpoint.game.game_engine.items.Bamboo;
import fr.cotedazur.univ.polytech.startingpoint.game.game_engine.items.WeatherType;
import fr.cotedazur.univ.polytech.startingpoint.game.game_engine.map.Map;
import fr.cotedazur.univ.polytech.startingpoint.game.game_engine.map.Pattern;
import fr.cotedazur.univ.polytech.startingpoint.game.game_engine.map.PlotType;

import java.util.List;

public interface Playable {

    Action play(List<ActionType> banActionTypes, WeatherType weather);

    Action fillObjectiveGardener(PlotType bambouType, boolean improvement, List<ActionType> banActionTypes, WeatherType weather);

    Action fillObjectivePanda(List<Bamboo> bambouSections, List<ActionType> banActionTypes, WeatherType weather);

    Action fillObjectivePlots(Pattern pattern, List<ActionType> banActionTypes);

    void setEnvironment(Referee referee, Map map);
}
