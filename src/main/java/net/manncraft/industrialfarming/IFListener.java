package net.manncraft.industrialfarming;

import lombok.Data;
import net.manncraft.utils.VaultContextBaseInterface;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.Objects;

@Data
public class IFListener implements Listener {
    private final VaultContextBaseInterface context;
    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        //Broke farmland
        if (event.getAction() == Action.PHYSICAL && event.getPlayer().isSneaking()
                && Objects.requireNonNull(event.getClickedBlock()).getType() == Material.FARMLAND) {
            event.getPlayer().setHealth(0);
        }
    }
}
