package com.junethewoods.variants.blockentity.renderer;

import com.junethewoods.variants.Variants;
import com.junethewoods.variants.blockentity.VSBlockEntities;
import com.junethewoods.variants.blockentity.custom.VSBedBlockEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import it.unimi.dsi.fastutil.ints.Int2IntFunction;
import net.minecraft.block.BedBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.ChestBlock;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.client.renderer.model.RenderMaterial;
import net.minecraft.client.renderer.tileentity.DualBrightnessCallback;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.state.properties.BedPart;
import net.minecraft.tileentity.TileEntityMerger;
import net.minecraft.util.Direction;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import static net.minecraft.client.renderer.Atlases.BED_SHEET;

@OnlyIn(Dist.CLIENT)
public class GlowBlackBedBlockEntityRenderer extends TileEntityRenderer<VSBedBlockEntity> {
    private final ModelRenderer headPiece = new ModelRenderer(64, 64, 0, 0);
    private final ModelRenderer footPiece;
    private final ModelRenderer[] legs = new ModelRenderer[4];

    public GlowBlackBedBlockEntityRenderer(TileEntityRendererDispatcher dispatcher) {
        super(dispatcher);
        this.headPiece.addBox(0, 0, 0, 16, 16, 6, 0);
        this.footPiece = new ModelRenderer(64, 64, 0, 22);
        this.footPiece.addBox(0, 0, 0, 16, 16, 6, 0);
        this.legs[0] = new ModelRenderer(64, 64, 50, 0);
        this.legs[1] = new ModelRenderer(64, 64, 50, 6);
        this.legs[2] = new ModelRenderer(64, 64, 50, 12);
        this.legs[3] = new ModelRenderer(64, 64, 50, 18);
        this.legs[0].addBox(0, 6, -16, 3, 3, 3);
        this.legs[1].addBox(0, 6, 0, 3, 3, 3);
        this.legs[2].addBox(-16, 6, -16, 3, 3, 3);
        this.legs[3].addBox(-16, 6, 0, 3, 3, 3);
        this.legs[0].xRot = 1.5707964F;
        this.legs[1].xRot = 1.5707964F;
        this.legs[2].xRot = 1.5707964F;
        this.legs[3].xRot = 1.5707964F;
        this.legs[0].zRot = 0;
        this.legs[1].zRot = 1.5707964F;
        this.legs[2].zRot = 4.712389F;
        this.legs[3].zRot = 3.1415927F;
    }

    public void render(VSBedBlockEntity bed, float flo, MatrixStack matrixStack, IRenderTypeBuffer renderBuffer, int i1, int i2) {
        RenderMaterial glowBlackBedMaterial = new RenderMaterial(BED_SHEET, Variants.resourceLoc("entity/bed/glow_black"));
        World world = bed.getLevel();
        if (world != null) {
            BlockState bedState = bed.getBlockState();
            TileEntityMerger.ICallbackWrapper<VSBedBlockEntity> bedCallbackWrapper = TileEntityMerger.combineWithNeigbour(VSBlockEntities.VS_BED.get(), BedBlock::getBlockType, BedBlock::getConnectedDirection, ChestBlock.FACING, bedState, world, bed.getBlockPos(), (iWorld, pos) -> false);
            int lvt_11_1_ = ((Int2IntFunction) bedCallbackWrapper.apply(new DualBrightnessCallback())).get(i1);
            this.renderPiece(matrixStack, renderBuffer, bedState.getValue(BedBlock.PART) == BedPart.HEAD, bedState.getValue(BedBlock.FACING), glowBlackBedMaterial, lvt_11_1_, i2, false);
        } else {
            this.renderPiece(matrixStack, renderBuffer, true, Direction.SOUTH, glowBlackBedMaterial, i1, i2, false);
            this.renderPiece(matrixStack, renderBuffer, false, Direction.SOUTH, glowBlackBedMaterial, i1, i2, true);
        }
    }

    private void renderPiece(MatrixStack matrixStack, IRenderTypeBuffer renderBuffer, boolean bool, Direction direction, RenderMaterial material, int i1, int i2, boolean bool1) {
        this.headPiece.visible = bool;
        this.footPiece.visible = !bool;
        this.legs[0].visible = !bool;
        this.legs[1].visible = bool;
        this.legs[2].visible = !bool;
        this.legs[3].visible = bool;
        matrixStack.pushPose();
        matrixStack.translate(0, 0.5625, bool1 ? -1 : 0);
        matrixStack.mulPose(Vector3f.XP.rotationDegrees(90));
        matrixStack.translate(0.5, 0.5, 0.5);
        matrixStack.mulPose(Vector3f.ZP.rotationDegrees(180 + direction.toYRot()));
        matrixStack.translate(-0.5, -0.5, -0.5);
        IVertexBuilder vertexBuilder = material.buffer(renderBuffer, RenderType::entitySolid);
        this.headPiece.render(matrixStack, vertexBuilder, i1, i2);
        this.footPiece.render(matrixStack, vertexBuilder, i1, i2);
        this.legs[0].render(matrixStack, vertexBuilder, i1, i2);
        this.legs[1].render(matrixStack, vertexBuilder, i1, i2);
        this.legs[2].render(matrixStack, vertexBuilder, i1, i2);
        this.legs[3].render(matrixStack, vertexBuilder, i1, i2);
        matrixStack.popPose();
    }
}
