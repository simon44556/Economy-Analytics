package me.simon44556.economyAnalytics.DataTypes.Enums;

public enum EventType {
    BUY(0),
    SELL(1),
    SELL_ALL(2),
    WITHDRAW(3),
    DEPOSIT(4),
    SET_BALANCE(5),
    OTHER(50);

    private int id;

    EventType(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    public static EventType setValue(int id) {
        switch (id) {
            case 0:
                return EventType.BUY;
            case 1:
                return EventType.SELL;
            case 2:
                return EventType.SELL_ALL;
            case 3:
                return EventType.WITHDRAW;
            case 4:
                return EventType.DEPOSIT;
            case 5:
                return EventType.SET_BALANCE;
            default:
                return EventType.OTHER;
        }
    }
}