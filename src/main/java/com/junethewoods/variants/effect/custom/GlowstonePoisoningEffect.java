package com.junethewoods.variants.effect.custom;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;

public class GlowstonePoisoningEffect extends MobEffect {
    public GlowstonePoisoningEffect() {
        super(MobEffectCategory.HARMFUL, 0xEBAA4E);
    }

    @Override
    public void applyEffectTick(LivingEntity livEntity, int potency) {
//        livEntity.hurt(VSDamageSources.GLOWSTONE_POISONING, potency == 0 ? 1 : potency);
        livEntity.addEffect(new MobEffectInstance(MobEffects.GLOWING, 40));
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int duration, int potency) {
        return duration % 10 == 0;
    }
}
