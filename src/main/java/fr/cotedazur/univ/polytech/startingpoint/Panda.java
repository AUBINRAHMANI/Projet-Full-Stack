package fr.cotedazur.univ.polytech.startingpoint;

public class Panda {

    private Position position;

    public Panda(){

        position = new Position(0,0);
    }

    public Position getPosition() {

        return position;
    }

    public void setPosition(Position position, Map map) {
        Plot plot=null;
        if(this.isPlot(map)){
        this.position = position;
            }

        else{
            System.out.println("Veuillez bouger votre pandat sur une plote existante");

        }
        }

    public boolean isBambouAround(Plot plot){
        //on recupere la position
        for(Bambou bambou : plot.getBambou()){
            if(bambou!=null){
                return true;
            }
            else return false;
        }

        return false;
    }

    public void mangerBambou(Plot plot){
        //plot.getBambou();
        //foreach des bambous et manger le dernier de la liste
        for(Bambou bambou : plot.getBambou()){
            if(bambou!=null){
                plot.getBambou().remove(bambou);
            }
        }
    }


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
