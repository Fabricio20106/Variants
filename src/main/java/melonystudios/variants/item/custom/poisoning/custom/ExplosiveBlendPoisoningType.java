package melonystudios.variants.item.custom.poisoning.custom;

import melonystudios.variants.effect.VSEffects;
import melonystudios.variants.item.custom.poisoning.PoisoningType;
import melonystudios.variants.item.custom.poisoning.VSPoisoningTypes;

public class ExplosiveBlendPoisoningType extends PoisoningType {
    public ExplosiveBlendPoisoningType() {
        super(VSEffects.EXPLOSIVE_BLEND_POISONING);
    }

    @Override
    public PoisoningType getTypeRegistry() {
        return VSPoisoningTypes.EXPLOSIVE_BLEND.get();
    }
}
