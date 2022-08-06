package me.simon44556.economyAnalytics.DataTypes;

import me.simon44556.economyAnalytics.DataTypes.Enums.EventType;

public class ShopEvent extends EconomyEvent {
    protected int amount;
    protected String item;

    public ShopEvent(long transactionTime, String playerUUID, EventType eventType, int amount, double price,
            String item) {
        super(transactionTime, playerUUID, eventType, price);
        this.amount = amount;
        this.item = item;
    }

    public ShopEvent(long transactionTime, String playerUUID, int eventType, int amount, double price, String item) {
        this(transactionTime, playerUUID, EventType.setValue(eventType), amount, price, item);
    }

    public int getAmount() {
        return this.amount;
    }

    public String getItem() {
        return this.item;
    }
}
