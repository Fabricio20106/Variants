package com.junethewoods.variants.mixin.item;

import com.junethewoods.variants.Variants;
import com.junethewoods.variants.config.VSConfigs;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ShieldItem;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(ShieldItem.class)
public class VSShieldItemMixin extends Item {
    public VSShieldItemMixin(Properties properties) {
        super(properties);
    }

    @Override
    public boolean canApplyAtEnchantingTable(ItemStack stack, Enchantment enchamentment) {
        return VSConfigs.COMMON_CONFIGS.enchantableShields.get() ? super.canApplyAtEnchantingTable(stack, enchamentment) || enchamentment == Enchantments.UNBREAKING :
                super.canApplyAtEnchantingTable(stack, enchamentment);
    }

    @Override
    public int getEnchantmentValue() {
        return VSConfigs.COMMON_CONFIGS.enchantableShields.get() ? 15 : 0;
    }

    @Override
    public String getCreatorModId(ItemStack stack) {
        return VSConfigs.COMMON_CONFIGS.enchantableShields.get() ? Variants.MOD_ID : super.getCreatorModId(stack);
    }
}
