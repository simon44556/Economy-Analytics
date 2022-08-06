package me.simon44556.economyAnalytics.DataHandler;

import java.sql.ResultSet;
import java.sql.SQLException;

import me.simon44556.economyAnalytics.DataTypes.BalanceEvent;
import me.simon44556.economyAnalytics.DataTypes.ShopEvent;
import me.simon44556.economyAnalytics.DatabaseManager.DatabaseManager;

public class DataHandler {
    private final String SHOP_TABLE = "shop_track";
    private final String BALANCE_TABLE = "balance_track";

    private final DatabaseManager _databaseManager;

    public DataHandler() throws SQLException {
        this._databaseManager = new DatabaseManager();
        this.creteTable();
    }

    public void creteTable() {
        String sql = "CREATE TABLE IF NOT EXISTS " + this.SHOP_TABLE + " (" +
                "ID INT NOT NULL AUTO_INCREMENT," +
                "transactionTime BIGINT NOT NULL," +
                "playerUUID VARCHAR(36) NOT NULL," +
                "eventType INT NOT NULL," +
                "amount INT," +
                "price DOUBLE," +
                "item VARCHAR(40)," +
                "PRIMARY KEY (ID)" +
                ')';
        this._databaseManager.execute(sql);

        sql = "CREATE TABLE IF NOT EXISTS " + this.BALANCE_TABLE + " (" +
                "ID INT NOT NULL AUTO_INCREMENT," +
                "transactionTime BIGINT NOT NULL," +
                "playerUUID VARCHAR(36) NOT NULL," +
                "eventType INT NOT NULL," +
                "price DOUBLE," +
                "PRIMARY KEY (ID)" +
                ')';
        this._databaseManager.execute(sql);
    }

    public void storeTransaction(ShopEvent dataStore) {
        String insert = "INSERT INTO " + this.SHOP_TABLE
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

    public void storeTransaction(BalanceEvent dataStore) {
        String insert = "INSERT INTO " + this.BALANCE_TABLE
                + " ( transactionTime, playerUUID, eventType, price) VALUES(?, ?, ?, ?)";

        try {
            _databaseManager.execute(insert, statement -> {
                statement.setLong(1, dataStore.getTransactionTime());
                statement.setString(2, dataStore.getPlayerUUID());
                statement.setInt(3, dataStore.getEventTypeAsInt());
                statement.setDouble(4, dataStore.getPrice());
                return statement.execute();
            });
        } catch (IllegalStateException e) {
            Thread.currentThread().interrupt();
        }
    }

    public ShopEvent getSingleTransactionForTime(int time, String uuid, String table) {
        String select = "SELECT * FROM " + this.SHOP_TABLE + " WHERE time=? AND uuid=?";

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

    public double getTransactionsForItemOnTime(int time, String item, String table) {
        final String select = "SELECT SUM(amount) AS sumOfAmounts FROM " + this.SHOP_TABLE + " WHERE time=? AND item=?";

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
