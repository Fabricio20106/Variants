package melonystudios.variants.mixin.item;

import melonystudios.variants.Variants;
import melonystudios.variants.util.tag.VSEnchantmentTags;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ShearsItem;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(ShearsItem.class)
public class RVShearsItemMixin extends Item {
    public RVShearsItemMixin(Properties properties) {
        super(properties);
    }

    @Override
    public boolean canApplyAtEnchantingTable(ItemStack stack, Enchantment enchantment) {
        return Variants.revaried().settings().enchantableShears ? super.canApplyAtEnchantingTable(stack, enchantment) || enchantment.isIn(VSEnchantmentTags.APPLICABLE_TO_SHEARS) : super.canApplyAtEnchantingTable(stack, enchantment);
    }

    @Override
    public int getEnchantmentValue() {
        return Variants.revaried().settings().enchantableShears ? 15 : 0;
    }
}
