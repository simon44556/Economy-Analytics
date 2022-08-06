package me.simon44556.economyAnalytics.DataTypes;

import me.simon44556.economyAnalytics.DataTypes.Enums.EventType;

public class BalanceEvent extends EconomyEvent {
    public BalanceEvent(long transactionTime, String playerUUID, EventType eventType, double price) {
        super(transactionTime, playerUUID, eventType, price);
    }

    public BalanceEvent(long transactionTime, String playerUUID, int eventType, double price) {
        super(transactionTime, playerUUID, EventType.setValue(eventType), price);
    }
}
