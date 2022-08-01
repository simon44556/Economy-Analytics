package me.simon44556.economyAnalytics.PlanQueryProvider;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.djrapitops.plan.query.QueryService;

import me.simon44556.economyAnalytics.DataTypes.ShopEvent;

public class EconomyTracker extends PlanQueryProvider {
    // TODO: - Move this to enum
    private static final String DB_NAME = "plan_economy_tracker";

    public EconomyTracker(QueryService service) {
        super(service, DB_NAME);
    }

    @Override
    public void createTable() {
        String dbType = _service.getDBType();
        boolean sqlite = dbType.equalsIgnoreCase("SQLITE");

        String sql = "CREATE TABLE IF NOT EXISTS " + this.dbTableName + " (" +
                "ID INT " + (sqlite ? "PRIMARY KEY" : "NOT NULL AUTO_INCREMENT") + ',' +
                "transactionTime BIGINT NOT NULL," +
                "playerUUID VARCHAR(36) NOT NULL," +
                "eventType INT NOT NULL," +
                "amount INT," +
                "price DOUBLE," +
                "item VARCHAR(40)" +
                (sqlite ? "" : ",PRIMARY KEY (ID)") +
                ')';

        _service.execute(sql, PreparedStatement::execute);
    }

    @Override
    public void storeTransaction(ShopEvent dataStore) {
        String insert = "INSERT INTO " + this.dbTableName
                + " ( transactionTime, playerUUID, eventType, amount, price, item ) VALUES(?, ?, ?, ?, ?, ?)";

        try {
            _service.execute(insert, statement -> {
                statement.setLong(1, dataStore.getTransactionTime());
                statement.setString(2, dataStore.getPlayerUUID());
                statement.setInt(3, dataStore.getEventTypeAsInt());
                statement.setDouble(4, dataStore.getAmount());
                statement.setDouble(5, dataStore.getPrice());
                statement.setString(6, dataStore.getItem());
                statement.execute();
            });
        } catch (IllegalStateException e) {
            Thread.currentThread().interrupt();
        }
    }

    @Override
    public ShopEvent getSingleTransactionForTime(int time, String uuid) {
        String select = "SELECT * FROM " + this.dbTableName + " WHERE time=? AND uuid=?";

        _service.query(select, statement -> {
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
        String select = "SELECT SUM(amount) AS sumOfAmounts FROM " + this.dbTableName + " WHERE time=? AND item=?";

        _service.query(select, statement -> {
            statement.setInt(1, time);
            statement.setString(2, item);
            try (ResultSet set = statement.executeQuery()) {
                if (!set.next()) {
                    return 0;
                }

                return set.getDouble("sumOfAmounts");
            }
        });

        return 0;
    }
}
