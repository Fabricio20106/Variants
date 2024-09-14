package melonystudios.variants.item.fix;

import melonystudios.variants.util.tag.TagFixTags;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.tags.ITag;
import net.minecraft.world.World;
import net.minecraftforge.registries.ForgeRegistryEntry;

import java.util.function.Supplier;

public abstract class TagFix extends ForgeRegistryEntry<TagFix> {
    private final Supplier<Ingredient> fixApplicableItems;

    public TagFix(Supplier<Ingredient> fixApplicableItems) {
        this.fixApplicableItems = fixApplicableItems;
    }

    public abstract boolean applyFix(ItemStack stack, World world, LivingEntity livEntity);

    public boolean appliesOnTagLoad() {
        return this.is(TagFixTags.APPLIES_ON_TAG_RELOAD);
    }

    public boolean is(ITag<TagFix> fixTag) {
        return fixTag.contains(this);
    }
}
