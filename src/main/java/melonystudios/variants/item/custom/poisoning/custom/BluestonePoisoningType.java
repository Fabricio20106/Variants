package melonystudios.variants.item.custom.poisoning.custom;

import melonystudios.variants.effect.VSEffects;
import melonystudios.variants.item.custom.poisoning.PoisoningType;
import melonystudios.variants.item.custom.poisoning.VSPoisoningTypes;

public class BluestonePoisoningType extends PoisoningType {
    public BluestonePoisoningType() {
        super(VSEffects.BLUESTONE_POISONING);
    }

    @Override
    public PoisoningType getTypeRegistry() {
        return VSPoisoningTypes.BLUESTONE.get();
    }
}
