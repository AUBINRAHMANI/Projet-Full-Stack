package fr.cotedazur.univ.polytech.startingpoint.logger;


import java.util.logging.ConsoleHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

public interface Loggeable {
    public final static Logger LOGGER = Logger.getLogger( Loggeable.class.getName() );

    static void initLogger(Level level){
        Handler consoleHandler = new ConsoleHandler();
        consoleHandler.setLevel(level);
        consoleHandler.setFormatter(new CustomFormatter(false));
        LOGGER.addHandler(consoleHandler);
        LOGGER.setLevel(level);
        LOGGER.setUseParentHandlers(false);
    }

}
