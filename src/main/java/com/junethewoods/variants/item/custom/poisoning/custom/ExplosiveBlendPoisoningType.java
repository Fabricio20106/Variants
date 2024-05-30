package com.junethewoods.variants.item.custom.poisoning.custom;

import com.junethewoods.variants.effect.VSEffects;
import com.junethewoods.variants.item.custom.poisoning.PoisoningType;
import com.junethewoods.variants.item.custom.poisoning.VSPoisoningTypes;

public class ExplosiveBlendPoisoningType extends PoisoningType {
    public ExplosiveBlendPoisoningType() {
        super(VSEffects.EXPLOSIVE_BLEND_POISONING);
    }

    @Override
    public PoisoningType getTypeRegistry() {
        return VSPoisoningTypes.EXPLOSIVE_BLEND.get();
    }
}
