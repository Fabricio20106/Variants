package com.junethewoods.variants.item.custom.poisoning.custom;

import com.junethewoods.variants.effect.VSEffects;
import com.junethewoods.variants.item.custom.poisoning.PoisoningType;
import com.junethewoods.variants.item.custom.poisoning.VSPoisoningTypes;

public class BluestonePoisoningType extends PoisoningType {
    public BluestonePoisoningType() {
        super(VSEffects.BLUESTONE_POISONING);
    }

    @Override
    public PoisoningType getTypeRegistry() {
        return VSPoisoningTypes.BLUESTONE.get();
    }
}
