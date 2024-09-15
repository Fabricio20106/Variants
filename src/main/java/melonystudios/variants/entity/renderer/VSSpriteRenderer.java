package melonystudios.variants.entity.renderer;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import melonystudios.variants.item.custom.bottle.StainedFullGlassBottleItem;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.entity.Entity;
import net.minecraft.entity.IRendersAsItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.vector.Matrix3f;
import net.minecraft.util.math.vector.Matrix4f;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nonnull;

@OnlyIn(Dist.CLIENT)
public class VSSpriteRenderer<T extends Entity & IRendersAsItem> extends EntityRenderer<T> {
    public VSSpriteRenderer(EntityRendererManager manager) {
        super(manager);
    }

    @Override
    public void render(@Nonnull T projectile, float yaw, float partialTicks, MatrixStack stack, IRenderTypeBuffer buffer, int packedLight) {
        stack.pushPose();
        stack.mulPose(this.entityRenderDispatcher.cameraOrientation());
        stack.mulPose(Vector3f.YP.rotationDegrees(180));
        stack.scale(0.75F, 0.75F, 0.75F);
        MatrixStack.Entry lastEntry = stack.last();
        Matrix4f matrix4F = lastEntry.pose();
        Matrix3f matrix3F = lastEntry.normal();
        IVertexBuilder vertexBuilder = buffer.getBuffer(RenderType.entityCutoutNoCull(getTextureFromEntity(projectile)));
        vertex(vertexBuilder, matrix4F, matrix3F, packedLight, 0, 0, 0, 1);
        vertex(vertexBuilder, matrix4F, matrix3F, packedLight, 1, 0, 1, 1);
        vertex(vertexBuilder, matrix4F, matrix3F, packedLight, 1, 1, 1, 0);
        vertex(vertexBuilder, matrix4F, matrix3F, packedLight, 0, 1, 0, 0);
        stack.popPose();
        super.render(projectile, yaw, partialTicks, stack, buffer, packedLight);
    }

    private static void vertex(IVertexBuilder builder, Matrix4f matrix4F, Matrix3f matrix3F, int packedLight, float x, int y, int z, int v) {
        builder.vertex(matrix4F, x - 0.5F, (float) y - 0.25F, 0).color(255, 255, 255, 255).uv((float) z, (float) v).overlayCoords(OverlayTexture.NO_OVERLAY)
                .uv2(packedLight).normal(matrix3F, 0, 1, 0).endVertex();
    }

    private ResourceLocation getTextureFromEntity(T projectile) {
        ItemStack displayStack = projectile.getItem();
        String namespace = displayStack.getItem().getRegistryName().getNamespace();
        if (displayStack.getItem() instanceof StainedFullGlassBottleItem) {
            StainedFullGlassBottleItem bottleItem = (StainedFullGlassBottleItem) displayStack.getItem();
            return new ResourceLocation(namespace, "textures/item/bottle/" + bottleItem.getUseRemainder(displayStack).getItem().getRegistryName().getPath() + ".png");
        }
        return new ResourceLocation(namespace, "textures/item/" + projectile.getItem().getItem().getRegistryName().getPath() + ".png");
    }

    @Override
    @Nonnull
    public ResourceLocation getTextureLocation(@Nonnull T projectile) {
        return this.getTextureFromEntity(projectile);
    }
}
