package fr.cotedazur.univ.polytech.startingpoint;

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

    PickObjectiveAction pickObjective() {
        return new PickObjectiveAction(this);
    }

    public Action fillObjectiveGardener( PlotType bambouType, boolean improvement) {
        ArrayList<Plot> typeValid = new ArrayList<>();
        ArrayList<Plot> typeAndDeplacementValid = new ArrayList<>();
        int maxNbBambou = 0;
        int indexMaxNbBambou = 0;
        if (map.getMapPlots().size() > 1) {
            for (Plot plot : map.getMapPlots()) {
                if(plot.isIrrigated() && plot.getNumberOfBambou()<4 ){
                    if (plot.getType() == bambouType) {
                        typeValid.add(plot);
                    }
                    if ((plot.getType() == bambouType) && (plot.getPosition().isDeplacementALine(referee.getGardenerPosition()))) {
                        typeAndDeplacementValid.add(plot);
                    }
                }
            }
            if ( typeAndDeplacementValid.isEmpty()==false ) {
                for (int i = 0; i < typeAndDeplacementValid.size(); i++) {
                    int numberOfBambou  = typeAndDeplacementValid.get(i).getNumberOfBambou();
                    if ( numberOfBambou > maxNbBambou) {
                        maxNbBambou = typeAndDeplacementValid.get(i).getNumberOfBambou();
                        indexMaxNbBambou = i;
                    }
                }
                return new MoveGardenerAction(typeAndDeplacementValid.get(indexMaxNbBambou).getPosition());

            } else if (!typeValid.isEmpty()) {
                for (Plot plot : typeValid) {
                    List<Plot> neighboursPlots = map.getNeighbours(plot.getPosition());
                    for (int i = 0; i <= neighboursPlots.size(); i++) {
                        if (neighboursPlots.get(i).getPosition().isDeplacementALine(referee.getGardenerPosition())) {
                            return new MoveGardenerAction(neighboursPlots.get(i).getPosition());
                        }
                        List<Plot> neighboursOfNeighboursPlots = map.getNeighbours(neighboursPlots.get(i).getPosition());
                        for (int j = 0; j <= neighboursOfNeighboursPlots.size(); j++) {
                            if (neighboursOfNeighboursPlots.get(j).getPosition().isDeplacementALine(referee.getGardenerPosition())) {
                                return new MoveGardenerAction(neighboursOfNeighboursPlots.get(j).getPosition());
                            }
                        }
                    }
                }
            }
        }
        return putRandomlyAPLot(bambouType);
    }


    public Action fillObjectivePlots(Pattern pattern){
        for(Plot plot : map.getMapPlots()){
            if(plot.getType() == pattern.getPlots().get(0).getType()){
                List<Plot> missingPLots = map.checkIfPossibleToPlacePattern(pattern, plot.getPosition());
                if( missingPLots != null){
                    for(Plot tempPlot : missingPLots){
                        Position tempPlotPosition = tempPlot.getPosition();
                        if(map.isPossibleToPutPlot(tempPlotPosition)){
                            return new PutPlotAction(tempPlot);
                        }
                        else {
                            if(map.getNeighbours(tempPlotPosition).isEmpty()==false ){
                                List<Position> positions = map.closestAvailableSpace(tempPlotPosition);
                                for(Position position : positions){
                                    if(map.isPossibleToPutPlot(position)){
                                        return new PutPlotAction(new Plot(tempPlot.getType(), position));
                                    }
                                }

                            }
                        }
                    }
                }
            }
        }
        for(Plot plot : map.getMapPlots()){
            if(map.closestAvailableSpace(plot.getPosition()).isEmpty() == false) {
                return new PutPlotAction(new Plot(pattern.getPlots().get(0).getType(), map.closestAvailableSpace(plot.getPosition()).get(0)));
            }
        }
        return null;
    }

    public Action fillObjectivePanda(List<Bambou> bambouSections){
        ArrayList<Bambou> missingBambous = new ArrayList<>(bambouSections);
        for(Bambou bambou : myBambous)removeBambou(missingBambous ,bambou);
        if( missingBambous.isEmpty()==false ){
            for(Bambou bambou : missingBambous){
                MovePandaAction action = movePandaOnPlantation(bambou.getBambouType());
                if( action!=null )return action;
            }
            for(Plot plot : map.getMapPlots()){
                if(plot.getType() == missingBambous.get(missingBambous.size()-1).getBambouType() && plot.getNumberOfBambou()>0){
                    return movePandaToUnlock(referee.getPandaPosition());
                }
            }
            return fillObjectiveGardener(missingBambous.get(missingBambous.size()-1).getBambouType(), false);
        }
        return putRandomlyAPLot(bambouSections.get(0).getBambouType());
    }

    private MovePandaAction movePandaOnPlantation(PlotType bambouType) {
        for(Plot plot : map.getMapPlots()){
            if(plot.getType()==bambouType && plot.getNumberOfBambou()>0 && plot.getPosition().isDeplacementALine(referee.getPandaPosition()))
            {
                    return new MovePandaAction(this, plot.getPosition());
            }
        }
        return null;
    }
    private void removeBambou(ArrayList<Bambou> bambous, Bambou bambouToRemove){
        for(int i=0; i<bambous.size() ; ++i){
            if(bambous.get(i).getBambouType() == bambouToRemove.getBambouType()){
                bambous.remove(i);
                break;
            }
        }
    }


    PutPlotAction putRandomlyAPLot(PlotType plotType){
        for(Plot plot : map.getMapPlots()){
            List<Position> positions = map.closestAvailableSpace(plot.getPosition());
            if(positions != null && positions.isEmpty()==false ){
                return placePLot(plotType, positions.get(0));
            }
        }
        return null;
    }

    PutPlotAction placePLot(PlotType plotType, Position position){
        List<Plot> plots = referee.pickPlot();
        for(Plot plot : plots){
            if(plot.getType() == plotType){
                plot.setPosition(position);
                return new PutPlotAction(plot);
            }
        }
        Plot plot  = plots.get(0);
        plot.setPosition(position);
        return new PutPlotAction(plot);
    }

    private MovePandaAction movePandaToUnlock(Position pandaPosition){
        List<Plot> potentialPlot = map.getNeighbours(pandaPosition);
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
        return Objects.equals(referee, bot.referee) && Objects.equals(map, bot.map) && Objects.equals(myBambous, bot.myBambous);
    }

    @Override
    public int hashCode() {
        return Objects.hash(referee, map, myBambous);
    }
}
