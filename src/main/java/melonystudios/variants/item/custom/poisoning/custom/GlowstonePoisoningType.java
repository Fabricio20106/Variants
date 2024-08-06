package melonystudios.variants.item.custom.poisoning.custom;

import melonystudios.variants.effect.VSEffects;
import melonystudios.variants.item.custom.poisoning.PoisoningType;
import melonystudios.variants.item.custom.poisoning.VSPoisoningTypes;

public class GlowstonePoisoningType extends PoisoningType {
    public GlowstonePoisoningType() {
        super(VSEffects.GLOWSTONE_POISONING);
    }

    @Override
    public PoisoningType getTypeRegistry() {
        return VSPoisoningTypes.GLOWSTONE.get();
    }
}
