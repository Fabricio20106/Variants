package com.junethewoods.variants.item.custom.poisoning;

import net.minecraft.potion.Effect;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.ForgeRegistryEntry;

public class PoisoningType extends ForgeRegistryEntry<PoisoningType> {
    private final RegistryObject<Effect> poisoningEffect;

    public PoisoningType(RegistryObject<Effect> poisoningEffect) {
        this.poisoningEffect = poisoningEffect;
    }

    public RegistryObject<Effect> getPoisoningEffect() {
        return this.poisoningEffect;
    }

    public PoisoningType getTypeRegistry() {
        return VSPoisoningTypes.REDSTONE.get();
    }
}
