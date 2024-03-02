package com.junethewoods.variants.entity.renderer;

import com.junethewoods.variants.Variants;
import com.junethewoods.variants.entity.custom.SmallSoulFireballEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.vector.Matrix3f;
import net.minecraft.util.math.vector.Matrix4f;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class SmallSoulFireballRenderer extends EntityRenderer<SmallSoulFireballEntity> {
    private final RenderType CUTOUT_NO_CULL = RenderType.entityCutoutNoCull(Variants.resourceLoc("textures/item/soul_charge.png"));

    public SmallSoulFireballRenderer(EntityRendererManager manager) {
        super(manager);
    }

    public void render(SmallSoulFireballEntity soulFireball, float f, float partialTicks, MatrixStack stack, IRenderTypeBuffer buffer, int packedLight) {
        stack.pushPose();
        stack.mulPose(this.entityRenderDispatcher.cameraOrientation());
        stack.mulPose(Vector3f.YP.rotationDegrees(180));
        MatrixStack.Entry matrixstack$entry = stack.last();
        Matrix4f matrix4f = matrixstack$entry.pose();
        Matrix3f matrix3f = matrixstack$entry.normal();
        IVertexBuilder ivertexbuilder = buffer.getBuffer(CUTOUT_NO_CULL);
        vertex(ivertexbuilder, matrix4f, matrix3f, packedLight, 0, 0, 0, 1);
        vertex(ivertexbuilder, matrix4f, matrix3f, packedLight, 1, 0, 1, 1);
        vertex(ivertexbuilder, matrix4f, matrix3f, packedLight, 1, 1, 1, 0);
        vertex(ivertexbuilder, matrix4f, matrix3f, packedLight, 0, 1, 0, 0);
        stack.popPose();
        super.render(soulFireball, f, partialTicks, stack, buffer, packedLight);
    }

    private static void vertex(IVertexBuilder builder, Matrix4f matrix4F, Matrix3f matrix3F, int packedLight, float x, int y, int z, int alpha) {
        builder.vertex(matrix4F, x - 0.5F, (float)y - 0.25F, 0).color(255, 255, 255, 255).uv((float) z, (float) alpha).overlayCoords(OverlayTexture.NO_OVERLAY)
                .uv2(packedLight).normal(matrix3F, 0, 1, 0).endVertex();
    }

    @Override
    public ResourceLocation getTextureLocation(SmallSoulFireballEntity projectile) {
        return Variants.resourceLoc("textures/item/soul_charge.png");
    }
}
