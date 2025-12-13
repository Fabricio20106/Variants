package melonystudios.variants.util.tag;

import melonystudios.variants.Variants;
import melonystudios.variants.consumable.ConsumeBehavior;
import net.minecraft.tags.ITag;
import net.minecraft.tags.ITagCollection;
import net.minecraft.tags.TagRegistryManager;
import net.minecraftforge.common.ForgeTagHandler;

public class ConsumeBehaviorTags {
    public static final ITag.INamedTag<ConsumeBehavior> CANNOT_RUN_WITHOUT_NBT = revaried("cannot_run_without_nbt");

    public static ITag.INamedTag<ConsumeBehavior> revaried(String name) {
        return ForgeTagHandler.makeWrapperTag(Variants.variants("consume_behavior"), Variants.variants(name));
    }

    @SuppressWarnings("unchecked")
    public static ITagCollection<ConsumeBehavior> getAllTags() {
        return (ITagCollection<ConsumeBehavior>) TagRegistryManager.get(Variants.variants("consume_behavior")).getAllTags();
    }
}
