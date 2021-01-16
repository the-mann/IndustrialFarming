package net.manncraft.industrialfarming;

import net.manncraft.utils.VaultContextBase;
import org.bukkit.plugin.java.JavaPlugin;

public final class IndustrialFarming extends JavaPlugin {
    VaultContextBase<?> context;
    @Override
    public void onEnable() {
        // Plugin startup logic
        context = new IFContext(this);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        context.shutdown();
    }
}
