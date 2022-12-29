package fr.cotedazur.univ.polytech.startingpoint;

public class Gardener {
    private Position position;

    public Gardener() {
        position = new Position(0, 0);
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public boolean nbOfBambou(){
        return false;
    }

    public void addBambou(Plot p){
        if(p.isIrrigated()){
            p.addBambou();
        }

    }

    public boolean sameSidePlotExist(){
        return false;
    }

    public void addBambouToSameSidePlot(){
    }
}
