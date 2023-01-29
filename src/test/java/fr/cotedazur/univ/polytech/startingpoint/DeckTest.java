package fr.cotedazur.univ.polytech.startingpoint;

import fr.cotedazur.univ.polytech.startingpoint.Game.Game;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class DeckTest {

    @Mock
    Game mockedGame = mock(Game.class);
    @Test
    public void addCardTest(){
        Deck  deck = new Deck(mockedGame);
        for(int i=0 ; i<10 ; ++i){
            deck.addCard(i);
        }
        for(int i=0 ; i<10 ; ++i){
            assertEquals(i, deck.getNextCard());
        }
    }

    @Test
    public void getNextCardTest(){
        Deck deck = new Deck(mockedGame);
        deck.addCard(5);
        deck.addCard(3);
        assertEquals(5, deck.getNextCard());
    }
}
