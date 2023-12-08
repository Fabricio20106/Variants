package com.junethewoods.variants.effect.custom;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;

public class ExplosiveBlendPoisoningEffect extends MobEffect {
    public ExplosiveBlendPoisoningEffect() {
        super(MobEffectCategory.HARMFUL, 0xEBAA4E);
    }

    @Override
    public void applyEffectTick(LivingEntity livEntity, int potency) {
//        livEntity.hurt(VSDamageSources.CREEPER_POWDER_POISONING, potency == 0 ? 1 : potency);
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int duration, int potency) {
        return duration % 10 == 0;
    }
}
