package uk.co.piratescode.tlmd.filters;

import uk.co.piratescode.tlmd.TlmdFilter;

import java.util.Map;
import java.util.logging.Filter;
import java.util.logging.LogRecord;


public class PartialFilter extends TlmdFilter implements Filter {
    private boolean caseSensitive;

    @Override
    public boolean initialize(String expression, Map<?, ?> params) {
        if (!super.initialize(expression, params)) return false;
        if (this.getParams().containsKey("case-sensitive")) {
            caseSensitive = Boolean.parseBoolean(this.getParams().get("case-sensitive").toString());
        }
        return true;
    }

    @Override
    public boolean isLoggable(LogRecord record) {
        String m = record.getMessage().trim();
        if (this.caseSensitive) {
            if (m.contains(this.getExpression())) {
                this.write(record);
                this.getParent().incrementFilteredLogCount();
                return false;
            }
        } else {
            if (m.toUpperCase().contains(this.getExpression().toUpperCase())) {
                this.write(record);
                this.getParent().incrementFilteredLogCount();
                return false;
            }
        }
        return true;
    }


}
