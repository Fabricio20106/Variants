package com.junethewoods.variants.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class VSCommonConfigs {
    // World Generation
    public final ForgeConfigSpec.BooleanValue generateFlowerPatches;
    public final ForgeConfigSpec.BooleanValue generateQuartzOre;
    public final ForgeConfigSpec.BooleanValue generateEndQuartzOre;
    public final ForgeConfigSpec.BooleanValue generateSoulLavaSprings;
    public final ForgeConfigSpec.BooleanValue generateEndCavesAndRavines;

    // Items
    public final ForgeConfigSpec.BooleanValue populateExponentialBowlsInTabs;
    public final ForgeConfigSpec.BooleanValue populateWoolArmorColorInTabs;
    public final ForgeConfigSpec.BooleanValue enableInfinitySweatersTab;
    public final ForgeConfigSpec.IntValue infinitySweatersTabLength;
    public final ForgeConfigSpec.IntValue infinitySweatersTabSpacing;
    public final ForgeConfigSpec.BooleanValue customFireworkDescriptions;
    public final ForgeConfigSpec.BooleanValue customEnchantmentDescriptions;
    public final ForgeConfigSpec.BooleanValue enchantmentTypesOnTooltip;
    public final ForgeConfigSpec.BooleanValue horseArmorArmorPointsOnTooltip;
    public final ForgeConfigSpec.BooleanValue enchantableShears;
    public final ForgeConfigSpec.BooleanValue enchantableFlintAndSteel;
    public final ForgeConfigSpec.BooleanValue enchantableShields;

    // Entities
    public final ForgeConfigSpec.BooleanValue fishSpawning;

    // Enchantments
    public final ForgeConfigSpec.BooleanValue enableQuickChargeFive;

    public VSCommonConfigs(ForgeConfigSpec.Builder builder) {
        builder.comment("Welcome to the Variants config file! This file contains various configs on many aspects of the mod. (Made on 18/11/23)");

        builder.push("worldGeneration");
        this.generateFlowerPatches = builder.comment("Allow Variants' flower patches to generate? (Glow Black Tulips and Sunny Flowers)").define("generateFlowerPatches", true);
        this.generateQuartzOre = builder.comment("Allow Overworld quartz ore to generate?").define("generateOverworldQuartzOre", true);
        this.generateEndQuartzOre = builder.comment("Allow End quartz ore to generate?").define("generateEndQuartzOre", true);
        this.generateSoulLavaSprings = builder.comment("Allow soul lava springs ore to generate in the Nether?").define("generateSoulLavaSprings", true);
        this.generateEndCavesAndRavines = builder.comment("Allow caves and ravines to generate in the End?").define("generateEndCavesAndRavines", true);
        builder.pop();

        builder.push("entities");
        this.fishSpawning = builder.comment("Allow fishes (old cods) to spawn in Oceans?").define("fishSpawning", true);
        builder.pop();

        builder.push("enchantments");
        this.enableQuickChargeFive = builder.comment("Enables Quick Charge enchantment to go up to level 5 (WARNING: This is not good for your crossbow's health!)").define("enableQuickChargeFive", true);
        builder.pop();

        builder.push("items");
        this.populateWoolArmorColorInTabs = builder.comment("Should the item tabs populate all (dye) colors of wool armor?").define("populateWoolArmorColorInTabs", true);
        this.populateExponentialBowlsInTabs = builder.comment("Should the item tabs populate all different wood types for all bowls?").define("populateExponentialBowlsInTabs", true);
        this.customFireworkDescriptions = builder.comment("Updates the Firework Rocket & Charge's descriptions.").define("customFireworkDescriptions", true);
        this.customEnchantmentDescriptions = builder.comment("Updates the enchantment description on enchanted items.").define("customEnchantmentDescriptions", true);
        this.enchantmentTypesOnTooltip = builder.comment("If the \"(Breakable Items)\" or \"(Swords)\" suffix on enchantment should show up.").define("enchantmentTypesOnTooltip", false);
        this.horseArmorArmorPointsOnTooltip = builder.comment("Shows how many armor points each horse armor gives on the item's tooltip.").define("horseArmorArmorPointsOnTooltip", true);
        this.enchantableShears = builder.comment("Allows any Shears to be enchanted at an Enchanting Table.").define("enchantableShears", true);
        this.enchantableFlintAndSteel = builder.comment("Allows any Flint and Steel to be enchanted at an Enchanting Table.").define("enchantableFlintAndSteel", true);
        this.enchantableShields = builder.comment("Allows any Shield to be enchanted at an Enchanting Table.").define("enchantableShields", true);
        this.enableInfinitySweatersTab = builder.comment("Enables the Infinity Sweaters tab (WARNING: This tab is *very* fragile and will most likely cause issues when at high values).").define("infinitySweatersTab.enableInfinitySweatersTab", false);
        this.infinitySweatersTabLength = builder.comment("How many sweaters should the tab load in. (Def: 4096)").defineInRange("infinitySweatersTab.infinitySweatersTabLength", 4096, 1, 16777215);
        this.infinitySweatersTabSpacing = builder.comment("The spacing between sweater colors. (Def: 16)").defineInRange("infinitySweatersTab.infinitySweatersTabSpacing", 16, 1, 16777215);
        builder.pop();
    }
}
