package com.junethewoods.variants.mixin;

import com.junethewoods.variants.config.VSConfigs;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.enchantment.QuickChargeEnchantment;
import net.minecraft.inventory.EquipmentSlotType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(QuickChargeEnchantment.class)
public class QuickChargeEnchantmentMixin extends Enchantment {
    public QuickChargeEnchantmentMixin(Rarity rarity, EquipmentSlotType[] slots) {
        super(rarity, EnchantmentType.CROSSBOW, slots);
    }

    @Override
    public int getMaxLevel() {
        return VSConfigs.COMMON_CONFIGS.enableQuickChargeFive.get() ? 5 : 3;
    }
}
