package melonystudios.variants.screen.options;

import com.mojang.blaze3d.matrix.MatrixStack;
import melonystudios.variants.screen.AbstractVSConfigScreen;
import net.minecraft.client.GameSettings;
import net.minecraft.client.gui.DialogTexts;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.util.text.TranslationTextComponent;

public class VSConfigCategoriesScreen extends AbstractVSConfigScreen {
    public VSConfigCategoriesScreen(Screen screen, GameSettings settings) {
        super(screen, settings, new TranslationTextComponent("gui.variants.config_categories.title"));
    }

    @Override
    protected void init() {
        this.addButton(new Button(this.width / 2 - 155, this.height / 6 + 48 - 6, 150, 20,
                new TranslationTextComponent("gui.variants.config.world_generation"), button -> this.minecraft.setScreen(new VSWorldGenerationConfigScreen(this, this.options))));
        this.addButton(new Button(this.width / 2 - 100, this.height / 6 + 168, 200, 20,
                DialogTexts.GUI_DONE, button -> this.minecraft.setScreen(this.lastScreen)));
    }

    @Override
    public void render(MatrixStack stack, int width, int height, float partialTicks) {
        super.render(stack, width, height, partialTicks);
        drawCenteredString(stack, this.font, this.title, this.width / 2, 15, 16777215);
    }
}
