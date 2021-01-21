package net.manncraft.utils;

import org.bukkit.util.Vector;

public class VectorUtils {
    public static void rotateAroundY(double angle, Vector vector) {
        double angleCos = Math.floor(Math.cos(angle));
        double angleSin = Math.floor(Math.sin(angle));

        double x = angleCos * vector.getX() + angleSin * vector.getZ();
        double z = -angleSin * vector.getX() + angleCos * vector.getZ();
        vector.setX(x).setZ(z);
    }
}
