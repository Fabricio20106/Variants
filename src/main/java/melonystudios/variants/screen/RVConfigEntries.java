package melonystudios.variants.screen;

import melonystudios.variants.Variants;
import melonystudios.variants.screen.button.NoticeBooleanOption;
import melonystudios.variants.screen.button.SeparatorOption;
import melonystudios.variants.settings.RVSettings;
import melonystudios.variants.util.VSStyles;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.BooleanOption;
import net.minecraft.client.settings.SliderPercentageOption;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.fml.ModList;

public class RVConfigEntries {
    public static final int TOOLTIP_MAX_WIDTH = ModList.get().isLoaded("mellowui") ? 170 : 200;
    public static final RVSettings SETTINGS = Variants.revaried().settings();

    // Items
    public static final BooleanOption PLACE_SPAWNER_WHEN_BREAKING_MINECART = new BooleanOption("config.variants.place_spawner_when_breaking_minecart", new TranslationTextComponent("config.variants.place_spawner_when_breaking_minecart.tooltip"), settings -> SETTINGS.placeSpawnerWhenBreakingMinecart, (settings, newValue) -> SETTINGS.placeSpawnerWhenBreakingMinecart = newValue);
    public static final BooleanOption SHOW_TAGS_WITH_ALT = new BooleanOption("config.variants.show_tags_with_alt", new TranslationTextComponent("config.variants.show_tags_with_alt.tooltip"), settings -> SETTINGS.showTagsWithAlt, (settings, newValue) -> SETTINGS.showTagsWithAlt = newValue);
    public static final BooleanOption LINE_BREAKS_ON_TAGS = new BooleanOption("config.variants.line_breaks_on_tags", new TranslationTextComponent("config.variants.line_breaks_on_tags.tooltip"), settings -> SETTINGS.lineBreaksOnTags, (settings, newValue) -> SETTINGS.lineBreaksOnTags = newValue);
    public static final SliderPercentageOption SPYGLASS_ZOOM_LEVEL = new SliderPercentageOption("config.variants.spyglass_zoom_level", 0, 25, 1,
            (options) -> (double) SETTINGS.spyglassZoomLevel,
            (options, newValue) -> SETTINGS.spyglassZoomLevel = (int) Math.round(newValue),
            (options, slider) -> {
                slider.setTooltip(Minecraft.getInstance().font.split(new TranslationTextComponent("config.variants.spyglass_zoom_level.tooltip"), TOOLTIP_MAX_WIDTH));
                return new TranslationTextComponent("options.generic_value", new TranslationTextComponent("config.variants.spyglass_zoom_level"), Math.round(slider.get(options)));
            });
    public static final SliderPercentageOption ANVIL_CHARACTER_LIMIT = new SliderPercentageOption("config.variants.anvil_character_limit", 1, 100, 1,
            (options) -> (double) SETTINGS.anvilCharacterLimit,
            (options, newValue) -> SETTINGS.anvilCharacterLimit = (int) Math.round(newValue),
            (options, slider) -> {
                slider.setTooltip(Minecraft.getInstance().font.split(new TranslationTextComponent("config.variants.anvil_character_limit.tooltip"), TOOLTIP_MAX_WIDTH));
                return new TranslationTextComponent("options.generic_value", new TranslationTextComponent("config.variants.anvil_character_limit"), Math.round(slider.get(options)));
            });
    public static final BooleanOption ENCHANTABLE_SHEARS = new BooleanOption("config.variants.enchantable_shears", new TranslationTextComponent("config.variants.enchantable_shears.tooltip"), settings -> SETTINGS.enchantableShears, (settings, newValue) -> SETTINGS.enchantableShears = newValue);
    public static final BooleanOption ENCHANTABLE_SHIELDS = new BooleanOption("config.variants.enchantable_shields", new TranslationTextComponent("config.variants.enchantable_shields.tooltip"), settings -> SETTINGS.enchantableShields, (settings, newValue) -> SETTINGS.enchantableShields = newValue);
    public static final BooleanOption ENCHANTABLE_FLINT_AND_STEEL = new BooleanOption("config.variants.enchantable_flint_and_steel", new TranslationTextComponent("config.variants.enchantable_flint_and_steel.tooltip"), settings -> SETTINGS.enchantableFlintAndSteel, (settings, newValue) -> SETTINGS.enchantableFlintAndSteel = newValue);

