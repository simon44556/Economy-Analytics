package me.simon44556.economyAnalytics.DataTypes.Enums;

public enum ShopEventType {
    BUY(0),
    SELL(1),
    SELL_ALL(2),
    OTHER(50);

    private int id;

    ShopEventType(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    public static ShopEventType setValue(int id) {
        switch (id) {
            case 0:
                return ShopEventType.BUY;
            case 1:
                return ShopEventType.SELL;
            default:
                return ShopEventType.OTHER;
        }
    }
}