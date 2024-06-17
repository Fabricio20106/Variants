package com.junethewoods.variants.item.custom.stew.custom;

import com.junethewoods.variants.item.custom.stew.StewBehavior;
import com.junethewoods.variants.item.custom.stew.VSStewBehaviors;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.world.World;

public class IgniteBehavior extends StewBehavior {
    private final int ticksOnFire;

    public IgniteBehavior(int ticksOnFire) {
        this.ticksOnFire = ticksOnFire;
    }

    public IgniteBehavior() {
        this(200);
    }

    @Override
    public void executeBehavior(ItemStack stack, World world, LivingEntity livEntity) {
        if (!world.isClientSide) livEntity.setSecondsOnFire(this.ticksOnFire * 20);
    }

    @Override
    public CompoundNBT writePropertiesToNBT(ItemStack stewStack) {
        CompoundNBT properties = new CompoundNBT();
        properties.putInt("ticks_on_fire", this.ticksOnFire);
        return properties;
    }

    @Override
    public StewBehavior getBehaviorRegistry() {
        return VSStewBehaviors.IGNITE.get();
    }
}
