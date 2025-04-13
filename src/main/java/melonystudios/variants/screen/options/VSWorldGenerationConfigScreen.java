package melonystudios.variants.screen.options;

import com.google.common.collect.Lists;
import com.mojang.blaze3d.matrix.MatrixStack;
import melonystudios.variants.Variants;
import melonystudios.variants.screen.AbstractRVConfigScreen;
import melonystudios.variants.util.VSUtils;
import net.minecraft.client.AbstractOption;
import net.minecraft.client.GameSettings;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.DialogTexts;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.toasts.SystemToast;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.client.gui.widget.Widget;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.client.gui.widget.list.OptionsRowList;
import net.minecraft.util.IReorderingProcessor;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.List;

import static melonystudios.variants.screen.VSConfigEntries.*;
import static melonystudios.variants.screen.VSConfigEntries.END_CAVES_AND_RAVINES;

public class VSWorldGenerationConfigScreen extends AbstractRVConfigScreen {
    public static List<AbstractOption> SETTINGS = Lists.newArrayList(PAINTINGWOOD_FOREST, AZURE_FIELDS, FLOWER_PATCHES, CRIMSON_WHEAT_PATCHES, SOUL_CARROT_PATCHES, WARPED_POTATO_PATCHES,
            MELTING_BEET_PATCHES, GENERATE_QUARTZ_ORE, GENERATE_END_QUARTZ_ORE, GENERATE_NETHER_COAL_ORE, GENERATE_CRYSTALLIZED_MAGMA_CREAM_ORE, SOUL_LAVA_SPRINGS, END_CAVES_AND_RAVINES);
    private final AbstractOption[] smallOptions;
    private OptionsRowList list;
    private TextFieldWidget endSubstitutionBox;
    private Widget doneButton;

    public VSWorldGenerationConfigScreen(Screen screen, GameSettings settings) {
        super(screen, settings, new TranslationTextComponent("gui.variants.config.world_generation.title"));
        this.smallOptions = SETTINGS.toArray(new AbstractOption[0]);
    }

    @Override
    public void tick() {
        this.endSubstitutionBox.tick();
    }

    @Override
    protected void init() {
        this.endSubstitutionBox = new TextFieldWidget(this.font, this.width / 2 - 155, this.height - 27, 150, 20, new TranslationTextComponent("config.variants.substitute_the_end_biome_with"));
        this.endSubstitutionBox.setMaxLength(128);
        this.endSubstitutionBox.setFocus(false);
        this.endSubstitutionBox.setCanLoseFocus(true);
        this.endSubstitutionBox.setValue(CONFIG.substituteTheEndBiomeWith.toString());
        this.endSubstitutionBox.setResponder(this::validateBiomeEntry);
        this.children.add(this.endSubstitutionBox);

        this.list = new OptionsRowList(this.minecraft, this.width, this.height, 32, this.height - 32, 25);
        this.list.addSmall(this.smallOptions);
        this.children.add(this.list);
        this.doneButton = this.addButton(new Button(this.width / 2 + 5, this.height - 27, 150, 20, DialogTexts.GUI_DONE, button -> {
            this.minecraft.setScreen(this.lastScreen);
            Variants.INSTANCE.saveConfig();
            SystemToast.multiline(this.minecraft, SystemToast.Type.TUTORIAL_HINT, new TranslationTextComponent("gui.variants.config.saved_settings"), new TranslationTextComponent("gui.variants.config.saved_settings.desc"));
        }));
    }

    private void validateBiomeEntry(String value) {
        boolean valid = ForgeRegistries.BIOMES.containsKey(new ResourceLocation(value));
        this.doneButton.active = valid;
        if (valid) {
            Variants.INSTANCE.getConfig().substituteTheEndBiomeWith = new ResourceLocation(value);
            Variants.INSTANCE.saveConfig();
        }
    }

    @Override
    public void resize(Minecraft minecraft, int width, int height) {
        super.resize(minecraft, width, height);
        String value = this.endSubstitutionBox.getValue();
        this.endSubstitutionBox.setValue(value);
    }

    @Override
    public void render(MatrixStack stack, int mouseX, int mouseY, float partialTicks) {
        super.renderPanorama(stack, partialTicks);
        if (!ModList.get().isLoaded("mellowui")) {
            this.list.setRenderBackground(false);
            this.list.setRenderTopAndBottom(false);
        }

        this.list.render(stack, mouseX, mouseY, partialTicks);
        this.endSubstitutionBox.render(stack, mouseX, mouseY, partialTicks);
        super.render(stack, mouseX, mouseY, partialTicks);
        drawString(stack, this.font, new TranslationTextComponent("config.variants.substitute_the_end_biome_with"), this.width / 2 - 156, this.height - 37, 0xA0A0A0);
        drawCenteredString(stack, this.font, this.title, this.width / 2, VSUtils.DEFAULT_TITLE_HEIGHT, 0xFFFFFF);
        List<IReorderingProcessor> processors = tooltipAt(this.list, mouseX, mouseY);
        if (processors != null) this.renderTooltip(stack, processors, mouseX, mouseY);
    }
}
