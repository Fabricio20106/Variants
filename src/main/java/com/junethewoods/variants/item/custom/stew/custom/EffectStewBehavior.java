package com.junethewoods.variants.item.custom.stew.custom;

import com.junethewoods.variants.item.custom.stew.StewBehavior;
import com.junethewoods.variants.item.custom.stew.VSStewBehaviors;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.world.World;

public class EffectStewBehavior extends StewBehavior {
    private final EffectInstance effects;

    public EffectStewBehavior(EffectInstance effects) {
        this.effects = effects;
    }

    public EffectStewBehavior() {
        this(null);
    }

    @Override
    public void executeBehavior(ItemStack stack, World world, LivingEntity livEntity) {}

    @Override
    public EffectInstance getEffects() {
        return this.effects;
    }

    @Override
    public StewBehavior getBehaviorRegistry() {
        return VSStewBehaviors.EFFECT.get();
    }
}
