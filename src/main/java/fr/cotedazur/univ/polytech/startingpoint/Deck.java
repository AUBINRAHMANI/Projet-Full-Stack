package fr.cotedazur.univ.polytech.startingpoint;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class Deck<T> {

    private ArrayList<T> deck_;

    public Deck(){
        deck_ = new ArrayList<T>();
    }

    public void shuffle(){
        Collections.shuffle(deck_);
    }

    public void addCard(T card){
        deck_.add(card);
    }

    public T getNextCard(){
        T card = deck_.get(0);
        deck_.remove(0);
        return card;
    }

    @Override
    public String toString() {
        return "Deck{" +
                "deck_=" + deck_ +
                '}';
    }
}
