package melonystudios.variants.util.tag;

import melonystudios.variants.Variants;
import melonystudios.variants.item.fix.TagFix;
import net.minecraft.tags.ITag;
import net.minecraftforge.common.ForgeTagHandler;

public class TagFixTags {
    /// Tag fixes in this tag are also applied when the item's tag is (re)loaded.
    public static final ITag.INamedTag<TagFix> APPLIES_ON_TAG_RELOAD = revaried("applies_on_tag_reload");

    public static ITag.INamedTag<TagFix> revaried(String name) {
        return ForgeTagHandler.makeWrapperTag(Variants.variants("tag_fix"), Variants.variants(name));
    }
}
