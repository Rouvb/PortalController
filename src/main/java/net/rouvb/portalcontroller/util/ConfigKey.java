package net.rouvb.portalcontroller.util;

import java.util.Arrays;

public enum ConfigKey {
    NETHER_ENABLE("nether.enable", true),
    NETHER_MESSAGE("nether.message", "&cYou can't use nether portal."),
    END_ENABLE("end.enable", true),
    END_MESSAGE("end.message", "&cYou can't use end portal."),
    RELOAD("reload", "&aPortal Controller has been reloaded."),
    NO_PERMISSION("no-permission", "&cNo Permission."),
    HELP_MESSAGE("help", Arrays.asList(
            "&7&m------------------------------------------",
            "&b&lPortal Controller Commands",
            "&f/portal help",
            "&f/portal reload",
            "&f/portal nether",
            "&f/portal end",
            "&7&m------------------------------------------"
    ));

    private final String path;
    private final Object defaultValue;

    ConfigKey(String path, Object defaultValue) {
        this.path = path;
        this.defaultValue = defaultValue;
    }

    public String getPath() {
        return path;
    }

    public Object getDefaultValue() {
        return defaultValue;
    }
}