package com.junethewoods.variants.mixin.item;

import com.junethewoods.variants.Variants;
import com.junethewoods.variants.config.VSConfigs;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(ItemStack.class)
public class ItemStackMixin {
    @Inject(method = "appendEnchantmentNames", at = @At("HEAD"), cancellable = true)
    @OnlyIn(Dist.CLIENT)
    private static void appendEnchantmentNames(List<ITextComponent> tooltip, ListNBT tagList, CallbackInfo ci) {
        if (VSConfigs.COMMON_CONFIGS.customEnchantmentDescriptions.get()) {
            ci.cancel();
            if (!tagList.isEmpty()) {
                tooltip.add(new TranslationTextComponent("tooltip." + Variants.MOD_ID + ".enchant.enchantments").withStyle(TextFormatting.GOLD).withStyle(TextFormatting.BOLD));
            }
            for (int i = 0; i < tagList.size(); ++i) {
                CompoundNBT tagListCompound = tagList.getCompound(i);
                Registry.ENCHANTMENT.getOptional(ResourceLocation.tryParse(tagListCompound.getString("id"))).ifPresent((enchantment) ->
                        //tooltip.add(enchantment.getFullname(tagListCompound.getInt("lvl"))));
                        tooltip.add(new StringTextComponent(" > ").withStyle(TextFormatting.GOLD).append(enchantment.getFullname(tagListCompound.getInt("lvl"))))
                );
            }
        }
    }
}
