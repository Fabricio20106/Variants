package melonystudios.variants.screen.options;

import com.mojang.blaze3d.matrix.MatrixStack;
import melonystudios.variants.Variants;
import melonystudios.variants.screen.AbstractRVConfigScreen;
import melonystudios.variants.screen.button.OpenMenuOption;
import melonystudios.variants.util.VSStyles;
import melonystudios.variants.util.VSUtils;
import net.minecraft.client.GameSettings;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.DialogTexts;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.client.gui.widget.list.OptionsRowList;
import net.minecraft.util.IReorderingProcessor;
import net.minecraftforge.fml.ModList;

import java.util.List;

public class RVConfigCategoriesScreen extends AbstractRVConfigScreen {
    public final OpenMenuOption itemSettings = new OpenMenuOption("menu.variants.options.items", new RVItemConfigScreen(this, Minecraft.getInstance().options));
    public final OpenMenuOption worldGenerationSettings = new OpenMenuOption("menu.variants.options.world_generation", new RVWorldGenerationConfigScreen(this, Minecraft.getInstance().options));
    public final OpenMenuOption entitySettings = new OpenMenuOption("menu.variants.options.entities", new RVEntityConfigScreen(this, Minecraft.getInstance().options));
    public final OpenMenuOption enchantmentSettings = new OpenMenuOption("menu.variants.options.enchantments", new RVEnchantmentConfigScreen(this, Minecraft.getInstance().options));
    public final OpenMenuOption consumeBehaviorSettings = new OpenMenuOption("menu.variants.options.consume_behaviors", new RVConsumeBehaviorConfigScreen(this, Minecraft.getInstance().options));
    private OptionsRowList list;

    public RVConfigCategoriesScreen(Screen screen, GameSettings settings) {
        super(screen, settings, VSStyles.buildScreenTitle("Revaried"));
    }

    @Override
    protected void init() {
        boolean mellowUILoaded = ModList.get().isLoaded("mellowui");
        this.list = new OptionsRowList(this.minecraft, this.width, this.height, 32, this.height - 32, 25);
        this.list.setRenderBackground(mellowUILoaded);
        this.list.setRenderTopAndBottom(mellowUILoaded);
        this.list.addBig(this.itemSettings);
        this.list.addBig(this.worldGenerationSettings);
        this.list.addBig(this.entitySettings);
        this.list.addBig(this.enchantmentSettings);
        this.list.addBig(this.consumeBehaviorSettings);
        this.children.add(this.list);

        // Cancel button
        this.addButton(new Button(this.width / 2 - 155, this.height - 25, 150, 20, DialogTexts.GUI_CANCEL,
                button -> this.minecraft.setScreen(this.lastScreen)));

        // Done button
        this.addButton(new Button(this.width / 2 + 5, this.height - 25, 150, 20, DialogTexts.GUI_DONE, button -> {
            this.minecraft.setScreen(this.lastScreen);
            if (SHOULD_SAVE_SETTINGS) {
                Variants.revaried().settingsManager().save();
                this.showSavedSettingsToast();
            }
        }));
    }

    @Override
    public void render(MatrixStack stack, int mouseX, int mouseY, float partialTicks) {
        super.renderPanorama(stack, partialTicks);
        this.list.render(stack, mouseX, mouseY, partialTicks);
        super.render(stack, mouseX, mouseY, partialTicks);
        drawCenteredString(stack, this.font, this.title, this.width / 2, VSUtils.DEFAULT_TITLE_HEIGHT, 0xFFFFFF);
        List<IReorderingProcessor> tooltip = tooltipAt(this.list, mouseX, mouseY);
        if (tooltip != null) this.renderTooltip(stack, tooltip, mouseX, mouseY);
    }
}
