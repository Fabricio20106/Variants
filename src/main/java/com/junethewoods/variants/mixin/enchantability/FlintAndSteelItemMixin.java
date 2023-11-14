package com.junethewoods.variants.mixin.enchantability;

import com.junethewoods.variants.Variants;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.FlintAndSteelItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(FlintAndSteelItem.class)
public class FlintAndSteelItemMixin extends Item {
    public FlintAndSteelItemMixin(Properties properties) {
        super(properties);
    }

    @Override
    public boolean canApplyAtEnchantingTable(ItemStack stack, Enchantment enchamentment) {
        return super.canApplyAtEnchantingTable(stack, enchamentment) || enchamentment == Enchantments.UNBREAKING;
    }

    @Override
    public int getEnchantmentValue() {
        return 15;
    }

    @Override
    public String getCreatorModId(ItemStack itemStack) {
        return Variants.MOD_ID;
    }
}
