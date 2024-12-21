package melonystudios.variants.screen.options;

import com.google.common.collect.Lists;
import com.mojang.blaze3d.matrix.MatrixStack;
import melonystudios.variants.Variants;
import melonystudios.variants.screen.AbstractVSConfigScreen;
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
import net.minecraftforge.registries.ForgeRegistries;

import java.util.List;

import static melonystudios.variants.screen.VSConfigEntries.*;
import static melonystudios.variants.screen.VSConfigEntries.END_CAVES_AND_RAVINES;

public class VSWorldGenerationConfigScreen extends AbstractVSConfigScreen {
    public static List<AbstractOption> CONFIG_ENTRIES = Lists.newArrayList(FLOWER_PATCHES, CRIMSON_WHEAT_PATCHES, SOUL_CARROT_PATCHES, WARPED_POTATO_PATCHES, MELTING_BEET_PATCHES, GENERATE_QUARTZ_ORE, GENERATE_END_QUARTZ_ORE, SOUL_LAVA_SPRINGS, END_CAVES_AND_RAVINES);
    private final AbstractOption[] smallOptions;
    private OptionsRowList list;
    private TextFieldWidget endSubstitutionBox;
    private Widget doneButton;

    public VSWorldGenerationConfigScreen(Screen screen, GameSettings settings) {
        super(screen, settings, new TranslationTextComponent("gui.variants.config.world_generation.title"));
        this.smallOptions = CONFIG_ENTRIES.toArray(new AbstractOption[0]);
    }

    @Override
    public void tick() {
        this.endSubstitutionBox.tick();
    }

    @Override
    protected void init() {
        this.endSubstitutionBox = new TextFieldWidget(this.font, this.width / 2 - 152, 20, 300, 20, new TranslationTextComponent("config.variants.substitute_the_end_biome_with"));
        this.endSubstitutionBox.setMaxLength(128);
        this.endSubstitutionBox.setFocus(false);
        this.endSubstitutionBox.setCanLoseFocus(true);
        this.endSubstitutionBox.setValue(CONFIG.substituteTheEndBiomeWith.toString());
        this.endSubstitutionBox.setResponder(this::validateBiomeEntry);
        this.children.add(this.endSubstitutionBox);

        this.list = new OptionsRowList(this.minecraft, this.width, this.height, 32, this.height - 32, 25);
        this.list.addSmall(this.smallOptions);
        this.children.add(this.list);
        this.doneButton = this.addButton(new Button(this.width / 2 - 100, this.height - 27, 200, 20, DialogTexts.GUI_DONE, button -> {
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
    public void render(MatrixStack stack, int width, int height, float partialTicks) {
        super.render(stack, width, height, partialTicks);
        this.list.setRenderBackground(false);
        this.list.setRenderTopAndBottom(false);
        this.list.render(stack, width, height, partialTicks);
        drawCenteredString(stack, this.font, this.title, this.width / 2, 15, 16777215);
        List<IReorderingProcessor> processors = tooltipAt(this.list, width, height);
        if (processors != null) this.renderTooltip(stack, processors, width, height);
    }

    //        this.renderBackground(stack);
    //        if (this.minecraft.level != null) {
    //            this.fillGradient(stack, 0, 0, this.width, this.height, -1072689136, -804253680);
    //            MinecraftForge.EVENT_BUS.post(new GuiScreenEvent.BackgroundDrawnEvent(this, stack));
    //        }
    // drawString(stack, this.font, new TranslationTextComponent("config.variants.substitute_the_end_biome_with"), this.width / 2 - 153, 10, 10526880);
    // this.endSubstitutionBox.render(stack, width, height, partialTicks);
}
