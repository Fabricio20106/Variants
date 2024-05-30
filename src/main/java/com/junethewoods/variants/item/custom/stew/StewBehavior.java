package com.junethewoods.variants.item.custom.stew;

import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.world.World;
import net.minecraftforge.registries.ForgeRegistryEntry;

public abstract class StewBehavior extends ForgeRegistryEntry<StewBehavior> {
    public abstract void executeBehavior(ItemStack stack, World world, LivingEntity livEntity);

    public EffectInstance getEffects() {
        return null;
    }

    public StewBehavior getBehaviorRegistry() {
        return VSStewBehaviors.DEFAULT.get();
    }
}
