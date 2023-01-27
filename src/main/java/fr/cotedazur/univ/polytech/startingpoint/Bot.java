package fr.cotedazur.univ.polytech.startingpoint;

import fr.cotedazur.univ.polytech.startingpoint.Action.*;
import fr.cotedazur.univ.polytech.startingpoint.objective.*;

import java.util.ArrayList;
import java.util.Objects;

public class Bot {

    Game game;
    Map map;
    String botName;
    ArrayList<Bambou> myBambous;



    public Bot(Game game, Map map, String botName) {
        this.map = map;
        this.botName = botName;
        game    = null;
        map     = null;
        myBambous = null;
    }


    public String getBotName() {
        return botName;
    }


    public Action play(Game game, Map map) {
        this.game = game;
        this.map = map;
        this.myBambous = game.getMyBambous(this);
        ArrayList<Objective> objectives = game.getMyObjectives(this);
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

    PickObjectiveAction pickObjective() {
        return new PickObjectiveAction(this);
    }

    public Action fillObjectiveGardener(int nbBambou, PlotType bambouType, boolean improvement, int nbSection) {
        ArrayList<Plot> typeValid = new ArrayList<>();
        ArrayList<Plot> typeAndDeplacementValid = new ArrayList<>();
        ArrayList<Plot> deplacementValid = new ArrayList<>();
        int maxNbBambou = 0;
        int IndexeMaxNbBambou = 0;
        if (map.getMap().size() > 1) {
            for (Plot plot : map.getMap()) {
                if (plot.getPosition().isDeplacementALine(game.getGardenerPosition())) {
                    deplacementValid.add(plot);
                }
                if (plot.getType() == bambouType) {
                    typeValid.add(plot);
                }
                if ((plot.getType() == bambouType) && (plot.getPosition().isDeplacementALine(game.getGardenerPosition()))) {
                    typeAndDeplacementValid.add(plot);
                }
            }
            if (!typeAndDeplacementValid.isEmpty()) {
                for (int i = 0; i < typeAndDeplacementValid.size(); i++) {
                    if (typeAndDeplacementValid.get(i).getNumberOfBambou() > maxNbBambou) {
                        maxNbBambou = typeAndDeplacementValid.get(i).getNumberOfBambou();
                        IndexeMaxNbBambou = i;
                    }
                }
                return new MoveGardenerAction(typeAndDeplacementValid.get(IndexeMaxNbBambou).getPosition());
            } else if (!typeValid.isEmpty()) {
                for (Plot plot : typeValid) {
                    ArrayList<Plot> neighboursPlots = map.getNeighbours(plot.getPosition());
                    for (int i = 0; i <= neighboursPlots.size(); i++) {
                        if (neighboursPlots.get(i).getPosition().isDeplacementALine(game.getGardenerPosition())) {
                            return new MoveGardenerAction(neighboursPlots.get(i).getPosition());
                        }
                        ArrayList<Plot> neighboursOfNeighboursPlots = map.getNeighbours(neighboursPlots.get(i).getPosition());
                        for (int j = 0; j <= neighboursOfNeighboursPlots.size(); j++) {
                            if (neighboursOfNeighboursPlots.get(j).getPosition().isDeplacementALine(game.getGardenerPosition())) {
                                return new MoveGardenerAction(neighboursOfNeighboursPlots.get(j).getPosition());
                            }
                        }
                    }
                }
            }
        }
        //Plot pickOnePlot = game.pickPlot(); PLot deck not implemented yet
        return putRandomlyAPLot(bambouType);
    }


    public Action fillObjectivePlots(Pattern pattern){
        for(Plot plot : map.getMap()){
            if(plot.getType() == pattern.getPlots().get(0).getType()){
                ArrayList<Plot> missingPLots = checkIfPossibleToPlacePattern(pattern, plot.getPosition());
                if( missingPLots != null){
                    for(Plot tempPlot : missingPLots){
                        Position tempPlotPosition = tempPlot.getPosition();
                        if(map.isPossibleToPutPlot(tempPlotPosition)){
                            return new PutPlotAction(tempPlot);
                        }
                        else {
                            if(map.isSpaceFree(tempPlotPosition) && map.getNeighbours(tempPlotPosition).size()>0){
                                ArrayList<Position> positions = map.closestAvailableSpace(tempPlotPosition);
                                return new PutPlotAction(new Plot(tempPlot.getType(), positions.get(0)));
                            }
                        }
                    }
                }
            }
        }
        for(Plot plot : map.getMap()){
            if(map.closestAvailableSpace(plot.getPosition()).isEmpty() == false) {
                return new PutPlotAction(new Plot(pattern.getPlots().get(0).getType(), map.closestAvailableSpace(plot.getPosition()).get(0)));
            }
        }
        return null;
    }

    private ArrayList<Plot> checkIfPossibleToPlacePattern(Pattern pattern, Position position) {
       Pattern tempPattern = new Pattern(pattern);
       for(Plot plot : pattern.getPlots()){
           tempPattern.setAncerPoint(plot.getPosition());
           for(int i=0 ; i<5 ; i++){
               ArrayList<Plot> missingPLots = map.computePatternVerification(tempPattern, position);
               if(missingPLots != null)return missingPLots;
               tempPattern.rotate60Right();
           }
           tempPattern.rotate60Right();
       }
       return null;
    }

    public Action fillObjectivePanda(ArrayList<Bambou> bambouSections){
        ArrayList<Bambou> missingBambous = new ArrayList<>(bambouSections);
        for(Bambou bambou : myBambous)removeAbBambou(missingBambous ,bambou);
        if( missingBambous.isEmpty()==false ){
            for(Bambou bambou : missingBambous){
                MovePandaAction action = movePandaOnPlantation(bambou.getBambouType());
                if( action!=null )return action;
            }
            for(Plot plot : map.getMap()){
                if(plot.getType() == missingBambous.get(missingBambous.size()-1).getBambouType() && plot.getNumberOfBambou()>0){
                    return movePandaToUnlock(game.getPandaPosition());
                }
            }
            return fillObjectiveGardener(4,missingBambous.get(missingBambous.size()-1).getBambouType(), false, 1);
        }
        return putRandomlyAPLot(bambouSections.get(0).getBambouType());
    }

    private MovePandaAction movePandaOnPlantation(PlotType bambouType) {
        for(Plot plot : map.getMap()){
            if(plot.getType()==bambouType && plot.getNumberOfBambou()>0 ){
                if(plot.getPosition().isDeplacementALine(game.getPandaPosition())){
                    return new MovePandaAction(this, plot.getPosition());
                }
            }
        }
        return null;
    }
    private void removeAbBambou(ArrayList<Bambou> bambous, Bambou bambouToRemove){
        for(int i=0; i<bambous.size() ; ++i){
            if(bambous.get(i).getBambouType() == bambouToRemove.getBambouType()){
                bambous.remove(i);
                break;
            }
        }
    }


    PutPlotAction putRandomlyAPLot(PlotType plotType){
        for(Plot plot : map.getMap()){
            ArrayList<Position> positions = map.closestAvailableSpace(plot.getPosition());
            if(positions != null && positions.isEmpty()==false ){
                return new PutPlotAction(new Plot(plotType, positions.get(0)));
            }
        }
        return null;
    }

    private MovePandaAction movePandaToUnlock(Position pandaPosition){
        ArrayList<Plot> potentialPlot = map.getNeighbours(pandaPosition);
        if( potentialPlot!=null ){
            return new MovePandaAction(this, potentialPlot.get(0).getPosition());
        }
        return null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bot bot = (Bot) o;
        return Objects.equals(game, bot.game) && Objects.equals(map, bot.map) && Objects.equals(myBambous, bot.myBambous);
    }

    @Override
    public int hashCode() {
        return Objects.hash(game, map, myBambous);
    }
}
