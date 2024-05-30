package com.junethewoods.variants.item.custom.poisoning.custom;

import com.junethewoods.variants.effect.VSEffects;
import com.junethewoods.variants.item.custom.poisoning.PoisoningType;
import com.junethewoods.variants.item.custom.poisoning.VSPoisoningTypes;

public class GlowstonePoisoningType extends PoisoningType {
    public GlowstonePoisoningType() {
        super(VSEffects.GLOWSTONE_POISONING);
    }

    @Override
    public PoisoningType getTypeRegistry() {
        return VSPoisoningTypes.GLOWSTONE.get();
    }
}
