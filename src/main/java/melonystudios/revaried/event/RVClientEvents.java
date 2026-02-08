package melonystudios.revaried.event;

import melonystudios.revaried.Revaried;
import melonystudios.revaried.entity.renderer.SpawnerMinecartRenderer;
import melonystudios.revaried.fluid.RVFluidTypes;
import melonystudios.revaried.fluid.RVFluids;
import melonystudios.revaried.fluid.custom.ExtendedFluidType;
import melonystudios.revaried.misc.particle.*;
import net.minecraft.client.model.MinecartModel;
import net.minecraft.client.particle.DripParticle;
import net.minecraft.client.particle.LavaParticle;
import net.minecraft.world.entity.EntityType;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.RegisterParticleProvidersEvent;
import net.neoforged.neoforge.client.extensions.common.RegisterClientExtensionsEvent;

@EventBusSubscriber(modid = Revaried.MOD_ID, value = Dist.CLIENT)
public class RVClientEvents {
    @SubscribeEvent
    public static void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(EntityType.SPAWNER_MINECART, SpawnerMinecartRenderer::new);
    }

    @SubscribeEvent
    public static void registerModelLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(SpawnerMinecartRenderer.SPAWNER_MINECART, MinecartModel::createBodyLayer);
    }

    @SubscribeEvent
    public static void registerFluidExtensions(RegisterClientExtensionsEvent event) {
        event.registerFluidType(((ExtendedFluidType) RVFluidTypes.MUSHROOM_STEW.get()).extension, RVFluidTypes.MUSHROOM_STEW.get());
        event.registerFluidType(((ExtendedFluidType) RVFluidTypes.SOUL_LAVA.get()).extension, RVFluidTypes.SOUL_LAVA.get());
    }

    @SubscribeEvent
    public static void registerParticleProviders(RegisterParticleProvidersEvent event) {
        event.registerSpriteSet(RVParticleTypes.SOUL_LAVA.get(), LavaParticle.Provider::new);
        event.registerSprite(RVParticleTypes.DRIPPING_SOUL_LAVA.get(), (type, world, x, y, z, xSpeed, ySpeed, zSpeed) ->
                new CoolingDripHangParticle(world, x, y, z, RVFluids.SOUL_LAVA.get(), RVParticleTypes.FALLING_SOUL_LAVA.get()));
        event.registerSprite(RVParticleTypes.FALLING_SOUL_LAVA.get(), (type, world, x, y, z, xSpeed, ySpeed, zSpeed) -> {
            DripParticle particle = new FallAndLandParticle(world, x, y, z, RVFluids.SOUL_LAVA.get(), RVParticleTypes.LANDING_SOUL_LAVA.get());
            particle.setColor(48 / 255F, 233 / 255F, 219 / 255F);
            return particle;
        });
        event.registerSprite(RVParticleTypes.LANDING_SOUL_LAVA.get(), (type, world, x, y, z, xSpeed, ySpeed, zSpeed) -> {
            DripParticle particle = new DripLandParticle(world, x, y, z, RVFluids.SOUL_LAVA.get());
            particle.setColor(48 / 255F, 233 / 255F, 219 / 255F);
            return particle;
        });
        event.registerSprite(RVParticleTypes.DRIPPING_DRIPSTONE_SOUL_LAVA.get(), (type, world, x, y, z, xSpeed, ySpeed, zSpeed) ->
                new CoolingDripHangParticle(world, x, y, z, RVFluids.SOUL_LAVA.get(), RVParticleTypes.FALLING_DRIPSTONE_SOUL_LAVA.get()));
        event.registerSprite(RVParticleTypes.FALLING_DRIPSTONE_SOUL_LAVA.get(), (type, world, x, y, z, xSpeed, ySpeed, zSpeed) -> {
            DripParticle particle = new DripstoneFallAndLandParticle(world, x, y, z, RVFluids.SOUL_LAVA.get(), RVParticleTypes.LANDING_SOUL_LAVA.get());
            particle.setColor(48 / 255F, 233 / 255F, 219 / 255F);
            return particle;
        });
    }
}
