package melonystudios.revaried.misc.particle;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.DripParticle;
import net.minecraft.world.level.material.Fluid;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class FallingParticle extends DripParticle {
    public FallingParticle(ClientLevel level, double x, double y, double z, Fluid type) {
        this(level, (int) (64 / (Math.random() * 0.8 + 0.2)), x, y, z, type);
    }

    public FallingParticle(ClientLevel world, int lifetime, double x, double y, double z, Fluid type) {
        super(world, x, y, z, type);
        this.lifetime = lifetime;
    }

    @Override
    protected void postMoveUpdate() {
        if (this.onGround) this.remove();
    }
}
