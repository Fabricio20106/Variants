package com.junethewoods.variants.effect.custom;

import com.junethewoods.variants.effect.VSDamageSources;
import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.EffectType;
import net.minecraft.potion.Effects;

public class CreeperPowderPoisoningEffect extends Effect {
    public CreeperPowderPoisoningEffect() {
        super(EffectType.HARMFUL, 0xEBAA4E);
    }

    @Override
    public void applyEffectTick(LivingEntity livEntity, int potency) {
        livEntity.hurt(VSDamageSources.CREEPER_POWDER_POISONING, potency == 0 ? 1 : potency);
    }

    @Override
    public boolean isDurationEffectTick(int duration, int potency) {
        return duration % 10 == 0;
    }
}
