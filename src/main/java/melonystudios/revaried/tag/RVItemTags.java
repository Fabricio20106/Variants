package melonystudios.revaried.tag;

import melonystudios.revaried.Revaried;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public class RVItemTags {
    // Revaried's tags
    /// Collection tag containing all shulker shells.
    public static final TagKey<Item> SHULKER_SHELLS = revaried("shulker_shells");

    // Common tags
    public static final TagKey<Item> DYED_INNO = common("dyed/inno");
    public static final TagKey<Item> DYED_GLOW_BLACK = common("dyed/glow_black");
    /// Soups, stews, and other liquid food in buckets belongs in this tag.
    /// Copy of {@link net.neoforged.neoforge.common.Tags.Items#FOODS_SOUP #c:foods/soup}.
    public static final TagKey<Item> BUCKET_SOUP_FOODS = common("foods/bucket_soup");
    public static final TagKey<Item> MUSHROOM_STEW_BUCKETS = common("buckets/mushroom_stew");
    public static final TagKey<Item> BEETROOT_SOUP_BUCKETS = common("buckets/beetroot_soup");
    public static final TagKey<Item> RABBIT_STEW_BUCKETS = common("buckets/rabbit_stew");
    public static final TagKey<Item> SUSPICIOUS_STEW_BUCKETS = common("buckets/suspicious_stew");
    public static final TagKey<Item> MELTING_BEET_SOUP_BUCKETS = common("buckets/melting_beet_soup");
    public static final TagKey<Item> FUNGI_STEW_BUCKETS = common("buckets/fungi_stew");
    public static final TagKey<Item> END_FUNGI_STEW_BUCKETS = common("buckets/end_fungi_stew");
    public static final TagKey<Item> SOUL_LAVA_BUCKETS = common("buckets/soul_lava");

    public static TagKey<Item> revaried(String name) {
        return TagKey.create(Registries.ITEM, Revaried.revaried(name));
    }

    public static TagKey<Item> common(String name) {
        return TagKey.create(Registries.ITEM, Revaried.common(name));
    }
}
