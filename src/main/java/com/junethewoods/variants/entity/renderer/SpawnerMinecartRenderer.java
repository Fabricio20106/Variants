package com.junethewoods.variants.entity.renderer;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MinecartRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.minecart.SpawnerMinecartEntity;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraft.world.spawner.AbstractSpawner;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class SpawnerMinecartRenderer extends MinecartRenderer<SpawnerMinecartEntity> {
    public SpawnerMinecartRenderer(EntityRendererManager manager) {
        super(manager);
    }

    @Override
    public void render(SpawnerMinecartEntity minecart, float yaw, float partialTicks, MatrixStack stack, IRenderTypeBuffer buffer, int packedLight) {
        super.render(minecart, yaw, partialTicks, stack, buffer, packedLight);
        stack.pushPose();
        stack.translate(0, 0, 0);
        AbstractSpawner spawner = minecart.spawner;
        Entity entity = spawner.getOrCreateDisplayEntity();
        if (entity != null) {
            float heightOffset = 0.253125F;
            float maxInBB = Math.max(entity.getBbWidth(), entity.getBbHeight());
            if ((double) maxInBB > 1) heightOffset /= maxInBB;

            stack.translate(0, 0.7, 0);
            stack.mulPose(Vector3f.YP.rotationDegrees((float) MathHelper.lerp(partialTicks, spawner.getoSpin(), spawner.getoSpin()) * 10));
            stack.translate(0, -0.2, 0);
            stack.mulPose(Vector3f.XP.rotationDegrees(-30));
            stack.scale(heightOffset, heightOffset, heightOffset);
            Minecraft.getInstance().getEntityRenderDispatcher().render(entity, 0, 0, 0, 0, partialTicks, stack, buffer, packedLight);
        }
        stack.popPose();
    }
}
