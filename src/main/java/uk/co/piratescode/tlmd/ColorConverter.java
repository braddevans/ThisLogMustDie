package uk.co.piratescode.tlmd;

import org.bukkit.ChatColor;
import org.fusesource.jansi.Ansi;
import org.fusesource.jansi.Ansi.Attribute;

import java.util.EnumMap;
import java.util.Map;

/* from here: https://github.com/Bukkit/CraftBukkit/blob/master/src/main/java/org/bukkit/craftbukkit/command/ColouredConsoleSender.java */
public class ColorConverter {

    public static final char ALT_COLOR_CODE = '&';
    private static final Map<ChatColor, String> replacements = new EnumMap<ChatColor, String>(
            ChatColor.class);
    private static final ChatColor[] colors = ChatColor.values();

    public static void initColorConverter() {
        replacements.clear();
        replacements.put(ChatColor.BLACK, Ansi.ansi().fg(Ansi.Color.BLACK)
                .boldOff().toString());
        replacements.put(ChatColor.DARK_BLUE, Ansi.ansi().fg(Ansi.Color.BLUE)
                .boldOff().toString());
        replacements.put(ChatColor.DARK_GREEN, Ansi.ansi().fg(Ansi.Color.GREEN)
                .boldOff().toString());
        replacements.put(ChatColor.DARK_AQUA, Ansi.ansi().fg(Ansi.Color.CYAN)
                .boldOff().toString());
        replacements.put(ChatColor.DARK_RED, Ansi.ansi().fg(Ansi.Color.RED)
                .boldOff().toString());
        replacements.put(ChatColor.DARK_PURPLE,
                Ansi.ansi().fg(Ansi.Color.MAGENTA).boldOff().toString());
        replacements.put(ChatColor.GOLD, Ansi.ansi().fg(Ansi.Color.YELLOW)
                .boldOff().toString());
        replacements.put(ChatColor.GRAY, Ansi.ansi().fg(Ansi.Color.WHITE)
                .boldOff().toString());
        replacements.put(ChatColor.DARK_GRAY, Ansi.ansi().fg(Ansi.Color.BLACK)
                .bold().toString());
        replacements.put(ChatColor.BLUE, Ansi.ansi().fg(Ansi.Color.BLUE).bold()
                .toString());
        replacements.put(ChatColor.GREEN, Ansi.ansi().fg(Ansi.Color.GREEN)
                .bold().toString());
        replacements.put(ChatColor.AQUA, Ansi.ansi().fg(Ansi.Color.CYAN).bold()
                .toString());
        replacements.put(ChatColor.RED, Ansi.ansi().fg(Ansi.Color.RED).bold()
                .toString());
        replacements.put(ChatColor.LIGHT_PURPLE,
                Ansi.ansi().fg(Ansi.Color.MAGENTA).bold().toString());
        replacements.put(ChatColor.YELLOW, Ansi.ansi().fg(Ansi.Color.YELLOW)
                .bold().toString());
        replacements.put(ChatColor.WHITE, Ansi.ansi().fg(Ansi.Color.WHITE)
                .bold().toString());
        replacements.put(ChatColor.MAGIC, Ansi.ansi().a(Attribute.BLINK_SLOW)
                .toString());
        replacements.put(ChatColor.BOLD,
                Ansi.ansi().a(Attribute.UNDERLINE_DOUBLE).toString());
        replacements.put(ChatColor.STRIKETHROUGH,
                Ansi.ansi().a(Attribute.STRIKETHROUGH_ON).toString());
        replacements.put(ChatColor.UNDERLINE, Ansi.ansi()
                .a(Attribute.UNDERLINE).toString());
        replacements.put(ChatColor.ITALIC, Ansi.ansi().a(Attribute.ITALIC)
                .toString());
        replacements.put(ChatColor.RESET,
                Ansi.ansi().a(Attribute.RESET).fg(Ansi.Color.DEFAULT)
                        .toString());
    }

    public static String convertColor(String m) {
        if (replacements.size() == 0)
            initColorConverter();

        m = ChatColor.translateAlternateColorCodes(ALT_COLOR_CODE, m);
        if (Config.use_color_codes) {
            return replaceColorCodes(m) + replacements.get(ChatColor.RESET);
        } else {
            return ChatColor.stripColor(m);
        }
    }

    public static String getColorFromChatColor(ChatColor c) {
        return replacements.get(c);
    }

    public static String stripColorCodes(String m) {
        m = ChatColor.stripColor(m);
        return m;
    }

    public static String stripASCIIColors(String m) {
        for (String s : replacements.values()) {
            m = m.replace(s, "");
        }
        return m;
    }

    public static String StripAllColor(String m) {
        m = stripColorCodes(m);
        m = stripASCIIColors(m);
        return m;
    }

    private static String replaceColorCodes(String m) {
        String result = m;
        for (ChatColor color : colors) {
            if (replacements.containsKey(color)) {
                result = result.replaceAll("(?i)" + color.toString(),
                        replacements.get(color));
            } else {
                result = result.replaceAll("(?i)" + color.toString(), "");
            }
        }
        return result;
    }
}
