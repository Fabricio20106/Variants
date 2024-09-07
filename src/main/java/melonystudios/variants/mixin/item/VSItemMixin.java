package melonystudios.variants.mixin.item;

import melonystudios.variants.Variants;
import melonystudios.variants.config.VSConfigs;
import melonystudios.variants.util.NBTUtils;
import melonystudios.variants.util.VSKeys;
import melonystudios.variants.util.VSUtils;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(Item.class)
public class VSItemMixin {
    @OnlyIn(Dist.CLIENT)
    @Inject(method = "appendHoverText", at = @At("HEAD"))
    public void appendHoverText(ItemStack stack, World world, List<ITextComponent> tooltip, ITooltipFlag flag, CallbackInfo ci) {
        if (flag.isAdvanced() && stack.getTag() != null && VSConfigs.COMMON_CONFIGS.showTagsWithAlt.get()) {
            boolean shouldHideTooltip = NBTUtils.shouldNotHideTooltip("hide_item_tags", stack);
            if (shouldHideTooltip && !VSKeys.isAltDown()) tooltip.add(new TranslationTextComponent("tooltip." + Variants.MOD_ID + ".hold_alt", VSKeys.getTranslation(VSKeys.SHOW_TAGS_KEY).withStyle(TextFormatting.GRAY)).withStyle(TextFormatting.DARK_GRAY));
            if (shouldHideTooltip && VSKeys.isAltDown()) tooltip.add(new TranslationTextComponent("tooltip." + Variants.MOD_ID + ".hold_alt.held", VSKeys.getTranslation(VSKeys.SHOW_TAGS_KEY).withStyle(TextFormatting.WHITE)).withStyle(TextFormatting.DARK_GRAY));
            if (shouldHideTooltip && VSKeys.isAltDown()) NBTUtils.addItemTagsTooltip(stack, tooltip, flag);
        }
        if (stack.getItem().getFoodProperties() != null && !stack.getItem().getFoodProperties().getEffects().isEmpty() && VSConfigs.COMMON_CONFIGS.showFoodEffects.get()) {
            VSUtils.addEffectsTooltip(stack, tooltip, 1);
        }
    }
}
