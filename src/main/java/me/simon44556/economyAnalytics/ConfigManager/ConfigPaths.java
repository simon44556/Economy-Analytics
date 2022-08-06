package me.simon44556.economyAnalytics.ConfigManager;

public enum ConfigPaths {
    DB_NAME("SQL.name", "economyAnalytics"),
    DB_USERNAME("SQL.username", "root"),
    DB_PASSWORD("SQL.password", "root"),
    DB_HOST("SQL.host", "localhost"),
    DB_PORT("SQL.port", "3306");

    final String path;
    final String value;

    ConfigPaths(String path, String value) {
        this.path = path;
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public String getKey() {
        return path;
    }

    @Override
    public String toString() {
        return value;
    }

    public static ConfigPaths[] getValues() {
        ConfigPaths[] returnValues = new ConfigPaths[ConfigPaths.values().length];

        int idx = 0;

        for (ConfigPaths e : ConfigPaths.values()) {
            returnValues[idx++] = e;
        }

        return returnValues;
    }
}
