package fr.cotedazur.univ.polytech.startingpoint;

import fr.cotedazur.univ.polytech.startingpoint.Game.DeckSignal;

import java.util.ArrayList;
import java.util.Collections;

public class Deck<T> {

    private ArrayList<T> _deck;
    DeckSignal _deckSignal;

    public Deck(DeckSignal deckSignal){
        this._deckSignal = deckSignal;
        _deck = new ArrayList<T>();
    }

    public void shuffle(){
        Collections.shuffle(_deck);
    }

    public void addCard(T card){
        _deck.add(card);
    }

    public T getNextCard(){
        T card = _deck.get(0);
        _deck.remove(0);
        if(_deck.size()<=1){
            _deckSignal.emptyDeck();
        }
        return card;
    }

    @Override
    public String toString() {
        return "Deck{" +
                "_deck=" + _deck +
                '}';
    }
}
