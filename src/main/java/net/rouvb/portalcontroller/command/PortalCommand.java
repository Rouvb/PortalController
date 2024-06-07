package net.rouvb.portalcontroller.command;

import net.rouvb.portalcontroller.PortalController;
import net.rouvb.portalcontroller.util.CC;
import net.rouvb.portalcontroller.util.ConfigKey;
import net.rouvb.portalcontroller.util.ConfigManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.List;

public class PortalCommand implements CommandExecutor {
    private ConfigManager configManager;

    public PortalCommand(ConfigManager configManager) {
        this.configManager = configManager;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender.hasPermission("portalcontroller.admin")) {
            if (args.length == 0) {
                sendMessages(sender, ConfigKey.HELP_MESSAGE);
                return true;
            }

            switch (args[0].toLowerCase()) {
                case "reload":
                    configManager.reloadConfig();
                    sendMessage(sender, ConfigKey.RELOAD);
                    return true;
                case "nether":
                    if (args.length == 2) {
                        toggleConfig(sender, "nether.enable", args[1]);
                    } else {
                        sender.sendMessage(CC.colorize("&f/portal nether true/false"));
                    }
                    return true;
                case "end":
                    if (args.length == 2) {
                        toggleConfig(sender, "end.enable", args[1]);
                    } else {
                        sender.sendMessage(CC.colorize("&f/portal end true/false"));
                    }
                    return true;
                default:
                    sendMessages(sender, ConfigKey.HELP_MESSAGE);
                    return true;
            }
        } else {
            sendMessage(sender, ConfigKey.NO_PERMISSION);
            return true;
        }
    }

    private void toggleConfig(CommandSender sender, String key, String value) {
        if ("true".equalsIgnoreCase(value) || "false".equalsIgnoreCase(value)) {
            boolean booleanValue = Boolean.parseBoolean(value);
            configManager.getConfig().set(key, booleanValue);
            configManager.saveConfig();
            String portalType = key;
            if (portalType == "nether.enable") { portalType = "Nether"; }
            else { portalType = "End"; }
            sender.sendMessage(CC.colorize("&b" + portalType + (booleanValue ? " &fis now enabled." : " &fis now disabled.")));
        } else {
            sender.sendMessage(CC.colorize("&cIncorrect Value."));
        }
    }

    private void sendMessage(CommandSender sender, ConfigKey key) {
        String message = configManager.getString(key);
        if (message != null) {
            sender.sendMessage(CC.colorize(message));
        }
    }

    private void sendMessages(CommandSender sender, ConfigKey key) {
        List<String> messages = configManager.getStringList(key);
        if (messages != null) {
            for (String message : messages) {
                sender.sendMessage(CC.colorize(message));
            }
        }
    }
}
