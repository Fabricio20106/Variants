package melonystudios.variants.util.tag;

import melonystudios.variants.Variants;
import melonystudios.variants.stew.StewBehavior;
import net.minecraft.tags.ITag;
import net.minecraft.tags.ITagCollection;
import net.minecraft.tags.TagRegistryManager;
import net.minecraftforge.common.ForgeTagHandler;

public class StewBehaviorTags {
    public static final ITag.INamedTag<StewBehavior> CANNOT_RUN_WITHOUT_NBT = variants("cannot_run_without_nbt");

    public static ITag.INamedTag<StewBehavior> variants(String name) {
        return ForgeTagHandler.makeWrapperTag(Variants.variants("stew_behavior"), Variants.variants(name));
    }

    @SuppressWarnings("unchecked")
    public static ITagCollection<StewBehavior> getAllTags() {
        return (ITagCollection<StewBehavior>) TagRegistryManager.get(Variants.variants("stew_behavior")).getAllTags();
    }
}
