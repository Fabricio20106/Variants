package com.junethewoods.variants.item.custom.stew;

import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.world.World;

public interface IStewBehavior {
    void executeBehavior(ItemStack stack, World world, LivingEntity livEntity);

    default EffectInstance getEffects() {
        return null;
    }
}
