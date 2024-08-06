package melonystudios.variants.effect.custom;

import melonystudios.variants.util.damage.VSDamageSources;
import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectType;

public class ExplosiveBlendPoisoningEffect extends Effect {
    public ExplosiveBlendPoisoningEffect() {
        super(EffectType.HARMFUL, 0x47BF4A);
    }

    @Override
    public void applyEffectTick(LivingEntity livEntity, int potency) {
        livEntity.hurt(VSDamageSources.EXPLOSIVE_BLEND_POISONING, potency == 0 ? 1 : potency);
    }

    @Override
    public boolean isDurationEffectTick(int duration, int potency) {
        return duration % 10 == 0;
    }
}
