package com.junethewoods.variants.blockentity.renderer;

/*@OnlyIn(Dist.CLIENT)
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

    public void render(VSBellBlockEntity bell, float p_225616_2_, MatrixStack stack, IRenderTypeBuffer buffer, int p_225616_5_, int p_225616_6_) {
        float lvt_7_1_ = (float) bell.ticks + p_225616_2_;
        float lvt_8_1_ = 0;
        float lvt_9_1_ = 0;
        if (bell.shaking) {
            float lvt_10_1_ = MathHelper.sin(lvt_7_1_ / 3.1415927F) / (4 + lvt_7_1_ / 3);
            if (bell.clickDirection == Direction.NORTH) {
                lvt_8_1_ = -lvt_10_1_;
            } else if (bell.clickDirection == Direction.SOUTH) {
                lvt_8_1_ = lvt_10_1_;
            } else if (bell.clickDirection == Direction.EAST) {
                lvt_9_1_ = -lvt_10_1_;
            } else if (bell.clickDirection == Direction.WEST) {
                lvt_9_1_ = lvt_10_1_;
            }
        }

        this.bellBody.xRot = lvt_8_1_;
        this.bellBody.zRot = lvt_9_1_;
        IVertexBuilder vertexBuilder = DIAMOND_BELL_RENDER_MATERIAL.buffer(buffer, RenderType::entitySolid);
        this.bellBody.render(stack, vertexBuilder, p_225616_5_, p_225616_6_);
    }
}*/
