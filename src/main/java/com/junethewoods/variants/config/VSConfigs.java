package com.junethewoods.variants.config;

import com.junethewoods.variants.Variants;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.config.ModConfigEvent;
import net.neoforged.neoforge.common.ModConfigSpec;

@Mod.EventBusSubscriber(modid = Variants.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class VSConfigs {
    private static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();

    // World Generation
    private static final ModConfigSpec.BooleanValue GENERATE_FLOWER_PATCHES = BUILDER.comment("Allow Variants' flower patches to generate? (Currently only Glow Black Tulips)").define("generateFlowerPatches", true);
    private static final ModConfigSpec.BooleanValue GENERATE_QUARTZ_ORE = BUILDER.comment("Allow overworld quartz ore to generate?").define("generateOverworldQuartzOre", true);
    private static final ModConfigSpec.BooleanValue GENERATE_END_QUARTZ_ORE = BUILDER.comment("Allow end quartz ore to generate?").define("generateEndQuartzOre", true);
    private static final ModConfigSpec.BooleanValue GENERATE_SOUL_LAVA_SPRINGS = BUILDER.comment("Allow soul lava springs ore to generate in the Nether?").define("generateSoulLavaSprings", true);

    // Entities
    private static final ModConfigSpec.BooleanValue FISH_SPAWNING = BUILDER.comment("Allow fishes (old cods) to spawn in Oceans?").define("fishSpawning", true);

    // Enchantments
    private static final ModConfigSpec.BooleanValue ENABLE_QUICK_CHARGE_FIVE = BUILDER.comment("Enables Quick Charge enchantment to go up to level 5 (WARNING: This is not good for your crossbow's health!)").define("enableQuickChargeFive", true);

    // Items
    private static final ModConfigSpec.BooleanValue POPULATE_WOOL_ARMOR_COLORS_IN_TABS = BUILDER.comment("Should the item tabs populate all (dye) colors of wool armor?").define("populateWoolArmorColorInTabs", true);
    private static final ModConfigSpec.BooleanValue CUSTOM_FIREWORK_DESCRIPTIONS = BUILDER.comment("Updates the Firework Rocket & Charge's descriptions.").define("customFireworkDescriptions", true);
    private static final ModConfigSpec.BooleanValue HORSE_ARMOR_ARMOR_POINTS_ON_TOOLTIP = BUILDER.comment("Shows how many armor points each horse armor gives on the item's tooltip.").define("horseArmorArmorPointsOnTooltip", true);
    private static final ModConfigSpec.BooleanValue ENCHANTABLE_SHEARS = BUILDER.comment("Allows any Shears to be enchanted at an Enchanting Table.").define("enchantableShears", true);
    private static final ModConfigSpec.BooleanValue ENCHANTABLE_FLINT_AND_STEEL = BUILDER.comment("Allows any Flint and Steel to be enchanted at an Enchanting Table.").define("enchantableFlintAndSteel", true);
    private static final ModConfigSpec.BooleanValue ENCHANTABLE_SHIELDS = BUILDER.comment("Allows any Shield to be enchanted at an Enchanting Table.").define("enchantableShields", true);

    public static final ModConfigSpec SPEC = BUILDER.build();

    // World Generation
    public static boolean generateFlowerPatches;
    public static boolean generateQuartzOre;
    public static boolean generateEndQuartzOre;
    public static boolean generateSoulLavaSprings;

    // Items
    public static boolean populateWoolArmorColorInTabs;
    public static boolean customFireworkDescriptions;
    public static boolean horseArmorArmorPointsOnTooltip;
    public static boolean enchantableShears;
    public static boolean enchantableFlintAndSteel;
    public static boolean enchantableShields;

    // Entities
    public static boolean fishSpawning;

    // Enchantments
    public static boolean enableQuickChargeFive;

    @SubscribeEvent
    static void onLoad(final ModConfigEvent event) {
        // World Generation
        generateFlowerPatches = GENERATE_FLOWER_PATCHES.get();
        generateQuartzOre = GENERATE_QUARTZ_ORE.get();
        generateEndQuartzOre = GENERATE_END_QUARTZ_ORE.get();
        generateSoulLavaSprings = GENERATE_SOUL_LAVA_SPRINGS.get();

        // Items
        populateWoolArmorColorInTabs = POPULATE_WOOL_ARMOR_COLORS_IN_TABS.get();
        customFireworkDescriptions = CUSTOM_FIREWORK_DESCRIPTIONS.get();
        horseArmorArmorPointsOnTooltip = HORSE_ARMOR_ARMOR_POINTS_ON_TOOLTIP.get();
        enchantableShears = ENCHANTABLE_SHEARS.get();
        enchantableFlintAndSteel = ENCHANTABLE_FLINT_AND_STEEL.get();
        enchantableShields = ENCHANTABLE_SHIELDS.get();

        // Enchantments
        enableQuickChargeFive = ENABLE_QUICK_CHARGE_FIVE.get();

        // Entities
        fishSpawning = FISH_SPAWNING.get();
    }
}
