package net.manncraft.industrialfarming;

import lombok.Data;
import lombok.val;
import net.manncraft.utils.VaultContextBaseInterface;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.Ageable;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.scheduler.BukkitTask;
import org.bukkit.util.BlockIterator;
import org.bukkit.util.BlockVector;
import org.bukkit.util.Vector;

import java.util.Objects;
import java.util.function.Consumer;

@Data
public class IFListener implements Listener {
    private final VaultContextBaseInterface<IndustrialFarmingConfig> context;
    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        //Broke farmland
        event.setCancelled(event.getAction() == Action.PHYSICAL && event.getPlayer().isSneaking()
                && Objects.requireNonNull(event.getClickedBlock()).getType() == Material.FARMLAND);
    }

    @EventHandler
    public void onBreakFarmland(BlockBreakEvent event) {
        if (context.config().getCrops().contains(event.getBlock().getType())) {
            SpiralIterator bi = new SpiralIterator(context.config().getMaxDistance(),
                                                   BlockFace.EAST.getDirection(),
                                                   event.getBlock().getLocation());
                    Bukkit.getScheduler().runTaskTimer(context.plugin(), t -> {
                        if (!bi.hasNext()) {
                            t.cancel();
                        }else {
                            context.log().info(bi.toString());
                            val b = bi.next();
                            if (context.config().getCrops().contains(b.getType())
                                    && ((Ageable) b.getBlockData()).getAge() == ((Ageable) b.getBlockData()).getMaximumAge()
                            ){
                                b.breakNaturally();
                            }
                        }
                    }, 2L, 2L);
                }
        }
}
