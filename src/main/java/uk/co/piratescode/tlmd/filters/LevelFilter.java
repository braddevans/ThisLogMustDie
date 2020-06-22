package uk.co.piratescode.tlmd.filters;

import uk.co.piratescode.tlmd.TlmdFilter;

import java.util.logging.Filter;
import java.util.logging.Level;
import java.util.logging.LogRecord;


public class LevelFilter extends TlmdFilter implements Filter {
    @Override
    public boolean isLoggable(LogRecord record) {
        Level m = record.getLevel();
        if (m.toString().equalsIgnoreCase(this.getExpression())) {
            this.write(record);
            this.getParent().incrementFilteredLogCount();
            return false;
        }
        return true;
    }

}
