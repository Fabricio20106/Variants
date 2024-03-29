package com.junethewoods.variants.mixin.item;

import com.junethewoods.variants.Variants;
import com.junethewoods.variants.config.VSConfigs;
import com.junethewoods.variants.util.NBTUtils;
import com.junethewoods.variants.util.VSKeys;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
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
            boolean shouldHideTooltip = NBTUtils.shouldHideTooltip("hide_item_tags", stack);
            if (shouldHideTooltip && !VSKeys.isAltDown()) tooltip.add(new TranslationTextComponent("tooltip." + Variants.MOD_ID + ".hold_alt"));
            if (shouldHideTooltip && VSKeys.isAltDown()) tooltip.add(new TranslationTextComponent("tooltip." + Variants.MOD_ID + ".hold_alt.held"));
            if (shouldHideTooltip && VSKeys.isAltDown()) NBTUtils.addItemTagsTooltip(stack, tooltip, flag);
        }
    }
}
