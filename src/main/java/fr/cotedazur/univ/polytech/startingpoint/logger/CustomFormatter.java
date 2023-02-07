package fr.cotedazur.univ.polytech.startingpoint.logger;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Formatter;
import java.util.logging.Level;
import java.util.logging.LogRecord;

public class CustomFormatter extends Formatter {

    @Override
    public String format(LogRecord record) {
        StringBuffer buffer = new StringBuffer(1000);
        if (record.getLevel().intValue() == Level.CONFIG.intValue()) {
            buffer = head(buffer, record);
            buffer.append("\u001B[34m");
            buffer.append(" ----> ");
            buffer.append(formatMessage(record));
            buffer.append("\n");
            buffer.append("\u001B[0m");

        } else {
            buffer.append("\u001B[0m");
            buffer.append(formatMessage(record));
            buffer.append("\n");
            buffer.append("\u001B[0m");

        }
        return buffer.toString();
    }

    private StringBuffer head(StringBuffer buffer, LogRecord record){
        buffer.append("\u001B[34m");
        buffer.append(record.getLevel());
        buffer.append(" -- ");
        buffer.append(calcDate(record.getMillis()));
        buffer.append("\u001B[0m");
        return buffer;
    }

    private String calcDate(long millisecs) {
        SimpleDateFormat date_format = new SimpleDateFormat("MMM dd,yyyy HH:mm");
        Date resultdate = new Date(millisecs);
        return date_format.format(resultdate);
    }
}
