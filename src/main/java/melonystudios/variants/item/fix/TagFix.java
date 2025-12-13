package melonystudios.variants.item.fix;

import melonystudios.variants.util.RVRegistries;
import melonystudios.variants.util.tag.TagFixTags;
import net.minecraft.item.Item;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tags.ITag;
import net.minecraftforge.registries.ForgeRegistryEntry;

import java.util.List;
import java.util.stream.Collectors;

/// A **tag fix** is a simple way to automatically correct the NBT data of items from older version of mods.
public abstract class TagFix extends ForgeRegistryEntry<TagFix> {
    /// Applies a fix on a compound tag, usually the item's NBT data.
    /// @param tag The tag to fix.
    public abstract void applyFix(CompoundNBT tag);

    /// An ingredient representing all items this tag fix is applied to. In other words, it's all items that may have the tag for fixing.
    public abstract List<Item> applicableItems();

    /// Whether this tag fix also {@linkplain Item#verifyTagAfterLoad applies on tag reloading}.
    /// @return Whether this tag fix is in `#variants:applies_on_tag_reload`.
    public boolean appliesOnTagLoad() {
        return this.is(TagFixTags.APPLIES_ON_TAG_RELOAD);
    }

    /// Whether the provided tag fix is in a tag.
    /// @param fixTag The tag to check.
    public boolean is(ITag<TagFix> fixTag) {
        return fixTag.contains(this);
    }

    /// Provides a list of all fixes that can be applied to the provided item.
    /// @param item The item to fix.
    public static List<TagFix> getApplicableFixes(Item item) {
        return RVRegistries.TAG_FIX.getValues().stream()
                .filter(fix -> fix.applicableItems().contains(item))
                .collect(Collectors.toList());
    }

    /// Provides a list of all fixes that can be applied to the provided item **during tag loading**.
    /// @param item The item to fix.
    public static List<TagFix> getApplicableFixesOnLoad(Item item) {
        return getApplicableFixes(item).stream()
                .filter(TagFix::appliesOnTagLoad)
                .collect(Collectors.toList());
    }
}
