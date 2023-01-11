package fr.cotedazur.univ.polytech.startingpoint;

import fr.cotedazur.univ.polytech.startingpoint.Action.*;
import fr.cotedazur.univ.polytech.startingpoint.objective.*;

import java.lang.reflect.Parameter;
import java.util.ArrayList;

public class Bot {

    Game game;
    Map map;

    public Bot(Game game, Map map) {
        this.map = map;
    }

    public Action play(Game game, Map map) {
        this.game = game;
        this.map = map;
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
            } else if (!deplacementValid.isEmpty()) {
                return new MoveGardenerAction(deplacementValid.get(0).getPosition());
            }
        }
        return new PutPlotAction(game.pickPlot());
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
        return null;
    }






    private boolean positionToSet(Map map, Plot plot) {

       // ArrayList<Plot> NewList = new ArrayList<>();
        ArrayList<Position> potentialPositions;
        for(Plot plotMap : map.getMap()){
            potentialPositions = map.closestAvailableSpace(plotMap.getPosition());
            if(potentialPositions.size() >0){
                plot.setPosition(potentialPositions.get(0));
                return true;
            }
        }
        return false;
    }
}
