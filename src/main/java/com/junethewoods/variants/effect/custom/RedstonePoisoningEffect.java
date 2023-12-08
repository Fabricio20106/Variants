package com.junethewoods.variants.effect.custom;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;

public class RedstonePoisoningEffect extends MobEffect {
    public RedstonePoisoningEffect() {
        super(MobEffectCategory.HARMFUL, 0xaa0f01);
    }

    @Override
    public void applyEffectTick(LivingEntity livEntity, int potency) {
//        livEntity.hurt(VSDamageSources.REDSTONE_POISONING, potency == 0 ? 1 : potency);
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int duration, int potency) {
        return duration % 10 == 0;
    }
}
