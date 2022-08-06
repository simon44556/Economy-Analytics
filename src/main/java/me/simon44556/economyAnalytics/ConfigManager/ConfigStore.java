package me.simon44556.economyAnalytics.ConfigManager;

public class ConfigStore {
    private String CONFIG_PATH;
    private String VALUE;
    private ConfigPaths TYPE;

    public ConfigStore(String path, String value) {
        CONFIG_PATH = path;
        VALUE = value;

        TYPE = findType(path);
    }

    public ConfigStore(String path, String value, ConfigPaths type) {
        CONFIG_PATH = path;
        VALUE = value;

        TYPE = type;
    }

    private ConfigPaths findType(String path) {
        for (ConfigPaths e : ConfigPaths.values()) {
            if (e.getKey().equals(path)) {
                return e;
            }
        }
        return null;
    }

    public String getPath() {
        return CONFIG_PATH;
    }

    public String getValue() {
        return VALUE;
    }

    public ConfigPaths getType() {
        return TYPE;
    }
}