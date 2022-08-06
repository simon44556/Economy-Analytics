package me.simon44556.economyAnalytics.SQLHandler;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface PreparedStatementSetter<R> {
    R setValues(PreparedStatement ps) throws SQLException;
}