package net.rouvb.portalcontroller;

import net.rouvb.portalcontroller.command.PortalCommand;
import net.rouvb.portalcontroller.listener.PlayerPortalListener;
import net.rouvb.portalcontroller.util.ConfigManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class PortalController extends JavaPlugin {
    private ConfigManager configManager;

    @Override
    public void onEnable() {
        configManager = new ConfigManager(this);
        getServer().getPluginManager().registerEvents(new PlayerPortalListener(getConfigManager()), this);
        this.getCommand("portal").setExecutor(new PortalCommand(configManager));
        getLogger().info("Portal Controller has been enabled.");
    }

    @Override
    public void onDisable() {
        configManager.saveConfig();
        getLogger().info("Portal Controller has been disabled.");
    }

    public ConfigManager getConfigManager() {
        return configManager;
    }
}
