package com.junethewoods.variants.item.custom.stew.behavior;

import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.world.World;
import net.minecraftforge.registries.ForgeRegistryEntry;

public abstract class PoisoningBehavior extends ForgeRegistryEntry<PoisoningBehavior> {
    public PoisoningBehavior() {}

    public abstract void executeBehavior(ItemStack stack, World world, LivingEntity livEntity);

    public EffectInstance getEffects() {
        return null;
    }
}