    // Creative tabs
    public static final SeparatorOption CREATIVE_TABS = new SeparatorOption(new TranslationTextComponent("separator.variants.creative_tabs"));
    public static final BooleanOption POPULATE_TAG_CONFIGURABLE_FOOD_TAGS = new BooleanOption("config.variants.populate_tag_configurable_food_tags", new TranslationTextComponent("config.variants.populate_tag_configurable_food_tags.tooltip"), settings -> SETTINGS.populateTagConfigurableFoodTags, (settings, newValue) -> SETTINGS.populateTagConfigurableFoodTags = newValue);
    public static final BooleanOption POPULATE_EXPONENTIAL_STEWS_IN_TABS = new BooleanOption("config.variants.populate_exponential_stews_in_tabs", new TranslationTextComponent("config.variants.populate_exponential_stews_in_tabs.tooltip"), settings -> SETTINGS.populateExponentialStewsInTabs, (settings, newValue) -> SETTINGS.populateExponentialStewsInTabs = newValue);
    public static final BooleanOption POPULATE_STAINED_GLASS_BOTTLES_IN_TABS = new BooleanOption("config.variants.populate_stained_glass_bottles_in_tabs", new TranslationTextComponent("config.variants.populate_stained_glass_bottles_in_tabs.tooltip"), settings -> SETTINGS.populateStainedGlassBottlesInTabs, (settings, newValue) -> SETTINGS.populateStainedGlassBottlesInTabs = newValue);
    public static final BooleanOption POPULATE_SPAWNER_MINECARTS_IN_TABS = new BooleanOption("config.variants.populate_spawner_minecarts_in_tabs", new TranslationTextComponent("config.variants.populate_spawner_minecarts_in_tabs.tooltip"), settings -> SETTINGS.populateSpawnerMinecartsInTabs, (settings, newValue) -> SETTINGS.populateSpawnerMinecartsInTabs = newValue);
    public static final BooleanOption POPULATE_WOOL_ARMOR_COLORS_IN_TABS = new BooleanOption("config.variants.populate_wool_armor_colors_in_tabs", new TranslationTextComponent("config.variants.populate_wool_armor_colors_in_tabs.tooltip"), settings -> SETTINGS.populateWoolArmorColorsInTabs, (settings, newValue) -> SETTINGS.populateWoolArmorColorsInTabs = newValue);
    public static final BooleanOption POPULATE_WOOL_ARMOR_DESIGNS_IN_TABS = new BooleanOption("config.variants.populate_wool_armor_designs_in_tabs", new TranslationTextComponent("config.variants.populate_wool_armor_designs_in_tabs.tooltip"), settings -> SETTINGS.populateWoolArmorDesignsInTabs, (settings, newValue) -> SETTINGS.populateWoolArmorDesignsInTabs = newValue);

    // Tooltips
    public static final SeparatorOption TOOLTIPS = new SeparatorOption(new TranslationTextComponent("separator.variants.tooltips"));
    public static final BooleanOption UPDATED_ENCHANTMENT_TOOLTIPS = new BooleanOption("config.variants.updated_enchantment_tooltips", new TranslationTextComponent("config.variants.updated_enchantment_tooltips.tooltip"), settings -> SETTINGS.updatedEnchantmentTooltips, (settings, newValue) -> SETTINGS.updatedEnchantmentTooltips = newValue);
    public static final BooleanOption ENCHANTMENT_TYPES_TOOLTIP = new BooleanOption("config.variants.enchantment_types_tooltip", new TranslationTextComponent("config.variants.enchantment_types_tooltip.tooltip"), settings -> SETTINGS.enchantmentTypesTooltip, (settings, newValue) -> SETTINGS.enchantmentTypesTooltip = newValue);
    public static final BooleanOption UPDATED_POTION_TOOLTIPS = new BooleanOption("config.variants.updated_potion_tooltips", new TranslationTextComponent("config.variants.updated_potion_tooltips.tooltip"), settings -> SETTINGS.updatedPotionTooltips, (settings, newValue) -> SETTINGS.updatedPotionTooltips = newValue);
    public static final BooleanOption DURATION_FACTOR_TOOLTIP = new BooleanOption("config.variants.duration_factor_tooltip", new TranslationTextComponent("config.variants.duration_factor_tooltip.tooltip"), settings -> SETTINGS.durationFactorTooltip, (settings, newValue) -> SETTINGS.durationFactorTooltip = newValue);
    public static final BooleanOption UPDATED_FIREWORK_TOOLTIPS = new BooleanOption("config.variants.updated_firework_tooltips", new TranslationTextComponent("config.variants.updated_firework_tooltips.tooltip"), settings -> SETTINGS.updatedFireworkTooltips, (settings, newValue) -> SETTINGS.updatedFireworkTooltips = newValue);
    public static final BooleanOption HORSE_ARMOR_POINTS_TOOLTIP = new BooleanOption("config.variants.horse_armor_points_tooltip", new TranslationTextComponent("config.variants.horse_armor_points_tooltip.tooltip"), settings -> SETTINGS.horseArmorPointsTooltip, (settings, newValue) -> SETTINGS.horseArmorPointsTooltip = newValue);

