package net.rouvb.portalcontroller.util;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class ConfigManager {
    private final JavaPlugin plugin;
    private FileConfiguration config;
    private File configFile;

    public ConfigManager(JavaPlugin plugin) {
        this.plugin = plugin;
        saveDefaultConfig();
    }

    public void reloadConfig() {
        if (configFile == null) {
            configFile = new File(plugin.getDataFolder(), "config.yml");
        }
        config = YamlConfiguration.loadConfiguration(configFile);

        // Load default from the jar
        File defaultConfigFile = new File(plugin.getDataFolder(), "config.yml");
        if (defaultConfigFile.exists()) {
            YamlConfiguration defaultConfig = YamlConfiguration.loadConfiguration(defaultConfigFile);
            config.setDefaults(defaultConfig);
        }
    }

    public FileConfiguration getConfig() {
        if (config == null) {
            reloadConfig();
        }
        return config;
    }

    public void saveConfig() {
        if (config == null || configFile == null) {
            return;
        }
        try {
            getConfig().save(configFile);
        } catch (IOException ex) {
            plugin.getLogger().severe("Could not save config to " + configFile);
        }
    }

    public void saveDefaultConfig() {
        if (configFile == null) {
            configFile = new File(plugin.getDataFolder(), "config.yml");
        }
        if (!configFile.exists()) {
            plugin.saveResource("config.yml", false);
        }
    }

    public Object get(ConfigKey key) {
        return getConfig().get(key.getPath(), key.getDefaultValue());
    }

    public String getString(ConfigKey key) {
        return getConfig().getString(key.getPath(), (String) key.getDefaultValue());
    }

    public boolean getBoolean(ConfigKey key) {
        return getConfig().getBoolean(key.getPath(), (Boolean) key.getDefaultValue());
    }

    public int getInt(ConfigKey key) {
        return getConfig().getInt(key.getPath(), (Integer) key.getDefaultValue());
    }

    public Double getDouble(ConfigKey key) {
        return getConfig().getDouble(key.getPath(), (Double) key.getDefaultValue());
    }
    public Float getFloat(ConfigKey key) {
        return (float) getConfig().getDouble(key.getPath(), (Float) key.getDefaultValue());
    }

    public List<String> getStringList(ConfigKey key) {
        return getConfig().getStringList(key.getPath());
    }

    public void set(ConfigKey key, Object value) {
        getConfig().set(key.getPath(), value);
        saveConfig();
    }
}
