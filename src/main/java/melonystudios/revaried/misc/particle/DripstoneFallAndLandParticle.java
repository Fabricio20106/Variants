package melonystudios.revaried.misc.particle;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Mth;
import net.minecraft.world.level.material.Fluid;

public class DripstoneFallAndLandParticle extends FallAndLandParticle {
    public DripstoneFallAndLandParticle(ClientLevel level, double x, double y, double z, Fluid type, ParticleOptions landParticle) {
        super(level, x, y, z, type, landParticle);
    }

    @Override
    protected void postMoveUpdate() {
        if (this.onGround) {
            this.remove();
            this.level.addParticle(this.landParticle, this.x, this.y, this.z, 0, 0, 0);
            SoundEvent sound = this.getType().is(FluidTags.LAVA) ? SoundEvents.POINTED_DRIPSTONE_DRIP_LAVA : SoundEvents.POINTED_DRIPSTONE_DRIP_WATER;
            float volume = Mth.randomBetween(this.random, 0.3F, 1);
            this.level.playLocalSound(this.x, this.y, this.z, sound, SoundSource.BLOCKS, volume, 1, false);
        }
    }
}
