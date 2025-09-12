package melonystudios.revaried.tag;

import melonystudios.revaried.Revaried;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public class RVItemTags {
    // Revaried's tags
    public static final TagKey<Item> SHULKER_SHELLS = revaried("shulker_shells");

    // Common tags
    public static final TagKey<Item> DYED_INNO = common("dyed/inno");
    public static final TagKey<Item> DYED_GLOW_BLACK = common("dyed/glow_black");

    public static TagKey<Item> revaried(String name) {
        return TagKey.create(Registries.ITEM, Revaried.revaried(name));
    }

    public static TagKey<Item> common(String name) {
        return TagKey.create(Registries.ITEM, Revaried.common(name));
    }
}
