package alexoft.tlmd.filters;

import java.util.Map;
import java.util.logging.Filter;
import java.util.logging.LogRecord;

import org.bukkit.ChatColor;

import alexoft.tlmd.TlmdFilter;

public class AlterateFilter extends TlmdFilter implements Filter {
	public enum AlterateType {
		regex, level
	}

	// private AlterateType type = AlterateType.regex;
	private String replace = "";

	@Override
	public boolean initialize(String expression, Map<?, ?> params) {
		if (!super.initialize(expression, params))
			return false;
		if (!this.getParams().containsKey("replace"))
			return false;

		replace = this.getParams().get("replace").toString();
		return true;
	}

	@Override
	public boolean isLoggable(LogRecord record) {
		String m = record.getMessage();
		if (m.matches(this.getExpression())) {
			this.write(record);
			String msg = ChatColor.translateAlternateColorCodes(COLOR_CODE,
					m.replaceAll(this.getExpression(), replace));
			if (!this.getParent().getParent().use_color_codes) {
				msg = ChatColor.stripColor(msg);
			}
			record.setMessage(msg);
			this.getParent().incrementAlteredLogCount();
		}
		return true;
	}

}
