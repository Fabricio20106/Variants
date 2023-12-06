package com.junethewoods.variants.blockentity.renderer;

import com.junethewoods.variants.Variants;
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
import net.minecraft.tileentity.BedTileEntity;
import net.minecraft.tileentity.TileEntityMerger;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraft.world.World;

import static net.minecraft.client.renderer.Atlases.BED_SHEET;

@SuppressWarnings("unchecked")
public class VSBedBlockEntityRenderer extends TileEntityRenderer<VSBedBlockEntity> {
    private final ModelRenderer headPiece = new ModelRenderer(64, 64, 0, 0);
    private final ModelRenderer footPiece = new ModelRenderer(64, 64, 0, 22);
    private final ModelRenderer[] legs = new ModelRenderer[4];

    public VSBedBlockEntityRenderer(TileEntityRendererDispatcher dispatcher) {
        super(dispatcher);
        this.headPiece.addBox(0, 0, 0, 16, 16, 6, 0);
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

    public void render(VSBedBlockEntity bed, float partialTicks, MatrixStack matrixStack, IRenderTypeBuffer buffer, int combinedLight, int combinedOverlay) {
        RenderMaterial glowBlackBedMaterial = new RenderMaterial(BED_SHEET, Variants.resourceLoc("entity/bed/glow_black"));
        World world = bed.getLevel();
        if (world != null) {
            BlockState bedState = bed.getBlockState();
            TileEntityMerger.ICallbackWrapper<? extends BedTileEntity> bedCallbackWrapper = TileEntityMerger.combineWithNeigbour(TileEntityType.BED, BedBlock::getBlockType, BedBlock::getConnectedDirection, ChestBlock.FACING, bedState, world, bed.getBlockPos(), (iWorld, pos) -> false);
            int i = ((Int2IntFunction) bedCallbackWrapper.apply(new DualBrightnessCallback())).get(combinedLight);
            this.renderPiece(matrixStack, buffer, bedState.getValue(BedBlock.PART) == BedPart.HEAD, bedState.getValue(BedBlock.FACING), glowBlackBedMaterial, i, combinedOverlay, false);
        } else {
            this.renderPiece(matrixStack, buffer, true, Direction.SOUTH, glowBlackBedMaterial, combinedLight, combinedOverlay, false);
            this.renderPiece(matrixStack, buffer, false, Direction.SOUTH, glowBlackBedMaterial, combinedLight, combinedOverlay, true);
        }
    }

    private void renderPiece(MatrixStack matrixStack, IRenderTypeBuffer renderBuffer, boolean isBedHead, Direction direction, RenderMaterial material, int i1, int i2, boolean bool) {
        this.headPiece.visible = isBedHead;
        this.footPiece.visible = !isBedHead;
        this.legs[0].visible = !isBedHead;
        this.legs[1].visible = isBedHead;
        this.legs[2].visible = !isBedHead;
        this.legs[3].visible = isBedHead;
        matrixStack.pushPose();
        matrixStack.translate(0, 0.5625, bool ? -1 : 0);
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
