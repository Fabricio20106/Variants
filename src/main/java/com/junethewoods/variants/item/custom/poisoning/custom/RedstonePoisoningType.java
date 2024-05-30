package com.junethewoods.variants.item.custom.poisoning;

import net.minecraft.potion.Effect;
import net.minecraftforge.fml.RegistryObject;

public class DefaultPoisoningType extends PoisoningType {
    public DefaultPoisoningType(RegistryObject<Effect> poisoningEffect) {
        super(poisoningEffect);
    }
}
