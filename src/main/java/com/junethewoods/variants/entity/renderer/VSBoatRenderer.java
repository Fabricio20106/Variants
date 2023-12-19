package com.junethewoods.variants.entity.renderer;

import com.junethewoods.variants.Variants;
import com.junethewoods.variants.entity.custom.VSBoatEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.BoatRenderer;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.model.BoatModel;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.entity.item.BoatEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Quaternion;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class VSBoatRenderer extends EntityRenderer<VSBoatEntity> {
    private static final ResourceLocation[] BOAT_TEXTURES = new ResourceLocation[] {Variants.resourceLoc("textures/entity/boat/painting.png"),Variants.resourceLoc("textures/entity/boat/crimson.png"),
            Variants.resourceLoc("textures/entity/boat/warped.png"), Variants.resourceLoc("textures/entity/boat/ender.png")};
    protected final BoatModel model = new BoatModel();

    public VSBoatRenderer(EntityRendererManager manager) {
        super(manager);
        this.shadowRadius = 0.8F;
    }

    public void render(VSBoatEntity boat, float v, float v1, MatrixStack stack, IRenderTypeBuffer buffer, int i) {
        stack.pushPose();
        stack.translate(0, 0.375D, 0);
        stack.mulPose(Vector3f.YP.rotationDegrees(180 - v));
        float hurtTime = (float) boat.getHurtTime() - v1;
        float damage = boat.getDamage() - v1;
        if (damage < 0) {
            damage = 0;
        }

        if (hurtTime > 0) {
            stack.mulPose(Vector3f.XP.rotationDegrees(MathHelper.sin(hurtTime) * hurtTime * damage / 10 * (float) boat.getHurtDir()));
        }

        float bubbleAngle = boat.getBubbleAngle(v1);
        if (!MathHelper.equal(bubbleAngle, 0)) {
            stack.mulPose(new Quaternion(new Vector3f(1, 0, 1), boat.getBubbleAngle(v1), true));
        }

        stack.scale(-1, -1, 1);
        stack.mulPose(Vector3f.YP.rotationDegrees(90));
        this.model.setupAnim(boat, v1, 0, -0.1F, 0, 0);
        IVertexBuilder vertexBuilder = buffer.getBuffer(this.model.renderType(this.getTextureLocation(boat)));
        this.model.renderToBuffer(stack, vertexBuilder, i, OverlayTexture.NO_OVERLAY, 1, 1, 1, 1);
        if (!boat.isUnderWater()) {
            IVertexBuilder vertexBuilder1 = buffer.getBuffer(RenderType.waterMask());
            this.model.waterPatch().render(stack, vertexBuilder1, i, OverlayTexture.NO_OVERLAY);
        }

        stack.popPose();
        super.render(boat, v, v1, stack, buffer, i);
    }

    @Override
    public ResourceLocation getTextureLocation(VSBoatEntity entity) {
        switch (entity.getWoodType()) {
            case "painting":
            default:
                return BOAT_TEXTURES[0];
            case "crimson":
                return BOAT_TEXTURES[1];
            case "warped":
                return BOAT_TEXTURES[2];
            case "ender":
                return BOAT_TEXTURES[3];
        }
    }
}