    // Infinity sweaters
    public static final SeparatorOption INFINITY_SWEATERS = new SeparatorOption(new TranslationTextComponent("separator.variants.infinity_sweaters").withStyle(VSStyles.getFromRGB(0x31CCDD)));
    public static final BooleanOption INFINITY_SWEATERS_ENABLED = new BooleanOption("config.variants.infinity_sweaters_enabled", new TranslationTextComponent("config.variants.infinity_sweaters_enabled.tooltip"), settings -> SETTINGS.infinitySweatersEnabled, (settings, newValue) -> SETTINGS.infinitySweatersEnabled = newValue);
    public static final SliderPercentageOption INFINITY_SWEATERS_TAB_LENGTH = new SliderPercentageOption("config.variants.infinity_sweaters_tab_length", 1, 16777215, SETTINGS.infinitySweatersTabSpacing,
            (options) -> (double) SETTINGS.infinitySweatersTabLength,
            (options, newValue) -> SETTINGS.infinitySweatersTabLength = (int) Math.round(newValue),
            (options, slider) -> {
                slider.setTooltip(Minecraft.getInstance().font.split(new TranslationTextComponent("config.variants.infinity_sweaters_tab_length.tooltip"), TOOLTIP_MAX_WIDTH));
                return new TranslationTextComponent("options.generic_value", new TranslationTextComponent("config.variants.infinity_sweaters_tab_length"), Math.round(slider.get(options)));
            });
    public static final SliderPercentageOption INFINITY_SWEATERS_TAB_SPACING = new SliderPercentageOption("config.variants.infinity_sweaters_tab_spacing", 1, SETTINGS.infinitySweatersTabLength, 1,
            (options) -> (double) SETTINGS.infinitySweatersTabSpacing,
            (options, newValue) -> SETTINGS.infinitySweatersTabSpacing = (int) Math.round(newValue),
            (options, slider) -> {
                slider.setTooltip(Minecraft.getInstance().font.split(new TranslationTextComponent("config.variants.infinity_sweaters_tab_spacing.tooltip"), TOOLTIP_MAX_WIDTH));
                return new TranslationTextComponent("options.generic_value", new TranslationTextComponent("config.variants.infinity_sweaters_tab_spacing"), Math.round(slider.get(options)));
            });

    // World generation
    public static final BooleanOption PAINTINGWOOD_FOREST = new NoticeBooleanOption("config.variants.paintingwood_forest", new TranslationTextComponent("config.variants.paintingwood_forest.tooltip"), settings -> SETTINGS.paintingwoodForest, (settings, newValue) -> SETTINGS.paintingwoodForest = newValue);
    public static final BooleanOption AZURE_FIELDS = new NoticeBooleanOption("config.variants.azure_fields", new TranslationTextComponent("config.variants.azure_fields.tooltip"), settings -> SETTINGS.azureFields, (settings, newValue) -> SETTINGS.azureFields = newValue);
    public static final BooleanOption FLOWER_PATCHES = new NoticeBooleanOption("config.variants.flower_patches", new TranslationTextComponent("config.variants.flower_patches.tooltip"), settings -> SETTINGS.flowerPatches, (settings, newValue) -> SETTINGS.flowerPatches = newValue);
    public static final BooleanOption CRIMSON_WHEAT_PATCHES = new NoticeBooleanOption("config.variants.crimson_wheat_patches", new TranslationTextComponent("config.variants.crimson_wheat_patches.tooltip"), settings -> SETTINGS.crimsonWheatPatches, (settings, newValue) -> SETTINGS.crimsonWheatPatches = newValue);
    public static final BooleanOption SOUL_CARROT_PATCHES = new NoticeBooleanOption("config.variants.soul_carrot_patches", new TranslationTextComponent("config.variants.soul_carrot_patches.tooltip"), settings -> SETTINGS.soulCarrotPatches, (settings, newValue) -> SETTINGS.soulCarrotPatches = newValue);
    public static final BooleanOption WARPED_POTATO_PATCHES = new NoticeBooleanOption("config.variants.warped_potato_patches", new TranslationTextComponent("config.variants.warped_potato_patches.tooltip"), settings -> SETTINGS.warpedPotatoPatches, (settings, newValue) -> SETTINGS.warpedPotatoPatches = newValue);
    public static final BooleanOption MELTING_BEET_PATCHES = new NoticeBooleanOption("config.variants.melting_beet_patches", new TranslationTextComponent("config.variants.melting_beet_patches.tooltip"), settings -> SETTINGS.meltingBeetPatches, (settings, newValue) -> SETTINGS.meltingBeetPatches = newValue);
    public static final BooleanOption GENERATE_QUARTZ_ORE = new NoticeBooleanOption("config.variants.quartz_ore", new TranslationTextComponent("config.variants.quartz_ore.tooltip"), settings -> SETTINGS.quartzOre, (settings, newValue) -> SETTINGS.quartzOre = newValue);
    public static final BooleanOption GENERATE_END_QUARTZ_ORE = new NoticeBooleanOption("config.variants.end_quartz_ore", new TranslationTextComponent("config.variants.end_quartz_ore.tooltip"), settings -> SETTINGS.endQuartzOre, (settings, newValue) -> SETTINGS.endQuartzOre = newValue);
    public static final BooleanOption GENERATE_NETHER_COAL_ORE = new NoticeBooleanOption("config.variants.nether_coal_ore", new TranslationTextComponent("config.variants.nether_coal_ore.tooltip"), settings -> SETTINGS.netherCoalOre, (settings, newValue) -> SETTINGS.netherCoalOre = newValue);
    public static final BooleanOption GENERATE_CRYSTALLIZED_MAGMA_CREAM_ORE = new NoticeBooleanOption("config.variants.crystallized_magma_cream_ore", new TranslationTextComponent("config.variants.crystallized_magma_cream_ore.tooltip"), settings -> SETTINGS.crystallizedMagmaCreamOre, (settings, newValue) -> SETTINGS.crystallizedMagmaCreamOre = newValue);
    public static final BooleanOption SOUL_LAVA_SPRINGS = new NoticeBooleanOption("config.variants.soul_lava_springs", new TranslationTextComponent("config.variants.soul_lava_springs.tooltip"), settings -> SETTINGS.soulLavaSprings, (settings, newValue) -> SETTINGS.soulLavaSprings = newValue);
    public static final BooleanOption END_CAVES_AND_RAVINES = new NoticeBooleanOption("config.variants.end_caves_and_ravines", new TranslationTextComponent("config.variants.end_caves_and_ravines.tooltip"), settings -> SETTINGS.endCavesAndRavines, (settings, newValue) -> SETTINGS.endCavesAndRavines = newValue);

