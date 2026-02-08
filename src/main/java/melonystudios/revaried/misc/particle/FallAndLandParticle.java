package melonystudios.revaried.misc.particle;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.world.level.material.Fluid;

public class FallAndLandParticle extends FallingParticle {
    protected final ParticleOptions landParticle;

    public FallAndLandParticle(ClientLevel level, double x, double y, double z, Fluid type, ParticleOptions landParticle) {
        super(level, x, y, z, type);
        this.landParticle = landParticle;
    }

    @Override
    protected void postMoveUpdate() {
        if (this.onGround) {
            this.remove();
            this.level.addParticle(this.landParticle, this.x, this.y, this.z, 0, 0, 0);
        }
    }
}
