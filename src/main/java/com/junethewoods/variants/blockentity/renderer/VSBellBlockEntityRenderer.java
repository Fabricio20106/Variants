package com.junethewoods.variants.blockentity.renderer;

import com.junethewoods.variants.Variants;
import com.junethewoods.variants.blockentity.custom.VSBellBlockEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.client.renderer.model.RenderMaterial;
import net.minecraft.client.renderer.texture.AtlasTexture;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.util.Direction;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class VSBellBlockEntityRenderer extends TileEntityRenderer<VSBellBlockEntity> {
    public static final RenderMaterial DIAMOND_BELL_RENDER_MATERIAL = new RenderMaterial(AtlasTexture.LOCATION_BLOCKS, Variants.resourceLoc("entity/bell/diamond_bell_body"));
    private final ModelRenderer bellBody = new ModelRenderer(32, 32, 0, 0);

    public VSBellBlockEntityRenderer(TileEntityRendererDispatcher renderDispatcher) {
        super(renderDispatcher);
        this.bellBody.addBox(-3, -6, -3, 6, 7, 6);
        this.bellBody.setPos(8, 12, 8);
        ModelRenderer renderer = new ModelRenderer(32, 32, 0, 13);
        renderer.addBox(4, 4, 4, 8, 2, 8);
        renderer.setPos(-8, -12, -8);
        this.bellBody.addChild(renderer);
    }

    public void render(VSBellBlockEntity bell, float yaw, MatrixStack stack, IRenderTypeBuffer buffer, int p_225616_5_, int packedLight) {
        float f = (float) bell.ticks + yaw;
        float f1 = 0;
        float f2 = 0;
        if (bell.shaking) {
            float lvt_10_1_ = MathHelper.sin(f / 3.1415927F) / (4 + f / 3);
            if (bell.clickDirection == Direction.NORTH) {
                f1 = -lvt_10_1_;
            } else if (bell.clickDirection == Direction.SOUTH) {
                f1 = lvt_10_1_;
            } else if (bell.clickDirection == Direction.EAST) {
                f2 = -lvt_10_1_;
            } else if (bell.clickDirection == Direction.WEST) {
                f2 = lvt_10_1_;
            }
        }

        this.bellBody.xRot = f1;
        this.bellBody.zRot = f2;
        IVertexBuilder vertexBuilder = DIAMOND_BELL_RENDER_MATERIAL.buffer(buffer, RenderType::entitySolid);
        this.bellBody.render(stack, vertexBuilder, p_225616_5_, packedLight);
    }
}
