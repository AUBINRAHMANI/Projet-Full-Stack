package fr.cotedazur.univ.polytech.startingpoint;

import fr.cotedazur.univ.polytech.startingpoint.bot.BotProfil;
import fr.cotedazur.univ.polytech.startingpoint.bot.Playable;
import fr.cotedazur.univ.polytech.startingpoint.game.Referee;
import fr.cotedazur.univ.polytech.startingpoint.logger.Loggeable;
import fr.cotedazur.univ.polytech.startingpoint.objective.*;

import java.util.ArrayList;
import java.util.Random;

import static fr.cotedazur.univ.polytech.startingpoint.WeatherType.*;
import static fr.cotedazur.univ.polytech.startingpoint.WeatherType.QUESTIONMARK;

import java.util.Arrays;
import java.util.List;

public class GameEngine implements Loggeable {


    private Deck<Objective> objectiveDeck;
    private Deck<Plot>      plotDeck;
    private Map             map;
    private Gardener        gardener;
    private Panda           panda;

    private BotProfil botProfil_;
    private WeatherType weather;


    public GameEngine(Deck<Objective> objectiveDeck, Deck<Plot> plotDeck, Map map) {
        this.objectiveDeck = objectiveDeck;
        this.plotDeck = plotDeck;
        this.map = map;
        this.panda = new Panda();
        this.gardener = new Gardener();
        this.weather = NOMETEO;
    }

    public void regenerateDecks(Deck<Objective> objectiveDeck, Deck<Plot> plotDeck){
        this.objectiveDeck = objectiveDeck;
        this.plotDeck = plotDeck;
    }

    public Objective pickObjective() {
        return objectiveDeck.getNextCard();
    }

    public List<Plot> pickPlot() {
        return new ArrayList<>(Arrays.asList(plotDeck.getNextCard(), plotDeck.getNextCard(), plotDeck.getNextCard()));
    }

    public boolean askToPutPlot( Plot plot ){
        return  map.putPlot(plot);
    }

    public boolean askToPutIrrigation(Irrigation irrigation) {
        return map.putIrrigation(irrigation);
    }

    public Map getMap(){
        return map;
    }

    public Position getGardenerPosition(){
        return gardener.getPosition();
    }
    public Position getPandaPosition(){
        return panda.getPosition();
    }

    public boolean moveGardener(Position position){
        if(!map.isSpaceFree(position) && position.isDeplacementALine(gardener.getPosition())){
            gardener.setPosition(position);
            growBambou();
            return true;
        }
        return false;
    }

    public void growBambou(){
        Plot gardenerPlot = map.findPlot(gardener.getPosition());
        if( gardenerPlot.getPosition().isCenter()==false )gardenerPlot.growBambou();
        for(Plot plot : map.getNeighbours(gardener.getPosition())){
            if((plot.getType() == gardenerPlot.getType()) && plot.isIrrigated() && plot.getPosition().isCenter()==false ){
                plot.growBambou();
            }
        }
    }

    public boolean movePanda(Referee referee, Playable bot, Position position){
        if(!map.isSpaceFree(position) && position.isDeplacementALine(panda.getPosition())){
            panda.setPosition(position);
            eatBambou(referee, bot, position);
            return true;
        }
        return false;
    }

    public boolean eatBambou(Referee referee, Playable bot, Position position){
       Plot plot = map.findPlot(position);
       Bambou bambou = plot.eatBambou();
       if( bambou!=null && referee!=null )referee.addBamboutToBot(bot, bambou);
       return true;
    }


    public boolean computeObjectivePlot(Pattern pattern, Plot lastPLacedPlot){
        List<List<Plot>> result = map.checkIfPossibleToPlacePattern(pattern, lastPLacedPlot.getPosition());
        if(result==null){
            return false;
        }
        List<Plot> missingPlots = result.get(0);
        List<Plot> nonIrrigatedPlot = result.get(1);
        return missingPlots.isEmpty() && nonIrrigatedPlot.isEmpty();
    }


    public boolean computeObjectiveGardener(int nbBambou, PlotType bambouType, boolean improvement, int nbPlot){
        Plot plot = map.findPlot(gardener.getPosition());
        if(nbBambou> 3){
            if(plot.getNumberOfBambou() <= nbBambou && plot.getType() == bambouType){
                return true;
            }
        }
        else {
            if(plot.getNumberOfBambou() <= nbBambou || plot.getType() != bambouType)return  false;
            int nbValidatedPlots = 0;
            for(Plot neighbour : map.getNeighbours(plot.getPosition())){
                if(neighbour.getNumberOfBambou() >= nbBambou && neighbour.getType() == bambouType){
                    nbValidatedPlots++;
                }
            }
            if(nbValidatedPlots >= nbPlot-1)return true;
        }
        return false;
    }

    public boolean computeObjectivePanda(BotProfil botProfil, List<Bambou> bambousToHave){
        List<Bambou> playerBambous = new ArrayList<>(botProfil.getBambous());
        for(Bambou bambou : bambousToHave){
            if(playerBambous.contains(bambou)){
                playerBambous.remove(bambou);
            }
            else {
                return false;
            }
        }
        botProfil.setBambous(playerBambous);
        return true;
    }

    public WeatherType drawWeather(){
        Random rand = new Random();

        int choseNumber = 1+rand.nextInt(7-1);
        WeatherType weatherType = WeatherType.values()[choseNumber];
        LOGGER.finest("Face : "+weatherType);
        return weatherType;
    }

    public WeatherType getWeatherType() {
        return weather;
    }

    public boolean rainAction(Position position) {
      if(getMap().findPlot(position).isIrrigated()){
          return getMap().findPlot(position).growBambou();
      }
        return false;
    }
    public boolean thunderAction(Position position){
        if(!getMap().isSpaceFree(position)){
            panda.setPosition(position);
            return true;
        }
        return false;
    }
}
