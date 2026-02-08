package melonystudios.revaried.misc.particle;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.DripParticle;
import net.minecraft.world.level.material.Fluid;

public class DripLandParticle extends DripParticle {
    public DripLandParticle(ClientLevel level, double x, double y, double z, Fluid type) {
        super(level, x, y, z, type);
        this.lifetime = (int) (64 / (Math.random() * 0.8 + 0.2));
    }
}
