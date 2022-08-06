package me.simon44556.economyAnalytics;

import org.bukkit.plugin.java.JavaPlugin;

import me.simon44556.economyAnalytics.ConfigManager.ConfigAccessor;
import me.simon44556.economyAnalytics.ShopListener.ShopListener;

public class EconomyAnalytics extends JavaPlugin {
    ConfigAccessor configHandler;

    @Override
    public void onEnable() {
        configHandler = new ConfigAccessor(this);

        this.getServer().getPluginManager().registerEvents(new ShopListener(this), this);
    }

    @Override
    public void onDisable() {
        configHandler = null;

    }

    public ConfigAccessor getConfiguration() {
        return configHandler;
    }

}
