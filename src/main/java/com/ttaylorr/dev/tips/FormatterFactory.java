package com.ttaylorr.dev.tips;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.MemorySection;

import java.util.HashMap;
import java.util.Map;

public class FormatterFactory {

    private ConfigurationSection config;
    private TipsPlugin plugin;

    private Map<String, TipFormatter> formatters;
    
    public FormatterFactory(ConfigurationSection config, TipsPlugin tipsPlugin) {
        this.config = config;
        this.plugin = tipsPlugin;
        
        this.formatters = new HashMap<>();
    
        this.parse(this.config);
    }

    private void parse(ConfigurationSection config) {
        for (Map.Entry<String, Object> entry : config.getValues(false).entrySet()) {
            MemorySection section = (MemorySection) entry.getValue();

            String prefix = section.getString("prefix");
            String format = section.getString("format");
            String suffix = section.getString("suffix");

            TipFormatter formatter = new TipFormatter(prefix, format, suffix);

            this.formatters.put(entry.getKey(), formatter);
        }
    }

    public Map<String, TipFormatter> getFormatters() {
        return this.formatters;
    }
}
