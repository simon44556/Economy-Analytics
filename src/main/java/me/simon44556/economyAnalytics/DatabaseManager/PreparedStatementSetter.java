package me.simon44556.economyAnalytics.DatabaseManager;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface PreparedStatementSetter<R> {
    R setValues(PreparedStatement ps) throws SQLException;
}