package com.junethewoods.variants.item.custom.food;

import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.world.World;

public class LavaBottleItem extends DrinkableContainerItem {
    private final boolean containsSoulLava;

    public LavaBottleItem(boolean containsSoulLava, Properties properties) {
        super(properties);
        this.containsSoulLava = containsSoulLava;
    }

    @Override
    public void bottleFunctionality(ItemStack containerStack, ItemStack stack, World world, LivingEntity livEntity) {
        this.containerItem = new ItemStack(Items.GLASS_BOTTLE);
        if (!world.isClientSide) livEntity.setSecondsOnFire(containsSoulLava ? 10 : 5);
    }
}
