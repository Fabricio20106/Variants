package melonystudios.variants.util.tag;

import melonystudios.variants.Variants;
import melonystudios.variants.item.fix.TagFix;
import net.minecraft.tags.ITag;
import net.minecraftforge.common.ForgeTagHandler;

public class TagFixTags {
    public static final ITag.INamedTag<TagFix> APPLIES_ON_TAG_RELOAD = variants("applies_on_tag_reload");

    public static ITag.INamedTag<TagFix> variants(String name) {
        return ForgeTagHandler.makeWrapperTag(Variants.variants("tag_fix"), Variants.variants(name));
    }
}
