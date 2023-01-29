package fr.cotedazur.univ.polytech.startingpoint;

import fr.cotedazur.univ.polytech.startingpoint.Game.Game;

public class CrashOrLoopTest {
    public static void main(String[] args) {
        while (true){
            Game game = new Game(false);
            game.start();
        }
    }
}
