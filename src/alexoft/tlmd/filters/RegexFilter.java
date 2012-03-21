package alexoft.tlmd.filters;

import java.util.Map;
import java.util.logging.Filter;
import java.util.logging.LogRecord;

import alexoft.tlmd.TlmdFilter;

public class RegexFilter extends TlmdFilter implements Filter {
	private String replace = "";

	@Override
	public boolean initialize(String expression, Map<?, ?> params) {
		if (!super.initialize(expression, params))
			return false;
		if (this.getParams().containsKey("replace")) {
			replace = this.getParams().get("replace").toString();
		}
		return true;
	}

	@Override
	public boolean isLoggable(LogRecord record) {
		String m = record.getMessage();
		if (m.matches(this.getExpression())) {
			if ("".equals(replace)) {
				record.setMessage(m.replaceAll(this.getExpression(), replace));
			}
			this.write(record);
			return false;
		}
		return true;
	}

}
