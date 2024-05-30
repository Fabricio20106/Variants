package com.junethewoods.variants.item.custom.poisoning.custom;

import com.junethewoods.variants.item.custom.poisoning.PoisoningType;
import com.junethewoods.variants.item.custom.poisoning.VSPoisoningTypes;
import net.minecraft.potion.Effect;
import net.minecraftforge.fml.RegistryObject;

public class BluestonePoisoningType extends PoisoningType {
    public BluestonePoisoningType(RegistryObject<Effect> poisoningEffect) {
        super(poisoningEffect);
    }

    @Override
    public PoisoningType getTypeRegistry() {
        return VSPoisoningTypes.BLUESTONE.get();
    }
}
