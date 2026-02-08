package melonystudios.revaried.misc.particle;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.DripParticle;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.world.level.material.Fluid;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class CoolingDripHangParticle extends DripParticle {
    private final ParticleOptions fallingParticle;

    public CoolingDripHangParticle(ClientLevel level, double x, double y, double z, Fluid type, ParticleOptions fallingParticle) {
        super(level, x, y, z, type);
        this.fallingParticle = fallingParticle;
        this.gravity *= 0.02F;
        this.lifetime = 40;
    }

    @Override
    protected void preMoveUpdate() {
        if (this.lifetime-- <= 0) {
            this.remove();
            this.level.addParticle(this.fallingParticle, this.x, this.y, this.z, this.xd, this.yd, this.zd);
        }
    }

    @Override
    protected void postMoveUpdate() {
        // todo: use soul lava color
        this.rCol = 1;
        this.gCol = 16 / (float) (40 - this.lifetime + 16);
        this.bCol = 4 / (float) (40 - this.lifetime + 8);
        this.xd *= 0.02;
        this.yd *= 0.02;
        this.zd *= 0.02;
    }
}
