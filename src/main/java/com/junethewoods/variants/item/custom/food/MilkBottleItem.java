package com.junethewoods.variants.item.custom.food;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class MilkBottleItem extends DrinkableContainerItem {
    public MilkBottleItem(Properties properties) {
        super(properties);
    }

    @Override
    public void bottleFunctionality(ItemStack containerStack, ItemStack stack, Level level, LivingEntity livEntity) {
        // FORGE - move up so stack.shrink does not turn stack into air
        if (!level.isClientSide) livEntity.curePotionEffects(stack);
    }
}
