package melonystudios.variants.util.tag;

import melonystudios.variants.Variants;
import melonystudios.variants.consumable.ConsumeBehavior;
import net.minecraft.tags.ITag;
import net.minecraft.tags.ITagCollection;
import net.minecraft.tags.TagRegistryManager;
import net.minecraftforge.common.ForgeTagHandler;

public class ConsumeBehaviorTags {
    public static final ITag.INamedTag<ConsumeBehavior> CANNOT_RUN_WITHOUT_NBT = variants("cannot_run_without_nbt");

    public static ITag.INamedTag<ConsumeBehavior> variants(String name) {
        return ForgeTagHandler.makeWrapperTag(Variants.variants("stew_behavior"), Variants.variants(name));
    }

    @SuppressWarnings("unchecked")
    public static ITagCollection<ConsumeBehavior> getAllTags() {
        return (ITagCollection<ConsumeBehavior>) TagRegistryManager.get(Variants.variants("stew_behavior")).getAllTags();
    }
}
