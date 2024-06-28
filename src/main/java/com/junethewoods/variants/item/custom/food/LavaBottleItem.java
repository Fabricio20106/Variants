package com.junethewoods.variants.item.custom.food;

import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.world.World;

public class LavaBottleItem extends DrinkableContainerItem {
    private final int secondsOnFire;

    public LavaBottleItem(int secondsOnFire, Properties properties) {
        super(properties);
        this.secondsOnFire = secondsOnFire;
    }

    @Override
    public void executeFunctionality(ItemStack containerStack, ItemStack bottleStack, World world, LivingEntity livEntity) {
        this.containerItem = new ItemStack(Items.GLASS_BOTTLE);
        if (!world.isClientSide) livEntity.setSecondsOnFire(this.secondsOnFire);
    }
}
