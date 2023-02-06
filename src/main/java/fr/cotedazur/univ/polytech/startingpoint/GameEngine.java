package fr.cotedazur.univ.polytech.startingpoint;

import fr.cotedazur.univ.polytech.startingpoint.action.Action;
import fr.cotedazur.univ.polytech.startingpoint.objective.*;

import java.util.ArrayList;
import java.util.Random;

import static fr.cotedazur.univ.polytech.startingpoint.WeatherType.*;
import static fr.cotedazur.univ.polytech.startingpoint.WeatherType.QUESTIONMARK;
import fr.cotedazur.univ.polytech.startingpoint.game.Referee;
import fr.cotedazur.univ.polytech.startingpoint.game.Game;

import java.util.Arrays;
import java.util.List;

public class GameEngine {


    private Deck<Objective> objectiveDeck;
    private Deck<Plot>      plotDeck;
    private Map             map;
    private Gardener        gardener;
    private Panda           panda;

    private BotProfil botProfil_;
    private Weather weather;


    public GameEngine(Deck<Objective> objectiveDeck, Deck<Plot> plotDeck, Map map) {
        this.objectiveDeck = objectiveDeck;
        this.plotDeck = plotDeck;
        this.map = map;

        this.panda = new Panda();

        this.gardener = new Gardener();
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

    public boolean movePanda(Referee referee, Bot bot, Position position){
        if(!map.isSpaceFree(position) && position.isDeplacementALine(panda.getPosition())){
            panda.setPosition(position);
            eatBambou(referee, bot, position);
            return true;
        }
        return false;
    }

    public boolean eatBambou(Referee referee, Bot bot, Position position){
       Plot plot = map.findPlot(position);
       Bambou bambou = plot.eatBambou();
       if( bambou!=null && referee!=null )referee.addBamboutToBot(bot, bambou);
       return true;
    }


    public boolean computeObjectivePlot(Pattern pattern, Plot lastPLacedPlot){
        List<Plot> missingPlots = map.checkIfPossibleToPlacePattern(pattern, lastPLacedPlot.getPosition());
        return missingPlots != null && missingPlots.isEmpty();
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
        int max = 6;
        int choseNumber = rand.nextInt(max+1);
        switch (choseNumber){
            case 1 :
                weather.setWeatherType(SUN);
                System.out.println("Face : SOLEIL\nAction supplémentaire");
                break;
            case 2 :
                weather.setWeatherType(RAIN);
                System.out.println("Face : PLUIE\nAjoute une section à la parcelle choisie");
                break;
            case 3 :
                weather.setWeatherType(WIND);
                System.out.println("Face : VENT\nDeux actions similaires peuvent être effectuées");
                break;
            case 4 :
                weather.setWeatherType(THUNDER);
                System.out.println("Face : ORAGE\nDéplacez le panda");
                break;
            case 5 :
                weather.setWeatherType(CLOUD);
                System.out.println("Face : NUAGE\nChoisissez un aménagement");
                break;
            case 6 :
                weather.setWeatherType(QUESTIONMARK);
                System.out.println("Face : ?\nChoisissez la météo de votre choix");
                break;
        }
        return null;
    }

    public void doAction(Game game){
        Action action = botProfil_.getBot().play();
        System.out.println("Il joue l'action " + action);
        action.play(game, this);
        action.verifyObjectiveAfterAction(game);
    }
    public void applyChangesDueToWeather(Game game){
        int nbAction = 2;
        switch (weather.getWeather()){
            case SUN :
                int nbActionSun = 3;
                for(int i = 0; i < nbActionSun; i++){
                    doAction(game);
                }
                break;
            case RAIN :
                map.getMapPlots().get(0).growBambou();
                for(int i = 0; i < nbAction; i++){
                    doAction(game);
                }
                break;
            case THUNDER :
                Position position = new Position(0,0);
                movePanda(game, botProfil_.getBot(), position);
                for(int i = 0; i < nbAction; i++){
                    doAction(game);
                }
                break;
            case WIND :
                for(int i = 0; i < nbAction; i++){
                    Action action = botProfil_.getBot().play();
                    System.out.println("Il joue l'action " + action);
                    action.play(game, this);
                    action.verifyObjectiveAfterAction(game);
                    System.out.println("Il rejoue l'action " + action);
                    action.play(game, this);
                    action.verifyObjectiveAfterAction(game);
                }
                break;
            case CLOUD :
                break;
            case QUESTIONMARK:
                break;
        }
    }

    public boolean rainAction(Position position) {
      if(getMap().findPlot(position).isIrrigated()){
          return getMap().findPlot(position).growBambou();
      }
        return false;
    }
}
