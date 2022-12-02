package fr.cotedazur.univ.polytech.startingpoint;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class DeckTest {

    @Test
    public void shuffleTest(){
        assert false;
    }
    @Test
    public void addCardTest(){
        assert false;
    }

    @Test
    public void getNextCardTest(){
        Deck deck = new Deck();
        deck.addCard(5);
        deck.addCard(3);
        assertEquals(5, deck.getNextCard());
    }
}
