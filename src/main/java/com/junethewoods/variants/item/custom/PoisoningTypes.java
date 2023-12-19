package com.junethewoods.variants.item.custom;

import com.junethewoods.variants.effect.VSEffects;
import net.minecraft.potion.Effect;
import net.minecraftforge.fml.RegistryObject;

public class PoisoningTypes implements PoisoningType {
    public static final PoisoningType REDSTONE = new PoisoningTypes(VSEffects.REDSTONE_POISONING);
    public static final PoisoningType BLUESTONE = new PoisoningTypes(VSEffects.BLUESTONE_POISONING);
    public static final PoisoningType GLOWSTONE = new PoisoningTypes(VSEffects.GLOWSTONE_POISONING);
    public static final PoisoningType GUNPOWDER = new PoisoningTypes(VSEffects.GUNPOWDER_POISONING);
    public static final PoisoningType EXPLOSIVE_BLEND = new PoisoningTypes(VSEffects.EXPLOSIVE_BLEND_POISONING);

    private final RegistryObject<Effect> poisoningEffect;

    public PoisoningTypes(RegistryObject<Effect> effect) {
        this.poisoningEffect = effect;
    }

    @Override
    public RegistryObject<Effect> getPoisoningEffect() {
        return this.poisoningEffect;
    }
}
