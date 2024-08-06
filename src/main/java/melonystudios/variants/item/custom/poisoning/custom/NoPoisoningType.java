package melonystudios.variants.item.custom.poisoning.custom;

import melonystudios.variants.item.custom.poisoning.PoisoningType;
import melonystudios.variants.item.custom.poisoning.VSPoisoningTypes;

public class NoPoisoningType extends PoisoningType {
    public NoPoisoningType() {
        super(null);
    }

    @Override
    public PoisoningType getTypeRegistry() {
        return VSPoisoningTypes.NONE.get();
    }
}
