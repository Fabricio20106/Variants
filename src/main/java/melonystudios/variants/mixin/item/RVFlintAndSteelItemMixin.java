package melonystudios.variants.mixin.item;

import melonystudios.variants.Variants;
import melonystudios.variants.util.tag.VSEnchantmentTags;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.FlintAndSteelItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(FlintAndSteelItem.class)
public class RVFlintAndSteelItemMixin extends Item {
    public RVFlintAndSteelItemMixin(Properties properties) {
        super(properties);
    }

    @Override
    public boolean canApplyAtEnchantingTable(ItemStack stack, Enchantment enchantment) {
        return Variants.revaried().settings().enchantableFlintAndSteel ? super.canApplyAtEnchantingTable(stack, enchantment) || enchantment.isIn(VSEnchantmentTags.APPLICABLE_TO_FLINT_AND_STEEL) : super.canApplyAtEnchantingTable(stack, enchantment);
    }

    @Override
    public int getEnchantmentValue() {
        return Variants.revaried().settings().enchantableFlintAndSteel ? 15 : 0;
    }
}
