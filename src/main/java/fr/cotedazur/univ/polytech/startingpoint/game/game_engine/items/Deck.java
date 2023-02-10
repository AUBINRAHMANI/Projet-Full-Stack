package fr.cotedazur.univ.polytech.startingpoint.game.game_engine.items;

import fr.cotedazur.univ.polytech.startingpoint.game.DeckSignal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck<T> {

    private final List<T> cards;
    DeckSignal deckSignal;

    public Deck(DeckSignal deckSignal) {
        this.deckSignal = deckSignal;
        this.cards = new ArrayList<>();
    }

    public void shuffle() {
        Collections.shuffle(cards);
    }

    public void addCard(T card) {
        cards.add(card);
    }

    public T getNextCard() {
        T card = cards.get(0);
        cards.remove(0);
        if (cards.size() <= 3) {
            deckSignal.emptyDeck();
        }
        return card;
    }

    @Override
    public String toString() {
        return "Deck{" +
                "_deck=" + cards +
                '}';
    }
}
