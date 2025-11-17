package melonystudios.variants.screen;

import melonystudios.variants.Variants;
import melonystudios.variants.config.RVJSONConfig;
import melonystudios.variants.screen.button.NoticeBooleanOption;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.BooleanOption;
import net.minecraft.client.settings.SliderPercentageOption;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.fml.ModList;

public class RVConfigEntries {
    public static final int TOOLTIP_MAX_WIDTH = ModList.get().isLoaded("mellowui") ? 170 : 200;
    public static final RVJSONConfig CONFIG = Variants.INSTANCE.getConfig();

    // Items

    // World Generation
    public static final BooleanOption PAINTINGWOOD_FOREST = new NoticeBooleanOption("config.variants.paintingwood_forest", new TranslationTextComponent("config.variants.paintingwood_forest.desc"), settings -> CONFIG.paintingwoodForest, (settings, newValue) -> CONFIG.paintingwoodForest = newValue);
    public static final BooleanOption AZURE_FIELDS = new NoticeBooleanOption("config.variants.azure_fields", new TranslationTextComponent("config.variants.azure_fields.desc"), settings -> CONFIG.azureFields, (settings, newValue) -> CONFIG.azureFields = newValue);
    public static final BooleanOption FLOWER_PATCHES = new NoticeBooleanOption("config.variants.flower_patches", new TranslationTextComponent("config.variants.flower_patches.desc"), settings -> CONFIG.flowerPatches, (settings, newValue) -> CONFIG.flowerPatches = newValue);
    public static final BooleanOption CRIMSON_WHEAT_PATCHES = new NoticeBooleanOption("config.variants.crimson_wheat_patches", new TranslationTextComponent("config.variants.crimson_wheat_patches.desc"), settings -> CONFIG.crimsonWheatPatches, (settings, newValue) -> CONFIG.crimsonWheatPatches = newValue);
    public static final BooleanOption SOUL_CARROT_PATCHES = new NoticeBooleanOption("config.variants.soul_carrot_patches", new TranslationTextComponent("config.variants.soul_carrot_patches.desc"), settings -> CONFIG.soulCarrotPatches, (settings, newValue) -> CONFIG.soulCarrotPatches = newValue);
    public static final BooleanOption WARPED_POTATO_PATCHES = new NoticeBooleanOption("config.variants.warped_potato_patches", new TranslationTextComponent("config.variants.warped_potato_patches.desc"), settings -> CONFIG.warpedPotatoPatches, (settings, newValue) -> CONFIG.warpedPotatoPatches = newValue);
    public static final BooleanOption MELTING_BEET_PATCHES = new NoticeBooleanOption("config.variants.melting_beet_patches", new TranslationTextComponent("config.variants.melting_beet_patches.desc"), settings -> CONFIG.meltingBeetPatches, (settings, newValue) -> CONFIG.meltingBeetPatches = newValue);
    public static final BooleanOption GENERATE_QUARTZ_ORE = new NoticeBooleanOption("config.variants.quartz_ore", new TranslationTextComponent("config.variants.quartz_ore.desc"), settings -> CONFIG.quartzOre, (settings, newValue) -> CONFIG.quartzOre = newValue);
    public static final BooleanOption GENERATE_END_QUARTZ_ORE = new NoticeBooleanOption("config.variants.end_quartz_ore", new TranslationTextComponent("config.variants.end_quartz_ore.desc"), settings -> CONFIG.endQuartzOre, (settings, newValue) -> CONFIG.endQuartzOre = newValue);
    public static final BooleanOption GENERATE_NETHER_COAL_ORE = new NoticeBooleanOption("config.variants.nether_coal_ore", new TranslationTextComponent("config.variants.nether_coal_ore.desc"), settings -> CONFIG.netherCoalOre, (settings, newValue) -> CONFIG.netherCoalOre = newValue);
    public static final BooleanOption GENERATE_CRYSTALLIZED_MAGMA_CREAM_ORE = new NoticeBooleanOption("config.variants.crystallized_magma_cream_ore", new TranslationTextComponent("config.variants.crystallized_magma_cream_ore.desc"), settings -> CONFIG.crystallizedMagmaCreamOre, (settings, newValue) -> CONFIG.crystallizedMagmaCreamOre = newValue);
    public static final BooleanOption SOUL_LAVA_SPRINGS = new NoticeBooleanOption("config.variants.soul_lava_springs", new TranslationTextComponent("config.variants.soul_lava_springs.desc"), settings -> CONFIG.soulLavaSprings, (settings, newValue) -> CONFIG.soulLavaSprings = newValue);
    public static final BooleanOption END_CAVES_AND_RAVINES = new NoticeBooleanOption("config.variants.end_caves_and_ravines", new TranslationTextComponent("config.variants.end_caves_and_ravines.desc"), settings -> CONFIG.endCavesAndRavines, (settings, newValue) -> CONFIG.endCavesAndRavines = newValue);

    // Entities
    public static final BooleanOption FISH_SPAWNING = new BooleanOption("config.variants.fish_spawning", new TranslationTextComponent("config.variants.fish_spawning.desc"), settings -> CONFIG.fishSpawning, (settings, newValue) -> CONFIG.fishSpawning = newValue);

    // Enchantments
    public static final SliderPercentageOption QUICK_CHARGE_MAX_LEVEL = new SliderPercentageOption("config.variants.quick_charge_max_level", 0, 25, 1,
            (options) -> (double) CONFIG.quickChargeMaxLevel,
            (options, newValue) -> CONFIG.quickChargeMaxLevel = (int) Math.round(newValue),
            (options, slider) -> {
                slider.setTooltip(Minecraft.getInstance().font.split(new TranslationTextComponent("config.variants.quick_charge_max_level.desc"), TOOLTIP_MAX_WIDTH));
                return new TranslationTextComponent("config.variants.quick_charge_max_level", new TranslationTextComponent("enchantment.level." + Math.round(slider.get(options))));
            });

    // Consume Behaviors
    public static final SliderPercentageOption EXPLOSION_RADIUS_UPPER_LIMIT = new SliderPercentageOption("config.variants.explosion_radius_upper_limit", 0, 128, 1,
            (options) -> CONFIG.explosionRadiusUpperLimit,
            (options, newValue) -> CONFIG.explosionRadiusUpperLimit = newValue,
            (options, slider) -> {
                slider.setTooltip(Minecraft.getInstance().font.split(new TranslationTextComponent("config.variants.explosion_radius_upper_limit.desc"), TOOLTIP_MAX_WIDTH));
                return new TranslationTextComponent("config.variants.explosion_radius_upper_limit", Math.round(slider.get(options)));
            });
    public static final SliderPercentageOption SOUND_PITCH_UPPER_LIMIT = new SliderPercentageOption("config.variants.sound_pitch_upper_limit", 0, 2, 0.25F,
            (options) -> CONFIG.soundPitchUpperLimit,
            (options, newValue) -> CONFIG.soundPitchUpperLimit = newValue,
            (options, slider) -> {
                slider.setTooltip(Minecraft.getInstance().font.split(new TranslationTextComponent("config.variants.sound_pitch_upper_limit.desc"), TOOLTIP_MAX_WIDTH));
                return new TranslationTextComponent("config.variants.sound_pitch_upper_limit", slider.get(options));
            });
}
