package me.simon44556.economyAnalytics.EconomyEvents;

import org.bukkit.Bukkit;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import net.milkbowl.vault.economy.Economy;

public class EconomyEventsAvailable extends Event {
    private final Economy economy;

    public EconomyEventsAvailable(Economy economy) {
        super(!Bukkit.isPrimaryThread());
        this.economy = economy;
    }

    public Economy getEconomy() {
        return economy;
    }

    private static final HandlerList HANDLERS = new HandlerList();

    public HandlerList getHandlers() {
        return HANDLERS;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }
}
