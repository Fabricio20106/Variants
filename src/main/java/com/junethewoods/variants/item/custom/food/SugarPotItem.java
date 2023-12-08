package com.junethewoods.variants.item.custom.food;

import com.junethewoods.variants.item.VSItems;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class SugarPotItem extends DrinkableContainerItem {
    public SugarPotItem(Properties properties) {
        super(properties);
    }

    @Override
    public void bottleFunctionality(ItemStack containerStack, ItemStack stack, Level level, LivingEntity livEntity) {
        if (!level.isClientSide) livEntity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 100));
    }

    @Override
    public ItemStack setContainerItem(ItemStack stack) {
        return new ItemStack(VSItems.STYLISED_POT.get());
    }
}
