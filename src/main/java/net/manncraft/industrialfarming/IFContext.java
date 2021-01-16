package net.manncraft.industrialfarming;

import lombok.Data;
import lombok.experimental.Accessors;
import net.manncraft.utils.VaultContextBase;
import org.bukkit.plugin.java.JavaPlugin;

@Accessors(fluent = true)
public class IFContext extends VaultContextBase<IndustrialFarmingConfig> {
    public IFContext (JavaPlugin plugin) {
        super(plugin, IndustrialFarmingConfig.class, IndustrialFarmingConfig::new);
        plugin.getServer().getPluginManager().registerEvents(new IFListener(this), plugin);
    }
}
