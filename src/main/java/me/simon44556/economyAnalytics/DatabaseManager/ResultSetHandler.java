package me.simon44556.economyAnalytics.DatabaseManager;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface ResultSetHandler {
    void process(ResultSet resultSet) throws SQLException;
}