    // Entities
    public static final BooleanOption FISH_SPAWNING = new BooleanOption("config.variants.fish_spawning", new TranslationTextComponent("config.variants.fish_spawning.tooltip"), settings -> SETTINGS.fishSpawning, (settings, newValue) -> SETTINGS.fishSpawning = newValue);

    // Enchantments
    public static final SliderPercentageOption QUICK_CHARGE_MAX_LEVEL = new SliderPercentageOption("config.variants.quick_charge_max_level", 1, 25, 1,
            (options) -> (double) SETTINGS.quickChargeMaxLevel,
            (options, newValue) -> SETTINGS.quickChargeMaxLevel = (int) Math.round(newValue),
            (options, slider) -> {
                slider.setTooltip(Minecraft.getInstance().font.split(new TranslationTextComponent("config.variants.quick_charge_max_level.tooltip"), TOOLTIP_MAX_WIDTH));
                return new TranslationTextComponent("options.generic_value", new TranslationTextComponent("config.variants.quick_charge_max_level"), new TranslationTextComponent("enchantment.level." + Math.round(slider.get(options))));
            });

    // Consume behaviors
    public static final SliderPercentageOption EXPLOSION_RADIUS_UPPER_LIMIT = new SliderPercentageOption("config.variants.explosion_radius_upper_limit", 0, 128, 1,
            (options) -> SETTINGS.explosionRadiusUpperLimit,
            (options, newValue) -> SETTINGS.explosionRadiusUpperLimit = newValue,
            (options, slider) -> {
                slider.setTooltip(Minecraft.getInstance().font.split(new TranslationTextComponent("config.variants.explosion_radius_upper_limit.tooltip"), TOOLTIP_MAX_WIDTH));
                return new TranslationTextComponent("options.generic_value", new TranslationTextComponent("config.variants.explosion_radius_upper_limit"), Math.round(slider.get(options)));
            });
    public static final SliderPercentageOption SOUND_PITCH_UPPER_LIMIT = new SliderPercentageOption("config.variants.sound_pitch_upper_limit", 0, 2, 0.25F,
            (options) -> SETTINGS.soundPitchUpperLimit,
            (options, newValue) -> SETTINGS.soundPitchUpperLimit = newValue,
            (options, slider) -> {
                slider.setTooltip(Minecraft.getInstance().font.split(new TranslationTextComponent("config.variants.sound_pitch_upper_limit.tooltip"), TOOLTIP_MAX_WIDTH));
                return new TranslationTextComponent("options.generic_value", new TranslationTextComponent("config.variants.sound_pitch_upper_limit"), slider.get(options));
            });
}
