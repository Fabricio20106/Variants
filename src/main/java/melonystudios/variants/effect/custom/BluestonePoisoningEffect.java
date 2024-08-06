package melonystudios.variants.effect.custom;

import melonystudios.variants.util.damage.VSDamageSources;
import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectType;

public class BluestonePoisoningEffect extends Effect {
    public BluestonePoisoningEffect() {
        super(EffectType.HARMFUL, 0x3D959C);
    }

    @Override
    public void applyEffectTick(LivingEntity livEntity, int potency) {
        livEntity.hurt(VSDamageSources.BLUESTONE_POISONING, potency == 0 ? 1 : potency);
    }

    @Override
    public boolean isDurationEffectTick(int duration, int potency) {
        return duration % 10 == 0;
    }
}
