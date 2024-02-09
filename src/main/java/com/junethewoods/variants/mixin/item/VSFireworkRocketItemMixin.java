package com.junethewoods.variants.mixin.item;

import com.google.common.collect.Lists;
import com.junethewoods.variants.Variants;
import com.junethewoods.variants.config.VSConfigs;
import com.junethewoods.variants.util.VSStyles;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.FireworkRocketItem;
import net.minecraft.item.FireworkStarItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.util.text.*;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import javax.annotation.Nullable;
import java.util.List;

@Mixin(FireworkRocketItem.class)
public class VSFireworkRocketItemMixin extends Item {
    public VSFireworkRocketItemMixin(Properties properties) {
        super(properties);
    }

    @Inject(method = "appendHoverText", at = @At("HEAD"), cancellable = true)
    public void appendHoverText(ItemStack stack, @Nullable World world, List<ITextComponent> tooltip, ITooltipFlag flag, CallbackInfo ci) {
        if (VSConfigs.COMMON_CONFIGS.customFireworkDescriptions.get()) {
            ci.cancel();
            CompoundNBT fireworksTag = stack.getTagElement("Fireworks");

            if (fireworksTag == null) {
                TranslationTextComponent unknownFlightDuration = new TranslationTextComponent("tooltip.variants.firework_rocket.flight_duration.unknown");
                tooltip.add(new TranslationTextComponent("tooltip.variants.firework_rocket.flight_duration", unknownFlightDuration).withStyle(TextFormatting.DARK_GRAY));
            }
            if (fireworksTag != null) {
                if (fireworksTag.contains("Flight", 99)) {
                    TextFormatting flightDurationColor = TextFormatting.DARK_RED;
                    if (fireworksTag.getByte("Flight") == 2) flightDurationColor = TextFormatting.GOLD;
                    if (fireworksTag.getByte("Flight") == 3) flightDurationColor = TextFormatting.GREEN;
                    if (fireworksTag.getByte("Flight") >= 4) flightDurationColor = TextFormatting.WHITE;

                    tooltip.add(new TranslationTextComponent("tooltip.variants.firework_rocket.flight_duration", fireworksTag.getByte("Flight")).withStyle(flightDurationColor));

                    ListNBT explosionsNBTList = fireworksTag.getList("Explosions", 10);
                    if (!explosionsNBTList.isEmpty()) {
                        tooltip.add(new StringTextComponent(""));
                        tooltip.add(new TranslationTextComponent("tooltip.variants.firework_rocket.explosions").withStyle(VSStyles.FIREWORK_TITLES));

                        for (int i = 0; i < explosionsNBTList.size(); ++i) {
                            CompoundNBT nbt = explosionsNBTList.getCompound(i);
                            List<ITextComponent> componentList = Lists.newArrayList();
                            FireworkStarItem.appendHoverText(nbt, componentList);
                            if (!componentList.isEmpty()) {
                                for (int j = 1; j < componentList.size(); ++j) {
                                    componentList.set(j, new StringTextComponent("").append(componentList.get(j)).withStyle(TextFormatting.GRAY));
                                }

                                tooltip.addAll(componentList);
                            }
                        }
                    }
                }
            }
        }
    }

    @Override
    public String getCreatorModId(ItemStack stack) {
        return VSConfigs.COMMON_CONFIGS.customFireworkDescriptions.get() ? Variants.MOD_ID : super.getCreatorModId(stack);
    }
}
