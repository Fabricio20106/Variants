package melonystudios.variants.item.custom.poisoning.custom;

import melonystudios.variants.effect.VSEffects;
import melonystudios.variants.item.custom.poisoning.PoisoningType;
import melonystudios.variants.item.custom.poisoning.VSPoisoningTypes;

public class GunpowderPoisoningType extends PoisoningType {
    public GunpowderPoisoningType() {
        super(VSEffects.GUNPOWDER_POISONING);
    }

    @Override
    public PoisoningType getTypeRegistry() {
        return VSPoisoningTypes.GUNPOWDER.get();
    }
}
