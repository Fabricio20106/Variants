package com.junethewoods.variants.item.custom.food;

import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.world.World;

public class MilkBottleItem extends DrinkableContainerItem {
    public MilkBottleItem(Properties properties) {
        super(properties);
    }

    @Override
    public void bottleFunctionality(ItemStack containerStack, ItemStack stack, World world, LivingEntity livEntity) {
        this.containerItem = new ItemStack(Items.GLASS_BOTTLE);
        // FORGE - move up so stack.shrink does not turn stack into air
        if (!world.isClientSide) livEntity.curePotionEffects(stack);
    }
}
