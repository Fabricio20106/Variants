package com.junethewoods.variants.item.custom.poisoning.custom;

import com.junethewoods.variants.effect.VSEffects;
import com.junethewoods.variants.item.custom.poisoning.PoisoningType;
import com.junethewoods.variants.item.custom.poisoning.VSPoisoningTypes;
import net.minecraft.potion.EffectInstance;

public class SugarPotType extends PoisoningType {
    public SugarPotType() {
        super(VSEffects.SUGAR_POT_SPEED);
    }

    @Override
    public PoisoningType getTypeRegistry() {
        return VSPoisoningTypes.SUGAR.get();
    }
}
