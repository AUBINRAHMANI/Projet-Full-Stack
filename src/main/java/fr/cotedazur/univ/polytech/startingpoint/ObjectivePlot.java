package fr.cotedazur.univ.polytech.startingpoint;

public class ObjectivePlot extends Objective{
    private FormationPlots _formation;
    public ObjectivePlot(int point, ObjectiveType obj, FormationPlots formation) {
        super(point, obj);
        _formation = formation;
    }

    public FormationPlots getFormation(){
        return _formation;
    }


}
