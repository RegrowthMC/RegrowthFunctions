package org.lushplugins.regrowthfunctions;

import org.bukkit.plugin.java.JavaPlugin;

public final class RegrowthFunctions extends JavaPlugin {
    private static RegrowthFunctions plugin;

    @Override
    public void onLoad() {
        plugin = this;
    }

    @Override
    public void onEnable() {
        // Enable implementation
    }

    @Override
    public void onDisable() {
        // Disable implementation
    }

    public static RegrowthFunctions getInstance() {
        return plugin;
    }
}
