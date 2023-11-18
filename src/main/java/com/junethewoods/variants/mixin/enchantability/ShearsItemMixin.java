package com.junethewoods.variants.mixin.enchantability;

import com.junethewoods.variants.Variants;
import com.junethewoods.variants.config.VSConfigs;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ShearsItem;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(ShearsItem.class)
public class ShearsItemMixin extends Item {
    public ShearsItemMixin(Properties properties) {
        super(properties);
    }

    @Override
    public boolean canApplyAtEnchantingTable(ItemStack stack, Enchantment enchamentment) {
        return VSConfigs.COMMON_CONFIGS.enchantableShears.get() ? super.canApplyAtEnchantingTable(stack, enchamentment) || enchamentment == Enchantments.UNBREAKING || enchamentment == Enchantments.BLOCK_EFFICIENCY
                || enchamentment == Enchantments.BLOCK_FORTUNE : super.canApplyAtEnchantingTable(stack, enchamentment);
    }

    @Override
    public int getEnchantmentValue() {
        return VSConfigs.COMMON_CONFIGS.enchantableShears.get() ? 15 : 0;
    }

    @Override
    public String getCreatorModId(ItemStack stack) {
        return VSConfigs.COMMON_CONFIGS.enchantableShears.get() ? Variants.MOD_ID : super.getCreatorModId(stack);
    }
}
