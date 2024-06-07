package net.rouvb.portalcontroller.listener;

import net.rouvb.portalcontroller.util.CC;
import net.rouvb.portalcontroller.util.ConfigKey;
import net.rouvb.portalcontroller.util.ConfigManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerPortalEvent;

public class PlayerPortalListener implements Listener {
    private final ConfigManager configManager;

    public PlayerPortalListener(ConfigManager configManager) {
        this.configManager = configManager;
    }

    @EventHandler
    public void onPortal(PlayerPortalEvent e) {
        switch (e.getCause()) {
            case NETHER_PORTAL:
                if (!configManager.getBoolean(ConfigKey.NETHER_ENABLE)) {
                    e.getPlayer().sendMessage(CC.colorize(configManager.getString(ConfigKey.NETHER_MESSAGE)));
                    e.setCancelled(true);
                }
                break;
            case END_PORTAL:
                if (!configManager.getBoolean(ConfigKey.END_ENABLE)) {
                    e.getPlayer().sendMessage(CC.colorize(configManager.getString(ConfigKey.END_MESSAGE)));
                    e.setCancelled(true);
                }
                break;
            default:
                break;
        }
    }
}
