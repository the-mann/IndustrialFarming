package net.manncraft.utils;

public interface PluginContextBaseInterface<T> {
    void shutdown();
    T config();
}
