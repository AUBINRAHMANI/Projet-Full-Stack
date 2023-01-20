package fr.cotedazur.univ.polytech.startingpoint;

public class Irrigation {

    private Position position;

    public Irrigation(){
        this.position = null;

    }

    public boolean isLink(Irrigation irrigation){
        return false;
    }

    public Position getPosition(){
        return this.position;
    }

    public void setPosition(Position position){
        this.position = position;
    }


}
