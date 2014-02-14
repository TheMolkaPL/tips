package com.ttaylorr.dev.tips;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;

public class TipsPlugin extends JavaPlugin {

    public static long TIP_DELAY = 0l;

    private final TipsManager manager = new TipsManager();
    private BukkitTask broadcastTask;

    @Override
    public void onDisable() {
        this.broadcastTask.cancel();
    }

    @Override
    public void onEnable() {

        this.saveDefaultConfig();
        this.reloadConfig();

        TIP_DELAY = this.getConfig().getLong("tip-delay");

        this.manager.addFormatters(new FormatterFactory(this.getConfig().getConfigurationSection("formatters"), this).getFormatters());
        this.manager.addTips(new TipsFactory(this.getConfig().getConfigurationSection("tips"), this).getTips());

        this.broadcastTask = Bukkit.getScheduler().runTaskTimer(this, new Runnable() {
            @Override
            public void run() {
                manager.broadcastNext();
            }
        }, TIP_DELAY, TIP_DELAY);

    }

    public TipsManager getTipsManager() {
        return this.manager;
    }
}
