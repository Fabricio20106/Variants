package com.junethewoods.variants.item.custom.stew.custom;

import com.junethewoods.variants.item.custom.stew.StewBehavior;
import com.junethewoods.variants.item.custom.stew.VSStewBehaviors;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class LavaStewBehavior extends StewBehavior {
    private final boolean containsSoulLava;

    public LavaStewBehavior(boolean containsSoulLava) {
        this.containsSoulLava = containsSoulLava;
    }

    public LavaStewBehavior() {
        this(false);
    }

    @Override
    public void executeBehavior(ItemStack stack, World world, LivingEntity livEntity) {
        if (!world.isClientSide) livEntity.setSecondsOnFire(this.containsSoulLava ? 10 : 5);
    }

    @Override
    public StewBehavior getBehaviorRegistry() {
        return VSStewBehaviors.LAVA.get();
    }
}
