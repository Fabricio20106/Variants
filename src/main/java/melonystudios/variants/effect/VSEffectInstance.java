package melonystudios.variants.effect;

import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;

import javax.annotation.Nonnull;
import java.util.function.Supplier;

// Variant of EffectInstance that uses suppliers for the effect because effects aren't registered during item registry.
public class VSEffectInstance extends EffectInstance {
    private final Supplier<Effect> effectSupplier;

    public VSEffectInstance(Supplier<Effect> effect, int duration) {
        super(null, duration);
        this.effectSupplier = effect;
    }

    @Override
    @Nonnull
    public Effect getEffect() {
        return this.effectSupplier.get();
    }
}
