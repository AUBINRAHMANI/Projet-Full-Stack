package fr.cotedazur.univ.polytech.startingpoint;

public class Objective {
    private ObjectiveType _objectiveType;
    private int _points;
    public Objective(int points, ObjectiveType objectiveType){
        _points = points;
        _objectiveType = objectiveType;
    }
    public int getPoint(){
        return _points;
    }

    public ObjectiveType getType(){
        return _objectiveType;
    }
}
