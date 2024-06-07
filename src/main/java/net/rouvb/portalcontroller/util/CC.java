package net.rouvb.portalcontroller.util;

import org.bukkit.ChatColor;

public class CC {
    public static String colorize(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }
}