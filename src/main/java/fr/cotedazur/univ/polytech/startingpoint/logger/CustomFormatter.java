package fr.cotedazur.univ.polytech.startingpoint.logger;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Formatter;
import java.util.logging.Level;
import java.util.logging.LogRecord;

public class CustomFormatter extends Formatter {


    public static String BLUE = "\u001B[34m";
    public static String RED = "\u001B[31m";
    public static String WHITE = "\u001B[37m";
    public static String BLACK = "\u001B[30m";
    public static String DEFAULT = "\u001B[0m";
    boolean darkMode;

    public CustomFormatter(boolean darkMode){
        this.darkMode = darkMode;
    }

    @Override
    public String format(LogRecord rec) {
        StringBuffer buffer = new StringBuffer(1000);
        if (rec.getLevel().intValue() == Level.CONFIG.intValue()) {
            head(buffer, rec);
            buffer.append(BLUE);
            buffer.append(" ----> ");
            buffer.append(formatMessage(rec));
            buffer.append("\n");
            buffer.append(DEFAULT);

        } else if(darkMode) {
            buffer.append(WHITE);
            buffer.append(formatMessage(rec));
            buffer.append("\n");
            buffer.append(DEFAULT);
        }
        else {
            buffer.append(BLACK);
            buffer.append(formatMessage(rec));
            buffer.append("\n");
            buffer.append(DEFAULT);
        }
        return buffer.toString();
    }

    private void head(StringBuffer buffer, LogRecord rec){
        buffer.append(BLUE);
        buffer.append(rec.getLevel());
        buffer.append(" -- ");
        buffer.append(calcDate(rec.getMillis()));
        buffer.append(DEFAULT);
    }

    private String calcDate(long millisecs) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MMM dd,yyyy HH:mm");
        Date resultdate = new Date(millisecs);
        return dateFormat.format(resultdate);
    }
}
