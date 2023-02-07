package fr.cotedazur.univ.polytech.startingpoint;
import com.beust.jcommander.Parameter;

public class JCommander {
    class Main {
        @Parameter(names={"-Dexec.args=\"--2thousands\""})
        int ;
        @Parameter(names={"--pattern", "-p"})
        int pattern;

        public static void main(String ... argv) {
            Main main = new Main();
            JCommander.newBuilder()
                    .addObject(main)
                    .build()
                    .parse(argv);
            main.run();
        }

        public void run() {
            System.out.printf("%d %d", length, pattern);
        }
    }
}
