package fr.cotedazur.univ.polytech.startingpoint;

public class Panda {

    private Position position;

    public Panda(){

        position = new Position(0,0);
    }

    public Position getPosition() {

        return position;
    }

    public void setPosition(Position position) {
        this.position = position;

        }

   /* public boolean isBambouAround(Plot plot){
        //on recupere la position
        for(Bambou bambou : plot.getBambou()){
            if(bambou!=null){
                return true;
            }
            else return false;
        }

        return false;
    }
     */





    //regarder si il y a un plot
    public boolean isPlot(Map map){
        //chercher avec la position un plot
        Plot plot= null;
        for(Plot positions  : map.getMap()){
            plot = positions;
            if(positions.getPosition().equals(position)){
                return true;
            }
        }
        return false;
    }


}
