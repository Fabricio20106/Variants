package com.junethewoods.variants.mixin.enchantment;

import com.junethewoods.variants.Variants;
import com.junethewoods.variants.config.VSConfigs;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.util.text.IFormattableTextComponent;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Enchantment.class)
public abstract class VSEnchantmentMixin {
    @Shadow
    public abstract int getMaxLevel();
    @Shadow
    public abstract String getDescriptionId();
    @Shadow
    public abstract boolean isCurse();
    @Shadow
    public abstract boolean isTreasureOnly();
    @Shadow
    @Final
    public EnchantmentType category;

    @Inject(method = "getFullname", at = @At("HEAD"), cancellable = true)
    private void getFullname(int enchLevel, CallbackInfoReturnable<ITextComponent> cir) {
        if (VSConfigs.COMMON_CONFIGS.customEnchantmentDescriptions.get()) {
            cir.cancel();
            IFormattableTextComponent component = new TranslationTextComponent(getDescriptionId());

            if (isCurse()) {
                component.withStyle(TextFormatting.RED);
            } else if (isTreasureOnly()) {
                component.withStyle(TextFormatting.YELLOW);
            } else {
                component.withStyle(TextFormatting.GRAY);
            }

            if (getMaxLevel() != 1) {
                component.append(" ").append(new TranslationTextComponent("enchantment.level." + enchLevel));
            }

            if (VSConfigs.COMMON_CONFIGS.enchantmentTypesOnTooltip.get()) component.append(new TranslationTextComponent("tooltip." + Variants.MOD_ID + ".enchant.type", new TranslationTextComponent(getTypeForEnchantment())));

            cir.setReturnValue(component);
        }
    }

    @Unique
    private String getTypeForEnchantment() {
        switch (category) {
            case ARMOR: return "tooltip.variants.enchant_type.armor";
            case ARMOR_HEAD: return "tooltip.variants.enchant_type.helmet";
            case ARMOR_CHEST: return "tooltip.variants.enchant_type.chestplate";
            case ARMOR_LEGS: return "tooltip.variants.enchant_type.leggings";
            case ARMOR_FEET: return "tooltip.variants.enchant_type.boots";
            case WEAPON: return "tooltip.variants.enchant_type.weapon";
            case DIGGER: return "tooltip.variants.enchant_type.digger";
            case FISHING_ROD: return "tooltip.variants.enchant_type.fishing_rod";
            case TRIDENT: return "tooltip.variants.enchant_type.trident";
            case BREAKABLE: return "tooltip.variants.enchant_type.breakable";
            case BOW: return "tooltip.variants.enchant_type.bow";
            case CROSSBOW: return "tooltip.variants.enchant_type.crossbow";
            case WEARABLE: return "tooltip.variants.enchant_type.wearable";
            case VANISHABLE: return "tooltip.variants.enchant_type.vanishable";
            default: return "tooltip.variants.enchant_type.other";
        }
    }
}
