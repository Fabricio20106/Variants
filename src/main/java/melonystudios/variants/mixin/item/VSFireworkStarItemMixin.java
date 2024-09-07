package melonystudios.variants.mixin.item;

import melonystudios.variants.config.VSConfigs;
import melonystudios.variants.util.VSStyles;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.*;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.text.*;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(FireworkStarItem.class)
public class VSFireworkStarItemMixin extends Item {
    public VSFireworkStarItemMixin(Properties properties) {
        super(properties);
    }

    @Inject(method = "appendHoverText(Lnet/minecraft/item/ItemStack;Lnet/minecraft/world/World;Ljava/util/List;Lnet/minecraft/client/util/ITooltipFlag;)V", at = @At("HEAD"))
    public void appendHoverText(ItemStack stack, World world, List<ITextComponent> tooltip, ITooltipFlag flag, CallbackInfo ci) {
        super.appendHoverText(stack, world, tooltip, flag);
    }

    @Inject(method = "appendHoverText(Lnet/minecraft/nbt/CompoundNBT;Ljava/util/List;)V", at = @At("HEAD"), cancellable = true)
    @OnlyIn(Dist.CLIENT)
    private static void appendHoverText(CompoundNBT tag, List<ITextComponent> tooltip, CallbackInfo ci) {
        if (VSConfigs.COMMON_CONFIGS.customFireworkDescriptions.get()) {
            ci.cancel();

            // Firework Star Shape
            FireworkRocketItem.Shape fireworkShapes = FireworkRocketItem.Shape.byId(tag.getByte("Type"));
            TranslationTextComponent shapeTranslation = new TranslationTextComponent("tooltip.variants.firework_star.shape." + fireworkShapes.getName());

            tooltip.add(new TranslationTextComponent("tooltip.variants.firework_star.shape", shapeTranslation).withStyle(TextFormatting.GRAY));

            // Main Color(s)
            int[] mainColors = tag.getIntArray("Colors");
            if (mainColors.length > 0) tooltip.add(appendColorsVS(new TranslationTextComponent("tooltip.variants.firework_star.main_colors").withStyle(TextFormatting.GRAY), mainColors));

            // Fade Color(s)
            int[] fadeColors = tag.getIntArray("FadeColors");
            if (fadeColors.length > 0) tooltip.add(appendColorsVS(new TranslationTextComponent("tooltip.variants.firework_star.fade_colors").withStyle(TextFormatting.GRAY), fadeColors));

            // Has Trail (Diamond)
            if (tag.getBoolean("Trail")) tooltip.add(new TranslationTextComponent("tooltip.variants.firework_star.effect.trail").withStyle(VSStyles.DIAMOND));

            // Has Twinkle (Glowstone Dust)
            if (tag.getBoolean("Flicker")) tooltip.add(new TranslationTextComponent("tooltip.variants.firework_star.effect.twinkle").withStyle(VSStyles.GLOWSTONE_DUST));
        }
    }

    @Unique
    @OnlyIn(Dist.CLIENT)
    private static ITextComponent appendColorsVS(IFormattableTextComponent component, int[] colors) {
        for (int i = 0; i < colors.length; ++i) {
            if (i > 0) {
                TranslationTextComponent separatorComponent = new TranslationTextComponent("tooltip.variants.firework_star.comma_color_separator");
                if (i == colors.length - 1) separatorComponent = new TranslationTextComponent("tooltip.variants.firework_star.and_color_separator");
                component.append(separatorComponent).withStyle(TextFormatting.GRAY);
            }
            component.append(getColorNameVS(colors[i]));
        }

        return component;
    }

    @Unique
    @OnlyIn(Dist.CLIENT)
    private static ITextComponent getColorNameVS(int color) {
        DyeColor dyeColor = DyeColor.byFireworkColor(color);
        return dyeColor == null ? new TranslationTextComponent("tooltip.variants.firework_star.custom_color", color).withStyle(TextFormatting.DARK_AQUA).withStyle(TextFormatting.UNDERLINE) : new TranslationTextComponent("tooltip.variants.firework_star.color." + dyeColor.getName())
                .withStyle(VSStyles.getFromBeaconBeamColor(dyeColor));
    }
}
