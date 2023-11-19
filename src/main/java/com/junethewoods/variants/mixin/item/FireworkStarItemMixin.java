package com.junethewoods.variants.mixin.item;

import com.junethewoods.variants.Variants;
import com.junethewoods.variants.config.VSConfigs;
import com.junethewoods.variants.util.VSStyles;
import net.minecraft.item.*;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.text.*;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(FireworkStarItem.class)
public class FireworkStarItemMixin extends Item {
    public FireworkStarItemMixin(Properties properties) {
        super(properties);
    }

    @Inject(method = "appendHoverText(Lnet/minecraft/nbt/CompoundNBT;Ljava/util/List;)V", at = @At("HEAD"), cancellable = true)
    private static void appendHoverText(CompoundNBT nbt, List<ITextComponent> tooltip, CallbackInfo ci) {
        if (VSConfigs.COMMON_CONFIGS.customFireworkDescriptions.get()) {
            ci.cancel();

            // Firework Star Shape
            FireworkRocketItem.Shape fireworkShapes = FireworkRocketItem.Shape.byId(nbt.getByte("Type"));
            TranslationTextComponent shapeTranslation = new TranslationTextComponent("tooltip.variants.firework_star.shape." + fireworkShapes.getName());

            tooltip.add(new TranslationTextComponent("tooltip.variants.firework_star.shape", shapeTranslation).withStyle(TextFormatting.GRAY));

            // Main Color(s)
            int[] mainColors = nbt.getIntArray("Colors");
            if (mainColors.length > 0) {
                tooltip.add(appendColors(new TranslationTextComponent("tooltip.variants.firework_star.main_colors").withStyle(TextFormatting.GRAY), mainColors));
            }

            // Fade Color(s)
            int[] fadeColors = nbt.getIntArray("FadeColors");
            if (fadeColors.length > 0) {
                tooltip.add(appendColors(new TranslationTextComponent("tooltip.variants.firework_star.fade_colors").withStyle(TextFormatting.GRAY), fadeColors));
            }

            // Has Trail (Diamond)
            if (nbt.getBoolean("Trail")) {
                tooltip.add(new TranslationTextComponent("tooltip.variants.firework_star.effect.trail").withStyle(VSStyles.DIAMOND));
            }

            // Has Twinkle (Glowstone Dust)
            if (nbt.getBoolean("Flicker")) {
                tooltip.add(new TranslationTextComponent("tooltip.variants.firework_star.effect.twinkle").withStyle(VSStyles.GLOWSTONE_DUST));
            }
        }
    }

    @OnlyIn(Dist.CLIENT)
    private static ITextComponent appendColors(IFormattableTextComponent textComponent, int[] colors) {
        for(int i = 0; i < colors.length; ++i) {
            if (i > 0) {
                textComponent.append(new TranslationTextComponent("tooltip.variants.firework_star.color_separator")).withStyle(TextFormatting.GRAY);
            }
            textComponent.append(getColorName(colors[i]));
        }

        return textComponent;
    }

    @OnlyIn(Dist.CLIENT)
    private static ITextComponent getColorName(int color) {
        DyeColor dyeColor = DyeColor.byFireworkColor(color);
        return dyeColor == null ? new TranslationTextComponent("tooltip.variants.firework_star.custom_color", color).withStyle(TextFormatting.DARK_AQUA)
                .withStyle(TextFormatting.UNDERLINE) : new TranslationTextComponent("tooltip.variants.firework_star.color." + dyeColor.getName());
    }

    @Override
    public String getCreatorModId(ItemStack stack) {
        return VSConfigs.COMMON_CONFIGS.customFireworkDescriptions.get() ? Variants.MOD_ID : super.getCreatorModId(stack);
    }
}
