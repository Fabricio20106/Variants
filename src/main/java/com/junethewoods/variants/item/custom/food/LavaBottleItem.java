package com.junethewoods.variants.item.custom.food;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class LavaBottleItem extends DrinkableContainerItem {
    public LavaBottleItem(Properties properties) {
        super(properties);
    }

    @Override
    public void bottleFunctionality(ItemStack containerStack, ItemStack stack, Level level, LivingEntity livEntity) {
        // FORGE - Move up so stack.shrink doesn't turn stack into air.
        if (!level.isClientSide) livEntity.hurt(level.damageSources().onFire(), Float.MAX_VALUE);
        if (!level.isClientSide) livEntity.setSecondsOnFire(100);
    }
}
