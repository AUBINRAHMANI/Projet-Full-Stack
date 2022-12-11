package fr.cotedazur.univ.polytech.startingpoint;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class DeckTest {

    @Test
    public void addCardTest(){
        Deck  deck = new Deck();
        for(int i=0 ; i<10 ; ++i){
            deck.addCard(i);
        }
        for(int i=0 ; i<10 ; ++i){
            assertEquals(i, deck.getNextCard());
        }
    }

    @Test
    public void getNextCardTest(){
        Deck deck = new Deck();
        deck.addCard(5);
        deck.addCard(3);
        assertEquals(5, deck.getNextCard());
    }
}
