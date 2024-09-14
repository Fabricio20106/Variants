package melonystudios.variants.mixin.item;

import melonystudios.variants.config.VSConfigs;
import melonystudios.variants.util.tag.VSEnchantmentTags;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.FlintAndSteelItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(FlintAndSteelItem.class)
public class VSFlintAndSteelItemMixin extends Item {
    public VSFlintAndSteelItemMixin(Properties properties) {
        super(properties);
    }

    @Override
    public boolean canApplyAtEnchantingTable(ItemStack stack, Enchantment enchamentment) {
        return VSConfigs.COMMON_CONFIGS.enchantableFlintAndSteel.get() ? super.canApplyAtEnchantingTable(stack, enchamentment) || enchamentment.isIn(VSEnchantmentTags.APPLICABLE_TO_FLINT_AND_STEEL) : super.canApplyAtEnchantingTable(stack, enchamentment);
    }

    @Override
    public int getEnchantmentValue() {
        return VSConfigs.COMMON_CONFIGS.enchantableFlintAndSteel.get() ? 15 : 0;
    }
}
