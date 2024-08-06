package melonystudios.variants.util.tag;

import melonystudios.variants.Variants;
import melonystudios.variants.stew.StewBehavior;
import net.minecraft.tags.ITag;
import net.minecraft.tags.ITagCollection;
import net.minecraft.tags.TagRegistryManager;
import net.minecraftforge.common.ForgeTagHandler;

public class StewBehaviorTags {
    public static ITag.INamedTag<StewBehavior> mod(String name) {
        return ForgeTagHandler.makeWrapperTag(Variants.resourceLoc("stew_behavior"), Variants.resourceLoc(name));
    }

    @SuppressWarnings("unchecked")
    public static ITagCollection<StewBehavior> getAllTags() {
        return (ITagCollection<StewBehavior>) TagRegistryManager.get(Variants.resourceLoc("stew_behavior")).getAllTags();
    }
}
