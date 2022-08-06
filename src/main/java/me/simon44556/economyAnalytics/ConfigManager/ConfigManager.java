package me.simon44556.economyAnalytics.ConfigManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;

import me.simon44556.economyAnalytics.EconomyAnalytics;

public class ConfigManager extends FileConfiguration {
    private FileConfiguration config;

    List<ConfigStore> configKeys;

    public ConfigManager(EconomyAnalytics plugin) {
        config = plugin.getConfig();
        configKeys = new ArrayList<>();
    }

    public void addKey(ConfigStore key) {
        configKeys.add(generateConfigKey(key.getPath(), key.getValue(), key.getType()));
    }

    public void addKey(String configPath, String value) {
        configKeys.add(generateConfigKey(configPath, value));
    }

    public void addKey(ConfigPaths e) {
        configKeys.add(generateConfigKey(e.getKey(), e.getValue(), e));
    }

    @Override
    public String saveToString() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void loadFromString(String contents) throws InvalidConfigurationException {
        // TODO Auto-generated method stub

    }

    public String getValueForEnum(ConfigPaths e) {
        for (ConfigStore val : configKeys) {
            if (val.getType() == e) {
                return val.getValue();
            }
        }
        return null;
    }

    public String getValueAtPath(String path) {
        for (ConfigStore val : configKeys) {
            if (val.getPath().equals(path)) {
                return val.getValue();
            }
        }
        return null;
    }

    public ConfigStore generateConfigKey(String path, String value) {
        final String val = configGetVal(path);
        if (val == null) {
            return new ConfigStore(path, value);
        }
        return new ConfigStore(path, val);
    }

    public ConfigStore generateConfigKey(String path, String value, ConfigPaths type) {
        final String val = configGetVal(path);
        if (val == null) {
            return new ConfigStore(path, value, type);
        }
        return new ConfigStore(path, val, type);
    }

    public String configGetVal(String path) {
        if (config.getString(path) != null) {
            return config.getString(path);
        }
        return null;
    }

    public void saveConfig() {
        config.addDefaults(toMap(configKeys));
        config.options().copyDefaults(true);

    }

    private Map<String, Object> toMap(List<ConfigStore> values) {
        final Map<String, Object> retunValue = new HashMap<>();

        for (ConfigStore obj : values) {
            retunValue.put(obj.getPath(), obj.getValue());
        }
        return retunValue;
    }

}
