package me.simon44556.economyAnalytics.DataTypes;

import me.simon44556.economyAnalytics.DataTypes.Enums.EventType;

public abstract class EconomyEvent {
    protected long transactionTime;
    protected String playerUUID;
    protected EventType eventType;
    protected double price;

    protected EconomyEvent() {
    }

    public EconomyEvent(long transactionTime, String playerUUID, EventType eventType, double price) {
        this.transactionTime = transactionTime;
        this.playerUUID = playerUUID;
        this.eventType = eventType;
        this.price = price;
    }

    public EconomyEvent(long transactionTime, String playerUUID, int eventType, double price) {
        this(transactionTime, playerUUID, EventType.setValue(eventType), price);
    }

    public long getTransactionTime() {
        return this.transactionTime;
    }

    public String getPlayerUUID() {
        return this.playerUUID;
    }

    public EventType getEventType() {
        return this.eventType;
    }

    public int getEventTypeAsInt() {
        return this.eventType.getId();
    }

    public double getPrice() {
        return this.price;
    }
}
