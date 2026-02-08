package melonystudios.revaried.entity.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import melonystudios.revaried.Revaried;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MinecartRenderer;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.vehicle.MinecartSpawner;
import net.minecraft.world.level.BaseSpawner;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class SpawnerMinecartRenderer extends MinecartRenderer<MinecartSpawner> {
    public static final ModelLayerLocation SPAWNER_MINECART = new ModelLayerLocation(Revaried.revaried("spawner_minecart"), "main");

    public SpawnerMinecartRenderer(EntityRendererProvider.Context context) {
        super(context, SPAWNER_MINECART);
    }

    @Override
    public void render(MinecartSpawner minecart, float yaw, float partialTicks, PoseStack stack, MultiBufferSource buffer, int packedLight) {
        super.render(minecart, yaw, partialTicks, stack, buffer, packedLight);
        BaseSpawner spawner = minecart.getSpawner();
        Entity entity = spawner.getOrCreateDisplayEntity(minecart.level(), minecart.blockPosition());
        if (entity != null) {
            renderEntityInSpawner(stack, buffer, this.entityRenderDispatcher, entity, partialTicks, packedLight, spawner.getoSpin(), spawner.getSpin());
        }
    }

    public static void renderEntityInSpawner(PoseStack stack, MultiBufferSource buffer, EntityRenderDispatcher entityRenderer, Entity entity, float partialTicks, int packedLight, double oldSpin, double spin) {
        stack.pushPose();
        stack.translate(0, 0, 0);
        float scale = 0.253125F;
        float maxInBB = Math.max(entity.getBbWidth(), entity.getBbHeight());
        if (maxInBB > 1F) scale /= maxInBB;

        stack.translate(0, 0.7, 0);
        stack.mulPose(Axis.YP.rotationDegrees((float) Mth.lerp(partialTicks, oldSpin, spin) * 10));
        stack.translate(0, 0.2, 0);
        stack.mulPose(Axis.XP.rotationDegrees(-30));
        stack.scale(scale, scale, scale);
        entityRenderer.render(entity, 0, 0, 0, 0, partialTicks, stack, buffer, packedLight);
        stack.popPose();
    }
}
