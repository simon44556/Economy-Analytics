package me.simon44556.economyAnalytics;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.bukkit.plugin.java.JavaPlugin;

import me.simon44556.economyAnalytics.ConfigManager.ConfigAccessor;
import me.simon44556.economyAnalytics.EconomyListener.CMIListener;
import me.simon44556.economyAnalytics.EconomyListener.EconomyListener;
import me.simon44556.economyAnalytics.EconomyWrapper.EconomyRegister;
import me.simon44556.economyAnalytics.ShopListener.ShopListener;

public class EconomyAnalytics extends JavaPlugin {
    ConfigAccessor configHandler;
    EconomyRegister vaultEventProvider;
    Logger logger;

    @Override
    public void onEnable() {
        this.logger = getLogger();
        registerVaultEvents();
        configHandler = new ConfigAccessor(this);
        registerListeners();
    }

    @Override
    public void onDisable() {
        configHandler = null;
    }

    public void registerListeners() {
        getServer().getPluginManager().registerEvents(new ShopListener(this), this);
        registerEconomyListener();
    }

    public void registerEconomyListener() {
        boolean isCmiAvailable = this.getServer().getPluginManager().getPlugin("CMI") != null;

        if (isCmiAvailable) {
            // register cmi
            getServer().getPluginManager().registerEvents(new CMIListener(this), this);
        } else {
            logger.log(Level.SEVERE, "CMI not provided -> Trying vault");

            if (vaultEventProvider.areEventsAvailable()) {
                // Register vault
                getServer().getPluginManager().registerEvents(new EconomyListener(this), this);
            } else {
                logger.log(Level.SEVERE, "ECONOMY NOT AVAILABLE");
            }
        }
    }

    public ConfigAccessor getConfiguration() {
        return configHandler;
    }

    public void registerVaultEvents() {
        this.vaultEventProvider = new EconomyRegister(this);
    }

}
