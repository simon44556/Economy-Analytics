package me.simon44556.economyAnalytics.DataTypes;

import me.simon44556.economyAnalytics.DataTypes.Enums.ShopEventType;

public class ShopEvent {
    private int transactionTime;
    private String playerUUID;
    private ShopEventType eventType;
    private double amount;
    private String item;

    public ShopEvent() {

    }

    public ShopEvent(int transactionTime, String playerUUID, ShopEventType eventType, double amount, String item) {
        this.transactionTime = transactionTime;
        this.playerUUID = playerUUID;
        this.eventType = eventType;
        this.amount = amount;
        this.item = item;
    }

    public ShopEvent(int transactionTime, String playerUUID, int eventType, double amount, String item) {
        new ShopEvent(transactionTime, playerUUID, ShopEventType.setValue(eventType), amount, item);
    }

    public int getTransactionTime() {
        return this.transactionTime;
    }

    public String getPlayerUUID() {
        return this.playerUUID;
    }

    public ShopEventType getEventType() {
        return this.eventType;
    }

    public int getEventTypeAsInt() {
        return this.eventType.getId();
    }

    public double getAmount() {
        return this.amount;
    }

    public String getItem() {
        return this.item;
    }
}
