package me.simon44556.economyAnalytics.DatabaseManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseManager implements AutoCloseable {
    private final Connection connection;

    public DatabaseManager() throws SQLException {
        this.connection = DataSource.getConnection();
    }

    @Override
    public void close() throws SQLException {
        connection.close();
    }

    public PreparedStatement prepareStatement(String sql, PreparedStatementSetter setter)
            throws SQLException {
        PreparedStatement ps = connection.prepareStatement(sql);
        setter.setValues(ps);
        return ps;
    }

    public void executeQuery(String sql, PreparedStatementSetter setter, ResultSetHandler handler) throws SQLException {
        try (PreparedStatement ps = prepareStatement(sql, setter);
                ResultSet rs = ps.executeQuery()) {
            handler.process(rs);
        }
    }

    public boolean execute(String sql) {
        try (PreparedStatement ps = prepareStatement(sql, ps1 -> ps1.execute())) {
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean execute(String sql, PreparedStatementSetter st) {
        try (PreparedStatement ps = prepareStatement(sql, st);) {
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
