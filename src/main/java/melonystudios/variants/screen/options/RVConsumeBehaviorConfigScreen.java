package melonystudios.variants.screen.options;

import com.mojang.blaze3d.matrix.MatrixStack;
import melonystudios.variants.screen.AbstractRVConfigScreen;
import melonystudios.variants.util.VSUtils;
import net.minecraft.client.GameSettings;
import net.minecraft.client.gui.DialogTexts;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.client.gui.widget.list.OptionsRowList;
import net.minecraft.util.IReorderingProcessor;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.fml.ModList;

import java.util.List;

import static melonystudios.variants.screen.VSConfigEntries.*;

public class RVConsumeBehaviorConfigScreen extends AbstractRVConfigScreen {
    private OptionsRowList list;

    public RVConsumeBehaviorConfigScreen(Screen screen, GameSettings settings) {
        super(screen, settings, new TranslationTextComponent("gui.variants.config.consume_behaviors.title"));
    }

    @Override
    protected void init() {
        this.list = new OptionsRowList(this.minecraft, this.width, this.height, 32, this.height - 32, 25);
        this.list.addBig(EXPLOSION_RADIUS_UPPER_LIMIT);
        this.list.addBig(SOUND_PITCH_UPPER_LIMIT);
        this.children.add(this.list);

        // Done button
        this.addButton(new Button(this.width / 2 - 100, this.height - 27, 200, 20, DialogTexts.GUI_DONE,
                button -> this.minecraft.setScreen(this.lastScreen)));
    }

    @Override
    public void render(MatrixStack stack, int mouseX, int mouseY, float partialTicks) {
        super.renderPanorama(stack, partialTicks);
        if (!ModList.get().isLoaded("mellowui")) {
            this.list.setRenderBackground(false);
            this.list.setRenderTopAndBottom(false);
        }

        this.list.render(stack, mouseX, mouseY, partialTicks);
        drawCenteredString(stack, this.font, this.title, this.width / 2, VSUtils.DEFAULT_TITLE_HEIGHT, 0xFFFFFF);
        super.render(stack, mouseX, mouseY, partialTicks);
        List<IReorderingProcessor> processors = tooltipAt(this.list, mouseX, mouseY);
        if (processors != null) this.renderTooltip(stack, processors, mouseX, mouseY);
    }
}
