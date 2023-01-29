package fr.cotedazur.univ.polytech.startingpoint;

public class CrashOrLoopTest {
    public static void main(String[] args) {
        while (true){
            Game game = new Game(false);
            game.start();
        }
    }
}
