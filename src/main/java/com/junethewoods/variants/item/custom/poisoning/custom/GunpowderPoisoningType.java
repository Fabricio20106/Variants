package com.junethewoods.variants.item.custom.poisoning.custom;

import com.junethewoods.variants.effect.VSEffects;
import com.junethewoods.variants.item.custom.poisoning.PoisoningType;
import com.junethewoods.variants.item.custom.poisoning.VSPoisoningTypes;

public class GunpowderPoisoningType extends PoisoningType {
    public GunpowderPoisoningType() {
        super(VSEffects.GUNPOWDER_POISONING);
    }

    @Override
    public PoisoningType getTypeRegistry() {
        return VSPoisoningTypes.GUNPOWDER.get();
    }
}
