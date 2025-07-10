package melonystudios.variants.util;

import net.minecraft.block.Block;
import net.minecraft.world.spawner.AbstractSpawner;

public class InterfaceMethods {
    public interface FireBlockMethods {
        default void flammable(Block block, int encouragement, int flammability) {}
    }

    public interface SpawnerMinecartMethods {
        default AbstractSpawner getSpawner() {
            return null;
        }
    }
}
