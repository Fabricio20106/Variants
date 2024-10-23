package melonystudios.variants.util;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.world.World;

// Basically backporting data component functionality as NBT tags.
// ~isa 12-20-24
public class ComponentUtils {
    public static boolean enchantmentGlintOverride(ItemStack stack, boolean fallback) {
        CompoundNBT tag = stack.getTag();
        if (tag != null && tag.contains("enchantment_glint_override", Constants.TagTypes.ANY_NUMERIC)) {
            return tag.getBoolean("enchantment_glint_override");
        } else return fallback;
    }

    public static int maxStackSize(ItemStack stack, int fallback) {
        CompoundNBT tag = stack.getTag();
        if (tag != null && tag.contains("max_stack_size", Constants.TagTypes.ANY_NUMERIC)) {
            return tag.getInt("max_stack_size");
        } else return fallback;
    }

    public static int maxDamage(ItemStack stack, int fallback) {
        CompoundNBT tag = stack.getTag();
        if (tag != null && tag.contains("max_damage", Constants.TagTypes.ANY_NUMERIC)) {
            return tag.getInt("max_damage");
        } else return fallback;
    }

    public static int enchantable(ItemStack stack, int fallback) {
        CompoundNBT tag = stack.getTag();
        if (tag != null && tag.contains("enchantable", Constants.TagTypes.ANY_NUMERIC)) {
            return tag.getInt("enchantable");
        } else return fallback;
    }

    // New
    public static int itemEntityLifespan(ItemStack stack, World world, int fallback) {
        CompoundNBT tag = stack.getTag();
        if (tag != null && tag.contains("item_entity_lifespan", Constants.TagTypes.ANY_NUMERIC)) {
            return tag.getInt("item_entity_lifespan");
        } else return fallback;
    }
}
