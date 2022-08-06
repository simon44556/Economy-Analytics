package me.simon44556.economyAnalytics.HikariHandler;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.bukkit.Bukkit;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class Hikari {
    private HikariConfig dbConfig;
    private DataSource source;

    public Hikari() {
        // TODO: add config handler

        dbConfig = new HikariConfig(
                Bukkit.getPluginManager().getPlugin("EconomyAnalytics").getDataFolder() + "sql.properties");

        source = new HikariDataSource(dbConfig);
    }

    public Connection getConn() throws SQLException {
        return source.getConnection();
    }
}
