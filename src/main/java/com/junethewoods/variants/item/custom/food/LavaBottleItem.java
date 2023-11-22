package com.junethewoods.variants.item.custom.food;

import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class LavaBottleItem extends DrinkableContainerItem {
    public LavaBottleItem(Properties properties) {
        super(properties);
    }

    @Override
    public void bottleFunctionality(ItemStack containerStack, ItemStack stack, World world, LivingEntity livEntity) {
        // FORGE - Move up so stack.shrink doesn't turn stack into air.
        if (!world.isClientSide) livEntity.hurt(DamageSource.ON_FIRE, Float.MAX_VALUE);
        if (!world.isClientSide) livEntity.setSecondsOnFire(100);
    }
}
