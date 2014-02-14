package com.ttaylorr.dev.tips;

import org.bukkit.ChatColor;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.MemorySection;

import java.util.LinkedList;
import java.util.Map;

public class TipsFactory {

    private ConfigurationSection section;
    private final TipsPlugin plugin;

    private LinkedList<Tip> tips;

    public TipsFactory(ConfigurationSection section, final TipsPlugin plugin) {
        this.section = section;
        this.plugin = plugin;

        this.tips = new LinkedList<>();

        this.parse(this.section);
    }

    private void parse(ConfigurationSection config) {
        for (Map.Entry<String, Object> entry : config.getValues(false).entrySet()) {

            MemorySection section = (MemorySection) entry.getValue();

            String message = ChatColor.translateAlternateColorCodes('`', entry.getKey());

            String exemptPermission = section.getString("exempt-permission");
            String receivePermission = section.getString("receive-permission");

            if (exemptPermission != null && receivePermission != null && exemptPermission.equals(receivePermission)) {
                throw new IllegalArgumentException("exempt permission and receive permission are at the same value");
            }

            String formatterName = section.getString("formatter");
            TipFormatter formatter = this.plugin.getTipsManager().getFormatter(formatterName);

            if (formatter == null) {
                throw new IllegalArgumentException("that formatter does not exist");
            }

            this.tips.add(new Tip(receivePermission, exemptPermission, message, formatter));
        }
    }

    public LinkedList<Tip> getTips() {
        return this.tips;
    }
}
