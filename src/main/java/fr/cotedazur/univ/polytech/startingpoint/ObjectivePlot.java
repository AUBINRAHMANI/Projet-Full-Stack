package fr.cotedazur.univ.polytech.startingpoint;

public class ObjectivePlot extends Objective{
    private DispositionPlots _disposition;
    public ObjectivePlot(int point, ObjectiveType objective, DispositionPlots disposition) {
        super(point, objective);
        _disposition = disposition;
    }

    public DispositionPlots getDisposition(){
        return _disposition;
    }


}
