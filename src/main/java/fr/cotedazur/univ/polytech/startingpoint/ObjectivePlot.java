package fr.cotedazur.univ.polytech.startingpoint;

public class ObjectivePlot extends Objective{
    private FormationPlots _formation;
    public ObjectivePlot(int point, ObjectiveType objective, FormationPlots formation) {
        super(point, objective);
        _formation = formation;
    }

    public FormationPlots getFormation(){
        return _formation;
    }


}
