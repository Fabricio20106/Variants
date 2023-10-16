package com.junethewoods.variants.item.custom.food;

import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class PowderSnowBottleItem extends DrinkableContainerItem {
    public PowderSnowBottleItem(Properties properties) {
        super(properties);
    }

    @Override
    public void bottleFunctionality(ItemStack containerStack, ItemStack stack, World world, LivingEntity livEntity) {}
}
