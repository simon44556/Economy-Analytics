package me.simon44556.economyAnalytics;

import org.bukkit.plugin.java.JavaPlugin;

import me.simon44556.economyAnalytics.ShopListener.ShopListener;

public class EconomyAnalytics extends JavaPlugin {


    @Override
    public void onEnable() {
        saveResource("sql.properties", false);


        this.getServer().getPluginManager().registerEvents(new ShopListener(this), this);

    }

    @Override
    public void onDisable() {

    }

}
