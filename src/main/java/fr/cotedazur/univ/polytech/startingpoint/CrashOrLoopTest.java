package fr.cotedazur.univ.polytech.startingpoint;

import fr.cotedazur.univ.polytech.startingpoint.game.Game;

public class CrashOrLoopTest {
    public static void main(String[] args) {
        while (true){
            Game game = new Game(true);
            game.start();
        }
    }
}
