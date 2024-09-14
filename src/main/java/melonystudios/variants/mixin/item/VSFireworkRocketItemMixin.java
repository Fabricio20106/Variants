package melonystudios.variants.mixin.item;

import com.google.common.collect.Lists;
import melonystudios.variants.Variants;
import melonystudios.variants.config.VSConfigs;
import melonystudios.variants.util.Constants;
import melonystudios.variants.util.NBTUtils;
import melonystudios.variants.util.VSKeys;
import melonystudios.variants.util.VSStyles;
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
        if (flag.isAdvanced() && stack.getTag() != null && VSConfigs.COMMON_CONFIGS.showTagsWithAlt.get()) {
            boolean shouldHideTooltip = NBTUtils.shouldNotHideTooltip("hide_item_tags", stack);
            if (shouldHideTooltip && !VSKeys.isAltDown()) tooltip.add(new TranslationTextComponent("tooltip." + Variants.MOD_ID + ".hold_alt", VSKeys.getTranslation(VSKeys.SHOW_TAGS_KEY).withStyle(TextFormatting.GRAY)).withStyle(TextFormatting.DARK_GRAY));
            if (shouldHideTooltip && VSKeys.isAltDown()) tooltip.add(new TranslationTextComponent("tooltip." + Variants.MOD_ID + ".hold_alt.held", VSKeys.getTranslation(VSKeys.SHOW_TAGS_KEY).withStyle(TextFormatting.WHITE)).withStyle(TextFormatting.DARK_GRAY));
            if (shouldHideTooltip && VSKeys.isAltDown()) NBTUtils.addItemTagsTooltip(stack, tooltip, flag);
        }
        if (VSConfigs.COMMON_CONFIGS.customFireworkDescriptions.get()) {
            ci.cancel();
            CompoundNBT fireworksTag = stack.getTagElement("Fireworks");

            if (fireworksTag == null) {
                tooltip.add(new TranslationTextComponent("tooltip.variants.firework_rocket.flight_duration", new TranslationTextComponent("tooltip.variants.firework_rocket.flight_duration.unknown").withStyle(TextFormatting.DARK_GRAY)).withStyle(TextFormatting.GRAY));
            } else {
                if (fireworksTag.contains("Flight", Constants.TagTypes.ANY_NUMERIC)) {
                    TextFormatting flightDurationColor = TextFormatting.DARK_RED;
                    if (fireworksTag.getByte("Flight") == 2) flightDurationColor = TextFormatting.GOLD;
                    if (fireworksTag.getByte("Flight") == 3) flightDurationColor = TextFormatting.GREEN;
                    if (fireworksTag.getByte("Flight") >= 4) flightDurationColor = TextFormatting.WHITE;

                    tooltip.add(new TranslationTextComponent("tooltip.variants.firework_rocket.flight_duration", new StringTextComponent("" + fireworksTag.getByte("Flight")).withStyle(flightDurationColor)).withStyle(TextFormatting.GRAY));

                    ListNBT explosions = fireworksTag.getList("Explosions", Constants.TagTypes.COMPOUND);
                    if (!explosions.isEmpty()) {
                        tooltip.add(new StringTextComponent(" "));
                        tooltip.add(new TranslationTextComponent("tooltip.variants.firework_rocket.explosions").withStyle(VSStyles.FIREWORK_TITLES));

                        for (int i = 0; i < explosions.size(); ++i) {
                            CompoundNBT explosion = explosions.getCompound(i);
                            List<ITextComponent> componentList = Lists.newArrayList();
                            FireworkStarItem.appendHoverText(explosion, componentList);
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
}
