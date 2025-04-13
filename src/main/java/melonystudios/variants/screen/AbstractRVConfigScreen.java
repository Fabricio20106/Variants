package melonystudios.variants.screen;

import com.mojang.blaze3d.matrix.MatrixStack;
import melonystudios.variants.util.VSUtils;
import net.minecraft.client.GameSettings;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.SettingsScreen;
import net.minecraft.util.text.*;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.ModList;

@OnlyIn(Dist.CLIENT)
public abstract class AbstractRVConfigScreen extends SettingsScreen {
    public AbstractRVConfigScreen(Screen screen, GameSettings settings, ITextComponent screenName) {
        super(screen, settings, screenName);
    }

    public void renderPanorama(MatrixStack stack, float partialTicks) {
        if (this.minecraft != null && this.minecraft.level == null && !ModList.get().isLoaded("mellowui")) {
            VSUtils.PANORAMA.render(partialTicks, 1);
        } else {
            super.renderBackground(stack);
        }
    }
}
