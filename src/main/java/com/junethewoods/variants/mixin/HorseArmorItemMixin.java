package com.junethewoods.variants.mixin;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.HorseArmorItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

import javax.annotation.Nullable;
import java.util.List;

@Mixin(HorseArmorItem.class)
public class HorseArmorItemMixin {
    @Unique
    public void appendHoverText(ItemStack stack, @Nullable World world, List<ITextComponent> tooltip, ITooltipFlag flag) {
        HorseArmorItem horseArmor = ((HorseArmorItem) stack.getItem());

        tooltip.add(new StringTextComponent(""));
        tooltip.add(new TranslationTextComponent("tooltip.variants.horse_armor.when_on_horse").withStyle(TextFormatting.GRAY));
        tooltip.add(new TranslationTextComponent("tooltip.variants.horse_armor.armor_stats", horseArmor.getProtection()).withStyle(TextFormatting.BLUE));
    }
}
