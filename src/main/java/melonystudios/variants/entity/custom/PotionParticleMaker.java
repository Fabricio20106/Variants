package melonystudios.variants.entity.custom;

import melonystudios.variants.item.custom.food.Consumable;
import melonystudios.variants.sound.VSSounds;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.renderer.ActiveRenderInfo;
import net.minecraft.client.settings.ParticleStatus;
import net.minecraft.item.ItemStack;
import net.minecraft.particles.IParticleData;
import net.minecraft.particles.ItemParticleData;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public interface PotionParticleMaker {
    default void spawnParticles(Vector3d pos, World world, ItemStack stack, int particleColor) {
        double power;
        double d27;
        double xAdd;
        double zAdd;
        double ySpeedAdd;

        for (int i = 0; i < 8; ++i) {
            world.addParticle(new ItemParticleData(ParticleTypes.ITEM, stack), pos.x, pos.y, pos.z, world.random.nextGaussian() * 0.15, world.random.nextDouble() * 0.2, world.random.nextGaussian() * 0.15);
        }

        float red = (float) (particleColor >> 16 & 255) / 255;
        float green = (float) (particleColor >> 8 & 255) / 255;
        float blue = (float) (particleColor & 255) / 255;
        IParticleData particleData = ParticleTypes.EFFECT;

        for (int j = 0; j < 100; ++j) {
            power = world.random.nextDouble() * 4;
            d27 = world.random.nextDouble() * Math.PI * 2;
            xAdd = Math.cos(d27) * power;
            ySpeedAdd = 0.01 + world.random.nextDouble() * 0.5;
            zAdd = Math.sin(d27) * power;
            Particle particle = this.addParticle(particleData, world, particleData.getType().getOverrideLimiter(), false, pos.x + xAdd * 0.1, pos.y + 0.3, pos.z + zAdd * 0.1, xAdd, ySpeedAdd, zAdd);
            if (particle != null) {
                float colorMultiplier = 0.75F + world.random.nextFloat() * 0.25F;
                particle.setColor(red * colorMultiplier, green * colorMultiplier, blue * colorMultiplier);
                particle.setPower((float) power);
            }
        }

        SoundEvent shatterSound = VSSounds.THROWN_BOTTLE_SHATTER.get();
        if (Consumable.validConsumableClass(stack.getItem())) {
            shatterSound = ((Consumable) stack.getItem()).getShatterSound(stack, shatterSound);
        }
        if (Minecraft.getInstance().level != null) Minecraft.getInstance().level.playLocalSound(pos.x + 0.5, pos.y + 0.5, pos.z + 0.5, shatterSound, SoundCategory.NEUTRAL, 1, 1, false); // Pitch: 0.9 min, 1.0 max
    }

    @Nullable
    default Particle addParticle(IParticleData particleData, World world, boolean alwaysRender, boolean minimizeLevel, double x, double y, double z, double speedX, double speedY, double speedZ) {
        Minecraft minecraft = Minecraft.getInstance();
        ActiveRenderInfo renderInfo = minecraft.gameRenderer.getMainCamera();

        if (renderInfo.isInitialized() && minecraft.particleEngine != null) {
            ParticleStatus status = this.checkParticleSettings(minecraft, world, minimizeLevel);
            if (alwaysRender) {
                return minecraft.particleEngine.createParticle(particleData, x, y, z, speedX, speedY, speedZ);
            } else if (renderInfo.getPosition().distanceToSqr(x, y, z) > 1024.0) {
                return null;
            } else {
                return status == ParticleStatus.MINIMAL ? null : minecraft.particleEngine.createParticle(particleData, x, y, z, speedX, speedY, speedZ);
            }
        } else {
            return null;
        }
    }

    default ParticleStatus checkParticleSettings(Minecraft minecraft, World world, boolean minimizeLevel) {
        ParticleStatus status = minecraft.options.particles;
        if (minimizeLevel && status == ParticleStatus.MINIMAL && world.random.nextInt(10) == 0) {
            status = ParticleStatus.DECREASED;
        }

        if (status == ParticleStatus.DECREASED && world.random.nextInt(3) == 0) {
            status = ParticleStatus.MINIMAL;
        }

        return status;
    }
}
