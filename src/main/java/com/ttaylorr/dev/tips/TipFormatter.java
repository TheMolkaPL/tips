package com.ttaylorr.dev.tips;

import org.bukkit.ChatColor;

public class TipFormatter {

    private String rawFormat;
    private String rawPrefix;
    private String rawSuffix;

    public TipFormatter(String prefix, String format, String suffix) {
        this.rawPrefix = prefix;
        this.rawFormat = format;
        this.rawSuffix = suffix;
    }

    public String format(String message) {
        StringBuilder builder = new StringBuilder();

        if (this.rawFormat != null) {
            builder.append(ChatColor.translateAlternateColorCodes('`', this.rawPrefix));
        }

        builder.append(this.rawFormat.replace("%message", message));

        if (this.rawSuffix != null) {
            builder.append(ChatColor.translateAlternateColorCodes('`', this.rawSuffix));
        }

        return builder.toString();
    }

    public String getRawPrefix() {
        return this.rawPrefix;
    }

    public String getRawFormat() {
        return this.rawFormat;
    }

    public String getRawSuffix() {
        return this.rawSuffix;
    }

}
