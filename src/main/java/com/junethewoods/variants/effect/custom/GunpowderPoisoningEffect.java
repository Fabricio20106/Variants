package com.junethewoods.variants.effect.custom;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;

public class GunpowderPoisoningEffect extends MobEffect {
    public GunpowderPoisoningEffect() {
        super(MobEffectCategory.HARMFUL, 0x727272);
    }

    @Override
    public void applyEffectTick(LivingEntity livEntity, int potency) {
//        livEntity.hurt(VSDamageSources.GUNPOWDER_POISONING, potency == 0 ? 1 : potency);
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int duration, int potency) {
        return duration % 10 == 0;
    }
}
