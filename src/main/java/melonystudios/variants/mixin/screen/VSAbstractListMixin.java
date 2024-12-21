package melonystudios.variants.mixin.screen;

// @Mixin(AbstractList.class)
public abstract class VSAbstractListMixin {
    /*private static final RenderSkyboxCube CUBE_MAP = new RenderSkyboxCube(new ResourceLocation("textures/gui/title/background/panorama"));
    private static final RenderSkybox PANORAMA = new RenderSkybox(CUBE_MAP);

    @Shadow
    private boolean renderBackground;
    @Shadow
    private boolean renderTopAndBottom;

    @Shadow protected int x0;

    @Shadow protected int y0;

    @Shadow protected int y1;

    @Shadow protected int x1;

    @Shadow protected int width;

    @Shadow protected int height;

    @Inject(method = "render", at = @At("HEAD"))
    public void render(MatrixStack stack, int width, int height, float partialTicks, CallbackInfo ci) {
        this.renderBackground = false;
        this.renderTopAndBottom = false;
        Minecraft minecraft = Minecraft.getInstance();
        if (minecraft.level == null) PANORAMA.render(partialTicks, MathHelper.clamp(1, 0, 1));

        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder buffer = tessellator.getBuilder();

        RenderSystem.enableDepthTest();
        RenderSystem.depthFunc(515);
//        RenderSystem.depthFunc(519);
        buffer.begin(7, DefaultVertexFormats.POSITION_TEX_COLOR);
        buffer.vertex(this.x0,  this.y0, -100).uv(0, (float) this.y0 / 32).color(64, 64, 64, 255).endVertex();
        buffer.vertex((this.x0 + this.width),  this.y0, -100).uv((float) this.width / 32, (float) this.y0 / 32).color(64, 64, 64, 255).endVertex();
        buffer.vertex((this.x0 + this.width), 0, -100).uv((float) this.width / 32, 0).color(64, 64, 64, 255).endVertex();
        buffer.vertex(this.x0, 0, -100).uv(0, 0).color(64, 64, 64, 255).endVertex();
        buffer.vertex(this.x0, this.height, -100).uv(0, (float) this.height / 32).color(64, 64, 64, 255).endVertex();
        buffer.vertex(this.x0 + this.width, this.height, -100).uv((float) this.width / 32, (float) this.height / 32).color(64, 64, 64, 255).endVertex();
        buffer.vertex(this.x0 + this.width, this.y1, -100).uv((float) this.width / 32, (float) this.y1 / 32).color(64, 64, 64, 255).endVertex();
        buffer.vertex(this.x0, this.y1, -100).uv(0, (float) this.y1 / 32).color(64, 64, 64, 255).endVertex();
        tessellator.end();
        RenderSystem.disableDepthTest();
        RenderSystem.enableBlend();
        RenderSystem.blendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ZERO, GlStateManager.DestFactor.ONE);
        RenderSystem.disableTexture();
        buffer.begin(7, DefaultVertexFormats.POSITION_TEX_COLOR);
        buffer.vertex(this.x0, (this.y0 + 4), 0).uv(0, 1).color(0, 0, 0, 0).endVertex();
        buffer.vertex(this.x1, (this.y0 + 4), 0).uv(1, 1).color(0, 0, 0, 0).endVertex();
        buffer.vertex(this.x1, this.y0, 0).uv(1, 0).color(0, 0, 0, 255).endVertex();
        buffer.vertex(this.x0, this.y0, 0).uv(0, 0).color(0, 0, 0, 255).endVertex();
        buffer.vertex(this.x0, this.y1, 0).uv(0, 1).color(0, 0, 0, 255).endVertex();
        buffer.vertex(this.x1, this.y1, 0).uv(1, 1).color(0, 0, 0, 255).endVertex();
        buffer.vertex(this.x1, (this.y1 - 4), 0).uv(1, 0).color(0, 0, 0, 0).endVertex();
        buffer.vertex(this.x0, (this.y1 - 4), 0).uv(0, 0).color(0, 0, 0, 0).endVertex();
        tessellator.end();
    }*/
}
