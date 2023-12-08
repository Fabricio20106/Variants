package com.junethewoods.variants.item.custom.food;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class PowderSnowBottleItem extends DrinkableContainerItem {
    public PowderSnowBottleItem(Properties properties) {
        super(properties);
    }

    @Override
    public void bottleFunctionality(ItemStack containerStack, ItemStack stack, Level level, LivingEntity livEntity) {}
}
