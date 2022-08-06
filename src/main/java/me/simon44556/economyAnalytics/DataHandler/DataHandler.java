package me.simon44556.economyAnalytics.DataHandler;

import me.simon44556.economyAnalytics.HikariHandler.QueryService;

public class DataHandler {
    private final String DB_NAME = "plan_economy_tracker";

    QueryService _service;

    public DataHandler() {
        _service = new QueryService();
    }

    public void creteTable() {
        String sql = "CREATE TABLE IF NOT EXISTS " + this.DB_NAME + " (" +
                "ID INT NOT NULL AUTO_INCREMENT," +
                "transactionTime BIGINT NOT NULL," +
                "playerUUID VARCHAR(36) NOT NULL," +
                "eventType INT NOT NULL," +
                "amount INT," +
                "price DOUBLE," +
                "item VARCHAR(40)" +
                ",PRIMARY KEY (ID)" +
                ')';

        _service.execute(sql);
    }

}
