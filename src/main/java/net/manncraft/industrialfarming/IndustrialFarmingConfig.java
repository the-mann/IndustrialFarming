package net.manncraft.industrialfarming;

import lombok.*;
import lombok.experimental.Accessors;
import org.bukkit.Material;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

@Data @NoArgsConstructor @AllArgsConstructor
public class IndustrialFarmingConfig {
    private List<Material> crops = List.of(Material.WHEAT, Material.POTATOES, Material.CARROTS, Material.BEETROOTS);
    private int maxDistance = 5;
}
