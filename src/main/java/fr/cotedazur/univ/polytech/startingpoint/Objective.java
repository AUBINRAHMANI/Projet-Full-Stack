package fr.cotedazur.univ.polytech.startingpoint;

public class Objective {
    private ObjectiveType _obj;
    private Map _map;
    private int point;
    public Objective(int point, ObjectiveType obj){
        _obj = obj.PLOT;

    }
    public int getPoint(){
        return point;
    }

    public ObjectiveType getType(){
        return _obj;
    }

    public void updateMap(Map map) {
        _map = map;
    }
}
