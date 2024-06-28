package com.junethewoods.variants.event.custom;

import com.junethewoods.variants.Variants;
import com.junethewoods.variants.item.custom.tool.SpyglassItem;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.GameSettings;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.settings.PointOfView;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.FOVUpdateEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderHandEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

// Copied from Caves & Cliffs Backport (by blackgear27)
@Mod.EventBusSubscriber(modid = Variants.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class SpyglassRenderEvent {
    private static final ResourceLocation SPYGLASS_SCOPE = Variants.resourceLoc("textures/misc/spyglass_scope.png");
    private static final Minecraft INSTANCE = Minecraft.getInstance();
    private static final double DEFAULT_LEVEL = 5;
    private static float SPYGLASS_SCALE;
    private static Double currentLevel;
    private static Double defaultMouseSensitivity;

    @SubscribeEvent
    public static void renderGameOverlay(RenderGameOverlayEvent event) {
        int windowWidth = event.getWindow().getGuiScaledWidth();
        int windowHeight = event.getWindow().getGuiScaledHeight();
        PlayerEntity player = INSTANCE.player;
        if (player != null) {
            if (!event.isCancelable() && event.getType() == RenderGameOverlayEvent.ElementType.HELMET) {
                float tickLength = INSTANCE.getDeltaFrameTime();
                SPYGLASS_SCALE = MathHelper.lerp(0.5F * tickLength, SPYGLASS_SCALE, 1.125F);
                if (INSTANCE.options.getCameraType().isFirstPerson()) {
                    if (SpyglassItem.isUsingSpyglass(player)) {
                        renderSpyglassOverlay(windowWidth, windowHeight);
                    } else {
                        SPYGLASS_SCALE = 0.5F;
                    }
                }
            }
        }
    }

    private static void renderSpyglassOverlay(int width, int height) {
        RenderSystem.disableDepthTest();
        RenderSystem.depthMask(false);
        RenderSystem.defaultBlendFunc();
        INSTANCE.getTextureManager().bind(SPYGLASS_SCOPE);
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder buffer = tessellator.getBuilder();

        float screenSize = (float) Math.min(width, height);
        float scaledScreenSize = Math.min((float) width / screenSize, (float) height / screenSize) * SPYGLASS_SCALE;
        float horizontalScreenSize = screenSize * scaledScreenSize;
        float verticalScreenSize = screenSize * scaledScreenSize;
        float modifiedWidth = ((float) width - horizontalScreenSize) / 2F;
        float modifiedHeight = ((float) height - verticalScreenSize) / 2F;
        float totalWidth = modifiedWidth + horizontalScreenSize;
        float totalHeight = modifiedHeight + verticalScreenSize;

        buffer.begin(7, DefaultVertexFormats.POSITION_TEX);
        buffer.vertex(modifiedWidth, totalHeight, -90).uv(0, 1).endVertex();
        buffer.vertex(totalWidth, totalHeight, -90).uv(1, 1).endVertex();
        buffer.vertex(totalWidth, modifiedHeight, -90).uv(1, 0).endVertex();
        buffer.vertex(modifiedWidth, modifiedHeight, -90).uv(0, 0).endVertex();
        tessellator.end();
        RenderSystem.disableTexture();
        buffer.begin(7, DefaultVertexFormats.POSITION_COLOR);
        buffer.vertex(0, height, -90).color(0, 0, 0, 255).endVertex();
        buffer.vertex(width, height, -90).color(0, 0, 0, 255).endVertex();
        buffer.vertex(width, totalHeight, -90).color(0, 0, 0, 255).endVertex();
        buffer.vertex(0, totalHeight, -90).color(0, 0, 0, 255).endVertex();
        buffer.vertex(0, modifiedHeight, -90).color(0, 0, 0, 255).endVertex();
        buffer.vertex(width, modifiedHeight, -90).color(0, 0, 0, 255).endVertex();
        buffer.vertex(width, 0, -90).color(0, 0, 0, 255).endVertex();
        buffer.vertex(0, 0, -90).color(0, 0, 0, 255).endVertex();
        buffer.vertex(0, totalHeight, -90).color(0, 0, 0, 255).endVertex();
        buffer.vertex(modifiedWidth, totalHeight, -90).color(0, 0, 0, 255).endVertex();
        buffer.vertex(modifiedWidth, modifiedHeight, -90).color(0, 0, 0, 255).endVertex();
        buffer.vertex(0, modifiedHeight, -90).color(0, 0, 0, 255).endVertex();
        buffer.vertex(totalWidth, totalHeight, -90).color(0, 0, 0, 255).endVertex();
        buffer.vertex(width, totalHeight, -90).color(0, 0, 0, 255).endVertex();
        buffer.vertex(width, modifiedHeight, -90).color(0, 0, 0, 255).endVertex();
        buffer.vertex(totalWidth, modifiedHeight, -90).color(0, 0, 0, 255).endVertex();
        tessellator.end();
        RenderSystem.enableTexture();
        RenderSystem.depthMask(true);
        RenderSystem.enableDepthTest();
        RenderSystem.color4f(1, 1, 1, 1);
    }

    @SubscribeEvent
    public static void onHandRender(RenderHandEvent event) {
        PlayerEntity player = Minecraft.getInstance().player;
        if (player != null && SpyglassItem.isUsingSpyglass(player)) event.setCanceled(true);
    }

    @SubscribeEvent
    public static void getFOVModifier(FOVUpdateEvent event) {
        PlayerEntity player = event.getEntity();
        float currentFov = event.getNewfov();
        if (Minecraft.getInstance().options.getCameraType() == PointOfView.FIRST_PERSON) event.setNewfov((float) changeFOV(player, currentFov));
    }

    public static double changeFOV(PlayerEntity player, double fov) {
        GameSettings options = Minecraft.getInstance().options;
        if (currentLevel == null) {
            currentLevel = DEFAULT_LEVEL;
        }

        if (!SpyglassItem.isUsingSpyglass(player)) {
            currentLevel = DEFAULT_LEVEL;
            if (defaultMouseSensitivity != null) {
                options.sensitivity = defaultMouseSensitivity;
                defaultMouseSensitivity = null;
            }

            return fov;
        } else {
            if (defaultMouseSensitivity == null) {
                defaultMouseSensitivity = options.sensitivity;
            }

            options.sensitivity = defaultMouseSensitivity * (0.1 / currentLevel / 0.1);
            return fov / currentLevel;
        }
    }
}
