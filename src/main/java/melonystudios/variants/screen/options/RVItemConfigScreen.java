package melonystudios.variants.screen.options;

import com.google.common.collect.Lists;
import com.mojang.blaze3d.matrix.MatrixStack;
import melonystudios.variants.screen.AbstractRVConfigScreen;
import melonystudios.variants.screen.RVConfigEntries;
import melonystudios.variants.util.VSStyles;
import melonystudios.variants.util.VSUtils;
import net.minecraft.client.AbstractOption;
import net.minecraft.client.GameSettings;
import net.minecraft.client.gui.DialogTexts;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.client.gui.widget.list.OptionsRowList;
import net.minecraft.util.IReorderingProcessor;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.fml.ModList;

import java.util.List;

import static melonystudios.variants.screen.RVConfigEntries.*;

public class RVItemConfigScreen extends AbstractRVConfigScreen {
    public static final List<AbstractOption> ITEMS = Lists.newArrayList(SHOW_TAGS_WITH_ALT, LINE_BREAKS_ON_TAGS, PLACE_SPAWNER_WHEN_BREAKING_MINECART, ENCHANTABLE_SHEARS, ENCHANTABLE_SHIELDS, ENCHANTABLE_FLINT_AND_STEEL);
    public static final List<AbstractOption> CREATIVE_TABS = Lists.newArrayList(POPULATE_TAG_CONFIGURABLE_FOOD_TAGS, POPULATE_EXPONENTIAL_STEWS_IN_TABS, POPULATE_STAINED_GLASS_BOTTLES_IN_TABS, POPULATE_SPAWNER_MINECARTS_IN_TABS, POPULATE_WOOL_ARMOR_COLORS_IN_TABS, POPULATE_WOOL_ARMOR_DESIGNS_IN_TABS);
    public static final List<AbstractOption> TOOLTIPS = Lists.newArrayList(UPDATED_ENCHANTMENT_TOOLTIPS, ENCHANTMENT_TYPES_TOOLTIP, UPDATED_POTION_TOOLTIPS, DURATION_FACTOR_TOOLTIP, UPDATED_FIREWORK_TOOLTIPS, HORSE_ARMOR_POINTS_TOOLTIP);
    public static final List<AbstractOption> INFINITY_SWEATERS = Lists.newArrayList(INFINITY_SWEATERS_ENABLED, INFINITY_SWEATERS_TAB_LENGTH, INFINITY_SWEATERS_TAB_SPACING);
    private OptionsRowList list;

    public RVItemConfigScreen(Screen screen, GameSettings settings) {
        super(screen, settings, VSStyles.buildScreenSubtitle("Revaried", new TranslationTextComponent("menu.variants.options.items.title")));
    }

    @Override
    protected void init() {
        boolean mellowUILoaded = ModList.get().isLoaded("mellowui");
        this.list = new OptionsRowList(this.minecraft, this.width, this.height, 32, this.height - 32, 25);
        this.list.setRenderBackground(mellowUILoaded);
        this.list.setRenderTopAndBottom(mellowUILoaded);
        this.list.addBig(SPYGLASS_ZOOM_LEVEL);
        this.list.addBig(ANVIL_CHARACTER_LIMIT);
        this.list.addSmall(ITEMS.toArray(new AbstractOption[0]));
        this.list.addBig(RVConfigEntries.CREATIVE_TABS);
        this.list.addSmall(CREATIVE_TABS.toArray(new AbstractOption[0]));
        this.list.addBig(RVConfigEntries.TOOLTIPS);
        this.list.addSmall(TOOLTIPS.toArray(new AbstractOption[0]));
        this.list.addBig(RVConfigEntries.INFINITY_SWEATERS);
        this.list.addSmall(INFINITY_SWEATERS.toArray(new AbstractOption[0]));
        this.children.add(this.list);

        // Done button
        this.addButton(new Button(this.width / 2 - 100, this.height - 25, 200, 20, DialogTexts.GUI_DONE, button -> {
            SHOULD_SAVE_SETTINGS = true;
            this.minecraft.setScreen(this.lastScreen);
        }));
    }

    @Override
    public void render(MatrixStack stack, int mouseX, int mouseY, float partialTicks) {
        super.renderPanorama(stack, partialTicks);
        this.list.render(stack, mouseX, mouseY, partialTicks);
        drawCenteredString(stack, this.font, this.title, this.width / 2, VSUtils.DEFAULT_TITLE_HEIGHT, 0xFFFFFF);
        super.render(stack, mouseX, mouseY, partialTicks);
        List<IReorderingProcessor> tooltip = tooltipAt(this.list, mouseX, mouseY);
        if (tooltip != null) this.renderTooltip(stack, tooltip, mouseX, mouseY);
    }
}
