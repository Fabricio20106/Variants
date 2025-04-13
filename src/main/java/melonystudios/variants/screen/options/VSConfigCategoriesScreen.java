package melonystudios.variants.screen.options;

import com.mojang.blaze3d.matrix.MatrixStack;
import melonystudios.variants.screen.AbstractRVConfigScreen;
import melonystudios.variants.screen.button.OpenMenuOption;
import melonystudios.variants.util.VSUtils;
import net.minecraft.client.GameSettings;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.DialogTexts;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.client.gui.widget.list.OptionsRowList;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.fml.ModList;

public class VSConfigCategoriesScreen extends AbstractRVConfigScreen {
    public final OpenMenuOption worldGenerationSettings = new OpenMenuOption("gui.variants.config.world_generation", new VSWorldGenerationConfigScreen(this, Minecraft.getInstance().options));
    public final OpenMenuOption entitySettings = new OpenMenuOption("gui.variants.config.entities", new RVEntityConfigScreen(this, Minecraft.getInstance().options));
    public final OpenMenuOption enchantmentSettings = new OpenMenuOption("gui.variants.config.enchantments", new RVEnchantmentConfigScreen(this, Minecraft.getInstance().options));
    public final OpenMenuOption consumeBehaviorSettings = new OpenMenuOption("gui.variants.config.consume_behaviors", new RVConsumeBehaviorConfigScreen(this, Minecraft.getInstance().options));
    private OptionsRowList list;

    public VSConfigCategoriesScreen(Screen screen, GameSettings settings) {
        super(screen, settings, new TranslationTextComponent("gui.variants.config_categories.title"));
    }

    @Override
    protected void init() {
        this.list = new OptionsRowList(this.minecraft, this.width, this.height, 32, this.height - 32, 25);
        this.list.addBig(this.worldGenerationSettings);
        this.list.addBig(this.entitySettings);
        this.list.addBig(this.enchantmentSettings);
        this.list.addBig(this.consumeBehaviorSettings);
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
        super.render(stack, mouseX, mouseY, partialTicks);
        drawCenteredString(stack, this.font, this.title, this.width / 2, VSUtils.DEFAULT_TITLE_HEIGHT, 0xFFFFFF);
    }
}
