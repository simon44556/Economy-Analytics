package me.simon44556.economyAnalytics.PlanQueryProvider;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.djrapitops.plan.query.QueryService;

import me.simon44556.economyAnalytics.DataTypes.ShopEvent;

public class EconomyTracker extends PlanQueryProvider {
    public EconomyTracker(QueryService service) {
        super(service);
        dbTableName = "plan_economy_tracker";
    }

    @Override
    public void createTable() {
        String dbType = _service.getDBType();
        boolean sqlite = dbType.equalsIgnoreCase("SQLITE");

        String sql = "CREATE TABLE IF NOT EXISTS " + dbTableName + " (" +
                "key int " + (sqlite ? "PRIMARY KEY" : "NOT NULL AUTO_INCREMENT") + ',' +
                "transactionTime int NOT NULL," +
                "playerUUID varchar(36) NOT NULL," +
                "eventType int NOT NULL," +
                "amount double," +
                "item varchar(40)" +
                (sqlite ? "" : ",PRIMARY KEY (key)") +
                ')';

        _service.execute(sql, PreparedStatement::execute);
    }

    @Override
    public void storeTransaction(ShopEvent dataStore) {
        String insert = "INSERT INTO " + dbTableName
                + " ( transactionTime, playerUUID, eventType, amount, item ) VALUES(?, ?, ?, ?, ?)";

        try {
            _service.execute(insert, statement -> {
                statement.setInt(1, dataStore.getTransactionTime());
                statement.setString(2, dataStore.getPlayerUUID());
                statement.setInt(3, dataStore.getEventTypeAsInt());
                statement.setDouble(4, dataStore.getAmount());
                statement.setString(5, dataStore.getItem());
                statement.execute();
            });
        } catch (IllegalStateException e) {
            Thread.currentThread().interrupt();
        }
    }

    @Override
    public ShopEvent getSingleTransactionForTime(int time, String uuid) {
        String select = "SELECT * FROM " + dbTableName + " WHERE time=? AND uuid=?";

        _service.query(select, statement -> {
            statement.setInt(1, time);
            statement.setString(2, uuid);
            try (ResultSet set = statement.executeQuery()) {
                if (!set.next()) {
                    return null;
                }

                return new ShopEvent(set.getInt("transactionTime"), set.getString("playerUUID"),
                        set.getInt("eventType"), set.getDouble("amount"), set.getString("item"));
            }
        });

        return null;
    }

    public double getTransactionsForItemOnTime(int time, String item) {
        String select = "SELECT SUM(amount) AS sumOfAmounts FROM " + dbTableName + " WHERE time=? AND item=?";

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
