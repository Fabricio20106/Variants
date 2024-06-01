package com.junethewoods.variants.item.custom.poisoning.custom;

import com.junethewoods.variants.item.custom.poisoning.PoisoningType;
import com.junethewoods.variants.item.custom.poisoning.VSPoisoningTypes;

public class NoPoisoningType extends PoisoningType {
    public NoPoisoningType() {
        super(null);
    }

    @Override
    public PoisoningType getTypeRegistry() {
        return VSPoisoningTypes.NONE.get();
    }
}
