package melonystudios.variants.mixin.enchantment;

import melonystudios.variants.Variants;
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
        return Variants.INSTANCE.getConfig().quickChargeMaxLevel;
    }
}
