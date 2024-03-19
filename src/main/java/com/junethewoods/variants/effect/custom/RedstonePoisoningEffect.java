package com.junethewoods.variants.effect.custom;

import com.junethewoods.variants.effect.VSDamageSources;
import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectType;

public class RedstonePoisoningEffect extends Effect {
    public RedstonePoisoningEffect() {
        super(EffectType.HARMFUL, 0xAA0F01);
    }

    @Override
    public void applyEffectTick(LivingEntity livEntity, int potency) {
        livEntity.hurt(VSDamageSources.REDSTONE_POISONING, potency == 0 ? 1 : potency);
    }

    @Override
    public boolean isDurationEffectTick(int duration, int potency) {
        return duration % 10 == 0;
    }
}
