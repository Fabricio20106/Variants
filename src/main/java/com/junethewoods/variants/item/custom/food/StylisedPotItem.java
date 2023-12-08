package com.junethewoods.variants.item.custom.food;

import com.junethewoods.variants.item.VSItems;
import com.junethewoods.variants.item.custom.IPoisoningType;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;
import java.util.List;

public class StylisedPotItem extends DrinkableContainerItem {
    private final IPoisoningType poisoningEffect;
    private final int poisoningDuration;
    private final String compatMod;

    public StylisedPotItem(Properties properties, String compatMod, IPoisoningType poisoningEffect, int poisoningDuration) {
        super(properties);
        this.poisoningEffect = poisoningEffect;
//        if (poisoningEffect.getPoisoningEffect(). && poisoningEffect.getPoisoningEffect().get().isInstantenous()) {
//            this.poisoningDuration = poisoningDuration;
//        } else {
            this.poisoningDuration = poisoningDuration * 20;
//        }
        this.compatMod = compatMod;
    }

    public StylisedPotItem(Properties properties, IPoisoningType poisoningEffect, int poisoningDuration) {
        this(properties, null, poisoningEffect, poisoningDuration);
    }

    @Override
    public void bottleFunctionality(ItemStack containerStack, ItemStack stack, Level level, LivingEntity livEntity) {
        if (poisoningEffect != null) {
            if (!level.isClientSide) livEntity.addEffect(new MobEffectInstance(poisoningEffect.getPoisoningEffect().get(), this.poisoningDuration));
        }
    }

    @Override
    public ItemStack setContainerItem(ItemStack stack) {
        return new ItemStack(VSItems.STYLISED_POT.get());
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
        if (compatMod != null) {
            tooltip.add(Component.translatable("tooltip.variants.compat_item_from", compatMod).withStyle(ChatFormatting.GRAY).withStyle(ChatFormatting.ITALIC));
        }
        super.appendHoverText(stack, level, tooltip, flag);
    }
}
