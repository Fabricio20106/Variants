package melonystudios.variants.item.custom.poisoning.custom;

import melonystudios.variants.effect.VSEffects;
import melonystudios.variants.item.custom.poisoning.PoisoningType;
import melonystudios.variants.item.custom.poisoning.VSPoisoningTypes;

public class SugarPotType extends PoisoningType {
    public SugarPotType() {
        super(VSEffects.SUGAR_POT_SPEED);
    }

    @Override
    public PoisoningType getTypeRegistry() {
        return VSPoisoningTypes.SUGAR.get();
    }
}
