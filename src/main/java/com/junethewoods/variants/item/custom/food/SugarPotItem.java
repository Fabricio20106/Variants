package com.junethewoods.variants.item.custom.food;

import com.junethewoods.variants.item.VSItems;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.world.World;

public class SugarPotItem extends DrinkableContainerItem {
    public SugarPotItem(Properties properties) {
        super(properties);
    }

    @Override
    public void bottleFunctionality(ItemStack containerStack, ItemStack stack, World world, LivingEntity livEntity) {
        this.containerItem = new ItemStack(VSItems.STYLISED_POT.get());

        if (!world.isClientSide) livEntity.addEffect(new EffectInstance(Effects.MOVEMENT_SPEED, 100));
    }
}
