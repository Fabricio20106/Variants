package com.junethewoods.variants.item.custom.food;

import com.junethewoods.variants.item.VSItems;
import com.junethewoods.variants.item.custom.PoisoningType;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

public class StylisedPotItem extends DrinkableContainerItem {
    private final PoisoningType poisoningEffect;
    private final int poisoningDuration;
    private final String compatMod;

    public StylisedPotItem(Properties properties, String compatMod, PoisoningType poisoningEffect, int poisoningDuration) {
        super(properties);
        this.poisoningEffect = poisoningEffect;
        if (poisoningEffect.getPoisoningEffect().isPresent() && poisoningEffect.getPoisoningEffect().get().isInstantenous()) {
            this.poisoningDuration = poisoningDuration;
        } else {
            this.poisoningDuration = poisoningDuration * 20;
        }
        this.compatMod = compatMod;
    }

    public StylisedPotItem(Properties properties, PoisoningType poisoningEffect, int poisoningDuration) {
        this(properties, null, poisoningEffect, poisoningDuration);
    }

    @Override
    public void bottleFunctionality(ItemStack containerStack, ItemStack stack, World world, LivingEntity livEntity) {
        this.containerItem = new ItemStack(VSItems.STYLISED_POT.get());
        if (poisoningEffect != null) {
            if (!world.isClientSide) livEntity.addEffect(new EffectInstance(poisoningEffect.getPoisoningEffect().get(), this.poisoningDuration));
        }
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable World world, List<ITextComponent> tooltip, ITooltipFlag flag) {
        if (compatMod != null) {
            tooltip.add(new TranslationTextComponent("tooltip.variants.compat_item_from", compatMod).withStyle(TextFormatting.GRAY).withStyle(TextFormatting.ITALIC));
        }
        super.appendHoverText(stack, world, tooltip, flag);
    }
}
