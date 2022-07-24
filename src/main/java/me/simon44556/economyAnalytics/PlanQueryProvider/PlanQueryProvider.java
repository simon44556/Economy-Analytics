package me.simon44556.economyAnalytics.PlanQueryProvider;

import java.sql.PreparedStatement;
import java.util.UUID;

import com.djrapitops.plan.query.QueryService;

import me.simon44556.economyAnalytics.DataTypes.ShopEvent;

public abstract class PlanQueryProvider {
    protected String dbTableName;
    protected QueryService _service;

    protected PlanQueryProvider(QueryService service) {
        this._service = service;

        createTable();
        _service.subscribeDataClearEvent(this::recreateTable);
        _service.subscribeToPlayerRemoveEvent(this::removePlayer);
    }

    public abstract void createTable();

    private void dropTable() {
        _service.execute("DROP TABLE IF EXISTS " + dbTableName + "", PreparedStatement::execute);
    }

    private void recreateTable() {
        dropTable();
        createTable();
    }

    private void removePlayer(UUID playerUUID) {
        _service.execute(
                "DELETE FROM " + dbTableName + " WHERE uuid=?",
                statement -> {
                    statement.setString(1, playerUUID.toString());
                    statement.execute();
                });
    }

    public abstract void storeTransaction(ShopEvent dataStore);

    public abstract ShopEvent getSingleTransactionForTime(int time, String uuid);

    public abstract double getTransactionsForItemOnTime(int time, String item);
}
