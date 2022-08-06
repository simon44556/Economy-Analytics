package me.simon44556.economyAnalytics;

import org.bukkit.plugin.java.JavaPlugin;

import me.simon44556.economyAnalytics.HikariHandler.Hikari;
import me.simon44556.economyAnalytics.ShopListener.ShopListener;

public class EconomyAnalytics extends JavaPlugin {

    Hikari sqlConnectionsHandler;

    @Override
    public void onEnable() {
        saveResource("sql.properties", false);

        sqlConnectionsHandler = new Hikari();

        this.getServer().getPluginManager().registerEvents(new ShopListener(this), this);

    }

    @Override
    public void onDisable() {

    }

    public Hikari getSqlConnection() {
        return sqlConnectionsHandler;
    }
}
