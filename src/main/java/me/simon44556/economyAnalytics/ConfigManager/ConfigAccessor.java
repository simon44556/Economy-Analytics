package me.simon44556.economyAnalytics.ConfigManager;

import me.simon44556.economyAnalytics.EconomyAnalytics;

public class ConfigAccessor {
    static ConfigManager manager;

    public ConfigAccessor(EconomyAnalytics plugin) {
        manager = new ConfigManager(plugin);
        this.initiateConfigValues();
        manager.saveConfig();
        plugin.saveConfig();
    }

    private void initiateConfigValues() {
        for (ConfigPaths e : ConfigPaths.getValues()) {
            manager.addKey(e.getKey(), e.getValue());
        }
    }

    public static String getConfigKey(ConfigPaths e) {
        return manager.getValueForEnum(e);
    }
}
