package me.simon44556.economyAnalytics.DataTypes;

import me.simon44556.economyAnalytics.DataTypes.Enums.ShopEventType;

public class ShopEvent {
    private long transactionTime;
    private String playerUUID;
    private ShopEventType eventType;
    private int amount;
    private double price;
    private String item;

    public ShopEvent(long transactionTime, String playerUUID, ShopEventType eventType, int amount, double price,
            String item) {
        this.transactionTime = transactionTime;
        this.playerUUID = playerUUID;
        this.eventType = eventType;
        this.amount = amount;
        this.price = price;
        this.item = item;
    }

    public ShopEvent(long transactionTime, String playerUUID, int eventType, int amount, double price, String item) {
        new ShopEvent(transactionTime, playerUUID, ShopEventType.setValue(eventType), amount, price, item);
    }

    public long getTransactionTime() {
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

    public int getAmount() {
        return this.amount;
    }

    public String getItem() {
        return this.item;
    }

    public double getPrice() {
        return this.price;
    }
}
