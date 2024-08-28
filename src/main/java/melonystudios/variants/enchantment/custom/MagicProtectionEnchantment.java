package melonystudios.variants.enchantment.custom;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.enchantment.ProtectionEnchantment;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.util.DamageSource;

public class MagicProtectionEnchantment extends Enchantment {
    public MagicProtectionEnchantment() {
        super(Rarity.UNCOMMON, EnchantmentType.ARMOR, new EquipmentSlotType[] {EquipmentSlotType.HEAD, EquipmentSlotType.CHEST, EquipmentSlotType.LEGS, EquipmentSlotType.FEET});
    }

    @Override
    public int getMinCost(int level) {
        return 5 + (level - 1) * 8;
    }

    @Override
    public int getMaxLevel() {
        return 4;
    }

    @Override
    public int getDamageProtection(int level, DamageSource source) {
        if (source.isBypassInvul()) return super.getDamageProtection(level, source);
        else return level * 2;
    }

    @Override
    protected boolean checkCompatibility(Enchantment enchantment) {
        if (enchantment instanceof ProtectionEnchantment) {
            if (((ProtectionEnchantment) enchantment).type != ProtectionEnchantment.Type.FALL) return false;
        } else if (enchantment instanceof MagicProtectionEnchantment) return false;
        return super.checkCompatibility(enchantment);
    }
}
