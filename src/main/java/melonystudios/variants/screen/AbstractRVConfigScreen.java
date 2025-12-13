package melonystudios.variants.screen;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import melonystudios.variants.util.VSUtils;
import net.minecraft.client.GameSettings;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.SettingsScreen;
import net.minecraft.client.gui.toasts.SystemToast;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.*;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.ModList;

@OnlyIn(Dist.CLIENT)
public abstract class AbstractRVConfigScreen extends SettingsScreen {
    private static final ResourceLocation PANORAMA_OVERLAY = new ResourceLocation("textures/gui/title/background/panorama_overlay.png");
    public static boolean SHOULD_SAVE_SETTINGS = false;

    public AbstractRVConfigScreen(Screen screen, GameSettings settings, ITextComponent screenName) {
        super(screen, settings, screenName);
    }

    public void renderPanorama(MatrixStack stack, float partialTicks) {
        if (this.minecraft != null && this.minecraft.level == null && !ModList.get().isLoaded("mellowui")) {
            VSUtils.PANORAMA.render(partialTicks, 1);
            this.minecraft.getTextureManager().bind(PANORAMA_OVERLAY);
            RenderSystem.enableBlend();
            RenderSystem.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
            RenderSystem.color4f(1, 1, 1, 1);
            blit(stack, 0, 0, this.width, this.height, 0, 0, 16, 128, 16, 128);
        } else {
            super.renderBackground(stack);
        }
    }

    public void showSavedSettingsToast() {
        this.minecraft.getToasts().addToast(SystemToast.multiline(this.minecraft, SystemToast.Type.TUTORIAL_HINT, new TranslationTextComponent("menu.variants.options.saved_settings"), new TranslationTextComponent("menu.variants.options.saved_settings.desc")));
    }
}
