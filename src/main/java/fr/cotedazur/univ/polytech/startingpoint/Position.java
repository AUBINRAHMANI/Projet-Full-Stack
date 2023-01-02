package fr.cotedazur.univ.polytech.startingpoint;

import javax.crypto.spec.PSource;
import java.util.ArrayList;
import java.util.Objects;

public class Position {


    private int x_;
    private int y_;

    public Position(int x, int y){
        x_ = x;
        y_ = y;
    }
    public Position(Position position){
        this(position.getX_(), position.y_);
    }

    public int getX_() {
        return x_;
    }

    public int getY_() {
        return y_;
    }

    public ArrayList<Position> closestPositions(){
        ArrayList<Position> closestPositions = new ArrayList<>();
        for(int i=-1; i<=1; ++i){
            for(int j=-1; j<=1 ; ++j){
                if(!(i==-1 && j==1) && !(i==0 && j==0) && !(i==+1 && j==+1)){
                    closestPositions.add(new Position(x_+i, y_+j));
                }
            }
        }

        return closestPositions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return x_ == position.x_ && y_ == position.y_;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x_, y_);
    }

    @Override
    public String toString() {
        return "Position{" +
                "x_=" + x_ +
                ", y_=" + y_ +
                '}';
    }
}

