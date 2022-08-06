package me.simon44556.economyAnalytics.EconomyEvents.Economy;

import org.bukkit.Bukkit;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import net.milkbowl.vault.economy.EconomyResponse;

public class BankWithdrawEvent extends Event {

    private final String bank;
    private final double amount;
    private final EconomyResponse response;

    public BankWithdrawEvent(String bank, double amount, EconomyResponse response) {
        super(!Bukkit.isPrimaryThread());
        this.bank = bank;
        this.amount = amount;
        this.response = response;
    }

    public String getBankName() {
        return bank;
    }

    public double getAmount() {
        return amount;
    }

    public EconomyResponse getResponse() {
        return response;
    }

    private static final HandlerList HANDLERS = new HandlerList();

    public HandlerList getHandlers() {
        return HANDLERS;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }
}
