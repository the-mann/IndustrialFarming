package net.manncraft.utils;

import org.bukkit.plugin.Plugin;

import java.util.logging.Logger;

public interface PluginContextBaseInterface<T> {
    void shutdown();
    T config();
    Plugin plugin();
    Logger log();
}
