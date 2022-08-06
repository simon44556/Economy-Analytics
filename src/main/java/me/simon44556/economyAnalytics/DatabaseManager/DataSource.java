package me.simon44556.economyAnalytics.DatabaseManager;

import java.sql.Connection;
import java.sql.SQLException;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import me.simon44556.economyAnalytics.ConfigManager.ConfigAccessor;
import me.simon44556.economyAnalytics.ConfigManager.ConfigPaths;

public class DataSource {
    private static HikariConfig config = new HikariConfig();
    private static HikariDataSource ds;

    static {
        String jdbcUrl = "jdbc:mysql://" + ConfigAccessor.getConfigKey(ConfigPaths.DB_HOST) + ":"
                + ConfigAccessor.getConfigKey(ConfigPaths.DB_PORT) + "/"
                + ConfigAccessor.getConfigKey(ConfigPaths.DB_NAME);
        config.setJdbcUrl(jdbcUrl);
        config.setUsername(ConfigAccessor.getConfigKey(ConfigPaths.DB_USERNAME));
        config.setPassword(ConfigAccessor.getConfigKey(ConfigPaths.DB_PASSWORD));
        config.addDataSourceProperty("cachePrepStmts", "true");
        config.addDataSourceProperty("prepStmtCacheSize", "250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
        ds = new HikariDataSource(config);
    }

    private DataSource() {
    }

    public static Connection getConnection() throws SQLException {
        return ds.getConnection();
    }
}
