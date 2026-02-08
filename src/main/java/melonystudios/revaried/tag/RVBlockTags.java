package melonystudios.revaried.tag;

import melonystudios.revaried.Revaried;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

public class RVBlockTags {
    public static final TagKey<Block> PICKUPABLE_SPAWNERS = revaried("pickupable_spawners");
    public static final TagKey<Block> SPAWNER_MINECARTS_CANNOT_REPLACE = revaried("spawner_minecarts_cannot_replace");

    public static TagKey<Block> revaried(String name) {
        return TagKey.create(Registries.BLOCK, Revaried.revaried(name));
    }
}
