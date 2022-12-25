package fr.cotedazur.univ.polytech.startingpoint;

public class Gardener {
    private Position position;
    private Map map;

    public Gardener() {
        position = new Position(0, 0);
    }

    public void move(Position position) {
        this.position = position;
    }

    public boolean isIrrigated(){
    }

    public boolean bambouIsPresent(){
    }

    public void addBambou(){
    }

    public boolean sameSidePlotExist(){
        return false;
    }

    public void addBambouToSameSidePlot(){
    }
}
