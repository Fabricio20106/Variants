package melonystudios.variants.mixin.item;

import melonystudios.variants.config.VSConfigs;
import melonystudios.variants.util.tag.VSEnchantmentTags;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ShearsItem;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(ShearsItem.class)
public class VSShearsItemMixin extends Item {
    public VSShearsItemMixin(Properties properties) {
        super(properties);
    }

    @Override
    public boolean canApplyAtEnchantingTable(ItemStack stack, Enchantment enchamentment) {
        return VSConfigs.COMMON_CONFIGS.enchantableShears.get() ? super.canApplyAtEnchantingTable(stack, enchamentment) || enchamentment.isIn(VSEnchantmentTags.APPLICABLE_TO_SHEARS) : super.canApplyAtEnchantingTable(stack, enchamentment);
    }

    @Override
    public int getEnchantmentValue() {
        return VSConfigs.COMMON_CONFIGS.enchantableShears.get() ? 15 : 0;
    }
}
