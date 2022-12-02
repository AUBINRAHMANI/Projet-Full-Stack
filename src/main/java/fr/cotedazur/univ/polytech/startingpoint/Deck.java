package fr.cotedazur.univ.polytech.startingpoint;

import java.util.ArrayList;

public class Deck<T> {

    private ArrayList<T> deck_;

    public Deck(){
        deck_ = new ArrayList<T>();
    }

    public void shuffle(){

    }

    public void addCard(T card){
        deck_.add(card);
    }

    public T getNextCard(){
        T card = deck_.get(0);
        deck_.remove(0);
        return card;

    }
}
