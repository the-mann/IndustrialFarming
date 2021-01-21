package net.manncraft.industrialfarming;

import lombok.*;
import net.manncraft.utils.VectorUtils;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.util.Vector;

import java.util.Iterator;

@RequiredArgsConstructor @ToString
public class SpiralIterator implements Iterator<Block> {
    @NonNull
    int maxBlocks;
    @NonNull
    Vector currentDirection;
    @NonNull
    Location currentLocation;

    int lineLength = 1;

    @Getter
    int step = 0;

    int stepsLeftUntilTurn = 0;

    boolean turning = false;
    @Override
    public boolean hasNext() {
        return step < maxBlocks;
    }

    @Override
    public Block next() {
        val x = currentLocation.getBlock();
        currentLocation = currentLocation.add(currentDirection);
        if (stepsLeftUntilTurn == 0) {
            VectorUtils.rotateAroundY(Math.toRadians(90), currentDirection);
            if (turning) {
                lineLength++;
            }
            stepsLeftUntilTurn = lineLength;
            turning = !turning;
        }
        step++;
        stepsLeftUntilTurn--;
        return x;
    }
}


