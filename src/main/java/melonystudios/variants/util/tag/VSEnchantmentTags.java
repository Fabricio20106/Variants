package melonystudios.variants.util.tag;

import melonystudios.variants.Variants;
import melonystudios.variants.util.VSUtils;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.tags.ITag;
import net.minecraftforge.common.ForgeTagHandler;

public class VSEnchantmentTags {
    public static final ITag.INamedTag<Enchantment> APPLICABLE_TO_SHEARS = variants("applicable_to/shears");
    public static final ITag.INamedTag<Enchantment> APPLICABLE_TO_FLINT_AND_STEEL = variants("applicable_to/flint_and_steel");
    public static final ITag.INamedTag<Enchantment> APPLICABLE_TO_SHIELDS = variants("applicable_to/shields");

    private static ITag.INamedTag<Enchantment> variants(String name) {
        return ForgeTagHandler.makeWrapperTag(VSUtils.minecraft("enchantment"), Variants.variants(name));
    }
}
