package melonystudios.variants.screen;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.GameSettings;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.SettingsScreen;
import net.minecraft.client.renderer.RenderSkybox;
import net.minecraft.client.renderer.RenderSkyboxCube;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public abstract class AbstractVSConfigScreen extends SettingsScreen {
    public static final RenderSkyboxCube CUBE_MAP = new RenderSkyboxCube(new ResourceLocation("textures/gui/title/background/panorama"));
    private final RenderSkybox panorama = new RenderSkybox(CUBE_MAP);

    public AbstractVSConfigScreen(Screen screen, GameSettings settings, ITextComponent screenName) {
        super(screen, settings, screenName);
    }

    @Override
    public void render(MatrixStack stack, int width, int height, float partialTicks) {
        if (this.minecraft != null && this.minecraft.level == null) this.panorama.render(partialTicks, MathHelper.clamp(1, 0, 1));
        super.render(stack, width, height, partialTicks);
    }
}
