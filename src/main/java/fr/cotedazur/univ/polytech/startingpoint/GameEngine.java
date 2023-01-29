package fr.cotedazur.univ.polytech.startingpoint;

import fr.cotedazur.univ.polytech.startingpoint.Action.Action;
import fr.cotedazur.univ.polytech.startingpoint.objective.*;

import java.util.ArrayList;
import java.util.Random;

import static fr.cotedazur.univ.polytech.startingpoint.WeatherType.*;
import static fr.cotedazur.univ.polytech.startingpoint.WeatherType.QUESTIONMARK;

public class GameEngine {


    private Deck<Objective> objectiveDeck_;
    private Deck<Plot>      plotDeck_;
    private Map             map_;
    private Gardener        gardener_;
    private Panda           panda;

    private BotProfil botProfil_;
    private Weather weather;


    public GameEngine(Deck<Objective> objectiveDeck, Deck<Plot> plotDeck, Map map) {
        objectiveDeck_              = objectiveDeck;
        plotDeck_                   = plotDeck;
        map_                        = map;

        panda                       = new Panda();

        gardener_                   = new Gardener();
    }

    public fr.cotedazur.univ.polytech.startingpoint.objective.Objective pickObjective() {
        return objectiveDeck_.getNextCard();
    }

    public Plot pickPlot() {
        return plotDeck_.getNextCard();
    }

    public boolean askToPutPlot( Plot plot ){
        return  map_.putPlot(plot);
    }

    public Map getMap(){
        return map_;
    }

    public Position getGardenerPosition(){
        return gardener_.getPosition();
    }
    public Position getPandaPosition(){
        return panda.getPosition();
    }

    public boolean moveGardener(Position position){
        if(!map_.isSpaceFree(position) && position.isDeplacementALine(gardener_.getPosition())){
            gardener_.setPosition(position);
            growBambou();
            return true;
        }
        return false;
    }

    public void growBambou(){
        Plot gardenerPlot = map_.findPlot(gardener_.getPosition());
        if( gardenerPlot.getPosition().isCenter()==false )gardenerPlot.growBambou();
        for(Plot plot : map_.getNeighbours(gardener_.getPosition())){
            if((plot.getType() == gardenerPlot.getType()) && plot.isIrrigated() && plot.getPosition().isCenter()==false ){
                plot.growBambou();
            }
        }
    }

    public boolean movePanda(Game game, Bot bot, Position position){
        if(!map_.isSpaceFree(position) && position.isDeplacementALine(panda.getPosition())){
            panda.setPosition(position);
            eatBambou(game, bot, position);
            return true;
        }
        return false;
    }

    public boolean eatBambou(Game game, Bot bot, Position position){
       Plot plot = map_.findPlot(position);
       Bambou bambou = plot.eatBambou();
       if( bambou!=null && game!=null )game.addBamboutToBot(bot, bambou);
       return true;
    }


    public boolean computeObjectivePlot(Pattern pattern, Plot lastPLacedPlot){
        pattern = new Pattern(pattern);
        int area_size = pattern.size();
        Position lastPlacedPosition = lastPLacedPlot.getPosition();
        for (int i=0; i<area_size/2 ; ++i){
            pattern.translateRight();
        }
        for (int i=0; i<area_size/4 ; ++i){
            pattern.translateUp();
        }

        for (int i=0; i<area_size ; ++i){
            for (int j=0; j<6 ; ++j){
                for (int k=0 ; k<area_size ; ++k){
                    ArrayList<Plot> incompletePlot = map_.computePatternVerification(new Pattern(pattern), lastPlacedPosition);
                    if(incompletePlot != null  && incompletePlot.isEmpty())return true;
                    pattern.translateDown();
                }
                for (int k=0 ; k<area_size ; ++k){
                    pattern.translateUp();
                }
                pattern.rotate60Right();
            }
            pattern.translateLeft();
        }
        return false;
    }


    public boolean computeObjectiveGardener(int nbBambou, PlotType bambouType, boolean improvement, int nbPlot){
        Plot plot = map_.findPlot(gardener_.getPosition());
        if(nbBambou> 3){
            if(plot.getNumberOfBambou() <= nbBambou && plot.getType() == bambouType){
                return true;
            }
        }
        else {
            if(plot.getNumberOfBambou() <= nbBambou || plot.getType() != bambouType)return  false;
            int nbValidatedPlots = 0;
            for(Plot neighbour : map_.getNeighbours(plot.getPosition())){
                if(neighbour.getNumberOfBambou() >= nbBambou && neighbour.getType() == bambouType){
                    nbValidatedPlots++;
                }
            }
            if(nbValidatedPlots >= nbPlot-1)return true;
        }
        return false;
    }

    public boolean computeObjectivePanda(BotProfil botProfil, ArrayList<Bambou> bambousToHave){
        ArrayList<Bambou> playerBambous = new ArrayList<>(botProfil.getBambous());
        ArrayList<Bambou> BambousToRemove = new ArrayList<>();
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
        Action action = botProfil_.getBot_().play(game, this.getMap());
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
                map_.getMap().get(0).growBambou();
                for(int i = 0; i < nbAction; i++){
                    doAction(game);
                }
                break;
            case THUNDER :
                Position position = new Position(0,0);
                movePanda(game, botProfil_.getBot_(), position);
                for(int i = 0; i < nbAction; i++){
                    doAction(game);
                }
                break;
            case WIND :
                for(int i = 0; i < nbAction; i++){
                    Action action = botProfil_.getBot_().play(game, this.getMap());
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
}
