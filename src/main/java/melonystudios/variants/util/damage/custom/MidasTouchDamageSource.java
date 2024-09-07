package melonystudios.variants.util.damage.custom;

import net.minecraft.entity.LivingEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;

import javax.annotation.Nonnull;

public class MidasTouchDamageSource extends DamageSource {
    public MidasTouchDamageSource() {
        super("turned_to_gold");
    }

    @Override
    @Nonnull
    public ITextComponent getLocalizedDeathMessage(LivingEntity livEntity) {
        return new TranslationTextComponent("death.midas.turned_into_gold", livEntity.getDisplayName());
    }
}
