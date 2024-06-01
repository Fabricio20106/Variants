package com.junethewoods.variants.item.custom.stew.custom;

import com.junethewoods.variants.item.custom.stew.StewBehavior;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.world.World;

public class DefaultStewBehavior extends StewBehavior {
    @Override
    public void executeBehavior(ItemStack stack, World world, LivingEntity livEntity) {}

    @Override
    public CompoundNBT writePropertiesToNBT(ItemStack stewStack) {
        return new CompoundNBT();
    }
}
