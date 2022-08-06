package me.simon44556.economyAnalytics.EconomyListener;

import me.simon44556.economyAnalytics.DataTypes.Enums.EventType;

public class BalanceTracker {

    /*
     * CMI Actions
     * setBalance, Withdraw, Deposit
     */
    public EventType matchEventType(String action) {
        switch (action) {
            case "setBalance":
                return EventType.SET_BALANCE;
            case "Withdraw":
                return EventType.WITHDRAW;
            case "Deposit":
                return EventType.DEPOSIT;
            default:
                return EventType.OTHER;
        }
    }
}
