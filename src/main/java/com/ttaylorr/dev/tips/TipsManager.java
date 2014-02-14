package com.ttaylorr.dev.tips;

import com.google.common.collect.ImmutableList;

import javax.annotation.Nonnull;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class TipsManager {

    private @Nonnull LinkedList<Tip> tips;
    private @Nonnull Map<String, TipFormatter> formatters;

    private int lastBroadcasted = -1;

    public TipsManager() {
        this(new LinkedList<Tip>());
    }

    public TipsManager(LinkedList<Tip> tips) {
        this.tips = tips;
        this.formatters = new HashMap<>();
    }

    public void addTip(Tip tip) {
        this.tips.add(tip);
    }

    public void addTips(List<Tip> tips) {
        this.tips.addAll(tips);
    }

    public void addFormatter(String key, TipFormatter formatter) {
        if (this.formatters.containsKey(key)) {
            throw new IllegalArgumentException("a formatter already exists with that name");
        }

        this.formatters.put(key, formatter);
    }

    public void addFormatters(Map<String, TipFormatter> formatters) {
        this.formatters.putAll(formatters);
    }

    public ImmutableList<Tip> getAllTips() {
        return ImmutableList.copyOf(this.tips);
    }

    public Tip getLastBroadcasted() {
        return this.tips.get(lastBroadcasted);
    }

    public TipFormatter getFormatter(String key) {
        return this.formatters.get(key);
    }

    public Tip broadcastNext() {
        this.lastBroadcasted = (this.lastBroadcasted + 1) % this.tips.size();

        this.tips.get(lastBroadcasted).broadcast();
        return this.getLastBroadcasted();
    }

}
