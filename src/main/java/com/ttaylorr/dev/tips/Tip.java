package com.ttaylorr.dev.tips;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class Tip {

    private @Nonnull String rawMessage;

    private @Nullable String recievePermission;
    private @Nullable String exemptPermission;

    private @Nonnull TipFormatter formatter;

    public Tip(String recievePermission, String exemptPermission, String rawMessage, TipFormatter formatter) {
        this.recievePermission = recievePermission;
        this.exemptPermission = exemptPermission;

        this.rawMessage = rawMessage;
        this.formatter = formatter;
    }

    public void broadcast() {
        String formatted = this.getFormattedMessage();

        for (Player p : Bukkit.getOnlinePlayers()) {
            if (canPlayerListen(p)) {
                p.sendMessage(formatted);
            }
        }
    }

    public void broadcast(Player... players) {
        String formatted = this.getFormattedMessage();

        for (Player player : players) {
            if (this.canPlayerListen(player)) {
                player.sendMessage(formatted);
            }
        }
    }

    public boolean canPlayerListen(Player p) {
        if (this.recievePermission != null && this.exemptPermission != null) {
            return p.hasPermission(recievePermission) && !p.hasPermission(exemptPermission);
        } else if (recievePermission != null) {
            return p.hasPermission(this.recievePermission);
        } else if (exemptPermission != null) {
            return !p.hasPermission(this.exemptPermission);
        } else {
            return true;
        }
    }

    public @Nonnull String getRawMessage() {
        return this.rawMessage;
    }

    public @Nonnull String getFormattedMessage() {
        return this.formatter.format(this.rawMessage);
    }

    public @Nullable String getExemptPermission() {
        return exemptPermission;
    }

    public @Nullable String getRecievePermission() {
        return recievePermission;
    }

}
