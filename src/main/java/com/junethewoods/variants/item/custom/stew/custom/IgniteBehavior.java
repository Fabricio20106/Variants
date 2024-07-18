package com.junethewoods.variants.item.custom.stew.custom;

import com.junethewoods.variants.item.custom.stew.StewBehavior;
import com.junethewoods.variants.item.custom.stew.VSStewBehaviors;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.world.World;

import static com.junethewoods.variants.util.NBTUtils.integerOrDefault;

public class IgniteBehavior extends StewBehavior {
    private final int ticksOnFire;

    public IgniteBehavior(int ticksOnFire) {
        this.ticksOnFire = ticksOnFire;
    }

    public IgniteBehavior() {
        this(100);
    }

    public int getTicksOnFire() {
        return this.ticksOnFire;
    }

    @Override
    public void executeBehavior(ItemStack stack, World world, LivingEntity livEntity) {
        if (!world.isClientSide) livEntity.setSecondsOnFire(this.ticksOnFire * 20);
    }

    @Override
    public void executeFromStewNBT(ItemStack stewStack, World world, LivingEntity livEntity, CompoundNBT propertiesTag) {
        IgniteBehavior igniteBehavior = new IgniteBehavior(integerOrDefault("ticks_on_fire", propertiesTag, 100));
        igniteBehavior.executeBehavior(stewStack, world, livEntity);
    }

    @Override
    public CompoundNBT writePropertiesToNBT() {
        CompoundNBT properties = new CompoundNBT();
        properties.putInt("ticks_on_fire", this.ticksOnFire);
        return properties;
    }

    @Override
    public StewBehavior getBehaviorRegistry() {
        return VSStewBehaviors.IGNITE.get();
    }
}
