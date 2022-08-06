package me.simon44556.economyAnalytics.DataHandler;

import java.sql.ResultSet;
import java.sql.SQLException;

import me.simon44556.economyAnalytics.DataTypes.ShopEvent;
import me.simon44556.economyAnalytics.DatabaseManager.DatabaseManager;

public class DataHandler {
    private final String TABLE_NAME = "plan_economy_tracker";

    private final DatabaseManager _databaseManager;

    public DataHandler() throws SQLException {
        this._databaseManager = new DatabaseManager();
    }

    public void creteTable() {
        final String sql = "CREATE TABLE IF NOT EXISTS " + this.TABLE_NAME + " (" +
                "ID INT NOT NULL AUTO_INCREMENT," +
                "transactionTime BIGINT NOT NULL," +
                "playerUUID VARCHAR(36) NOT NULL," +
                "eventType INT NOT NULL," +
                "amount INT," +
                "price DOUBLE," +
                "item VARCHAR(40)" +
                ",PRIMARY KEY (ID)" +
                ')';
        this._databaseManager.execute(sql);
    }

    public void storeTransaction(ShopEvent dataStore) {
        String insert = "INSERT INTO " + this.TABLE_NAME
                + " ( transactionTime, playerUUID, eventType, amount, price, item ) VALUES(?, ?, ?, ?, ?, ?)";

        try {
            _databaseManager.execute(insert, statement -> {
                statement.setLong(1, dataStore.getTransactionTime());
                statement.setString(2, dataStore.getPlayerUUID());
                statement.setInt(3, dataStore.getEventTypeAsInt());
                statement.setDouble(4, dataStore.getAmount());
                statement.setDouble(5, dataStore.getPrice());
                statement.setString(6, dataStore.getItem());
                return statement.execute();
            });
        } catch (IllegalStateException e) {
            Thread.currentThread().interrupt();
        }
    }

    public ShopEvent getSingleTransactionForTime(int time, String uuid) {
        String select = "SELECT * FROM " + this.TABLE_NAME + " WHERE time=? AND uuid=?";

        _databaseManager.execute(select, statement -> {
            statement.setInt(1, time);
            statement.setString(2, uuid);
            try (ResultSet set = statement.executeQuery()) {
                if (!set.next()) {
                    return null;
                }

                return new ShopEvent(set.getLong("transactionTime"), set.getString("playerUUID"),
                        set.getInt("eventType"), set.getInt("amount"), set.getDouble("price"), set.getString("item"));
            }
        });

        return null;
    }

    public double getTransactionsForItemOnTime(int time, String item) {
        final String select = "SELECT SUM(amount) AS sumOfAmounts FROM " + this.TABLE_NAME + " WHERE time=? AND item=?";

        try {
            _databaseManager.execute(select, ps -> {
                ps.setInt(1, time);
                ps.setString(2, item);
                try (ResultSet set = ps.executeQuery()) {
                    if (!set.next()) {
                        return 0d;
                    } else {
                        return set.getDouble("sumOfAmounts");
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0;
    }

}
