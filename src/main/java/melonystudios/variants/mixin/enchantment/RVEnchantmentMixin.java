package melonystudios.variants.mixin.enchantment;

import melonystudios.variants.Variants;
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
public abstract class RVEnchantmentMixin {
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
    private void getFullEnchantmentName(int level, CallbackInfoReturnable<ITextComponent> callback) {
        if (!Variants.revaried().settings().updatedEnchantmentTooltips) return;
        callback.cancel();
        IFormattableTextComponent translation = new TranslationTextComponent(this.getDescriptionId());

        if (this.isCurse()) {
            translation.withStyle(TextFormatting.RED);
        } else if (this.isTreasureOnly()) {
            translation.withStyle(TextFormatting.YELLOW);
        } else {
            translation.withStyle(TextFormatting.GRAY);
        }

        if (this.getMaxLevel() > 1) {
            translation.append(" ").append(new TranslationTextComponent("enchantment.level." + level));
        }

        if (Variants.revaried().settings().enchantmentTypesTooltip) translation.append(
                new TranslationTextComponent("tooltip." + Variants.MOD_ID + ".enchant.type",
                        new TranslationTextComponent(this.getEnchantmentType()))
        );

        callback.setReturnValue(translation);
    }

    @Unique
    private String getEnchantmentType() {
        switch (this.category) {
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
