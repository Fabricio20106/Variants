package com.junethewoods.variants.effect.custom;

import com.junethewoods.variants.effect.VSEffects;
import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.EffectType;
import net.minecraft.potion.Effects;

public class SugarPotSpeedEffect extends Effect {
    public SugarPotSpeedEffect() {
        super(EffectType.HARMFUL, 8171462);
    }

    @Override
    public void applyEffectTick(LivingEntity livEntity, int amplifier) {
        livEntity.addEffect(new EffectInstance(Effects.MOVEMENT_SPEED, 100));
        livEntity.removeEffect(VSEffects.SUGAR_POT_SPEED.get());
    }

    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {
        return true;
    }
}
