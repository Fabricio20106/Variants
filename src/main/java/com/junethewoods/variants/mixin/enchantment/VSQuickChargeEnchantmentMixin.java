package com.junethewoods.variants.mixin.enchantment;

import com.junethewoods.variants.config.VSConfigs;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.enchantment.QuickChargeEnchantment;
import net.minecraft.inventory.EquipmentSlotType;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(QuickChargeEnchantment.class)
public class VSQuickChargeEnchantmentMixin extends Enchantment {
    public VSQuickChargeEnchantmentMixin(Rarity rarity, EquipmentSlotType[] slots) {
        super(rarity, EnchantmentType.CROSSBOW, slots);
    }

    @Override
    public int getMaxLevel() {
        return VSConfigs.COMMON_CONFIGS.enableQuickChargeFive.get() ? 5 : 3;
    }
}
