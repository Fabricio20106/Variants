package com.junethewoods.variants.entity.client;

import com.junethewoods.variants.Variants;
import com.junethewoods.variants.entity.custom.Fish;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.model.CodModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class FishRenderer extends MobRenderer<Fish, CodModel<Fish>> {
    public FishRenderer(EntityRendererProvider.Context context) {
        super(context, new CodModel<>(context.bakeLayer(VSModelLayers.FISH_LAYER)), 0.3f);
    }

    @Override
    public ResourceLocation getTextureLocation(Fish entity) {
        return Variants.resourceLoc("textures/entity/old_cod.png");
    }

    protected void setupRotations(Fish fish, PoseStack poseStack, float v, float v1, float v2) {
        super.setupRotations(fish, poseStack, v, v1, v2);
        float f = 4.3F * Mth.sin(0.6F * v);
        poseStack.mulPose(Axis.YP.rotationDegrees(f));
        if (!fish.isInWater()) {
            poseStack.translate(0.1F, 0.1F, -0.1F);
            poseStack.mulPose(Axis.ZP.rotationDegrees(90.0F));
        }
    }
}
