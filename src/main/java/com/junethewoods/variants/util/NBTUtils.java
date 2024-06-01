package com.junethewoods.variants.util;

import com.junethewoods.variants.Variants;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;

import java.util.List;

public class NBTUtils {
    public static boolean shouldHideTooltip(String toHide, ItemStack stack) {
        CompoundNBT hideTooltipsTag = stack.getTagElement("hide_vs_tooltips");
        if (hideTooltipsTag != null) return !hideTooltipsTag.getBoolean(toHide);
        return true;
    }

    public static void addItemTagsTooltip(ItemStack stack, List<ITextComponent> tooltip, ITooltipFlag flag) {
        if (flag.isAdvanced()) {
            CompoundNBT tagTag = stack.getTag();
            if (tagTag != null) tooltip.add(new TranslationTextComponent("tooltip." + Variants.MOD_ID + ".tags", tagTag.getPrettyDisplay()).withStyle(TextFormatting.GRAY));
        }
    }

    public static ListNBT writeEffectsOntoNBT(EffectInstance[] instances) {
        ListNBT effectsList = new ListNBT();
        if (instances != null) {
            for (EffectInstance instance : instances) {
                CompoundNBT effectTag = new CompoundNBT();

                effectTag.putString("id", instance.getEffect().getRegistryName().toString());
                effectTag.putInt("duration", instance.getDuration());
                if (instance.getAmplifier() > 0) effectTag.putInt("amplifier", instance.getAmplifier());
                if (instance.isAmbient()) effectTag.putBoolean("ambient", instance.isAmbient());
                if (!instance.isVisible()) effectTag.putBoolean("show_particles", instance.isVisible());
                if (!instance.showIcon()) effectTag.putBoolean("show_icon", instance.showIcon());
                if (instance.isNoCounter()) effectTag.putBoolean("no_counter", instance.isNoCounter());
                if (instance.getCurativeItems().size() > 1) {
                    ListNBT curativeItems = new ListNBT();
                    for (ItemStack curativeStack : instance.getCurativeItems()) {
                        CompoundNBT savedStack = curativeStack.save(new CompoundNBT());
                        curativeItems.add(savedStack);
                    }
                    effectTag.put("curative_items", curativeItems);
                }
                effectsList.add(effectTag);
            }
        }
        return effectsList;
    }
}
