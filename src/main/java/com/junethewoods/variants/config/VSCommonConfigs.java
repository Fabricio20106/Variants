package com.junethewoods.variants.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class VSCommonConfigs {
    // Mixins
    public final ForgeConfigSpec.BooleanValue customFireworkDescriptions;
    public final ForgeConfigSpec.BooleanValue horseArmorArmorPointsOnTooltip;
    public final ForgeConfigSpec.BooleanValue enchantableShears;
    public final ForgeConfigSpec.BooleanValue enchantableFlintAndSteel;
    public final ForgeConfigSpec.BooleanValue enchantableShields;
    public final ForgeConfigSpec.BooleanValue enableQuickChargeFive;

    // World Generation
    public final ForgeConfigSpec.BooleanValue generateFlowerPatches;
    public final ForgeConfigSpec.BooleanValue generateQuartzOre;
    public final ForgeConfigSpec.BooleanValue generateEndQuartzOre;
    public final ForgeConfigSpec.BooleanValue generateSoulLavaSprings;

    // Items
    public final ForgeConfigSpec.BooleanValue populateWoolArmorColorInTabs;

    public VSCommonConfigs(ForgeConfigSpec.Builder builder) {
        builder.comment("Welcome to the Variants config file! This file contains various configs on many aspects of the mod. (Made on 18/11/23)");

        builder.push("mixinRelated");
        this.customFireworkDescriptions = builder.comment("Updates the Firework Rocket & Charge's descriptions.").define("customFireworkDescriptions", true);
        this.horseArmorArmorPointsOnTooltip = builder.comment("Shows how many armor points each horse armor gives on the item's tooltip.").define("horseArmorArmorPointsOnTooltip", true);
        this.enchantableShears = builder.comment("Allows any Shears to be enchanted at an Enchanting Table.").define("enchantableShears", true);
        this.enchantableFlintAndSteel = builder.comment("Allows any Flint and Steel to be enchanted at an Enchanting Table.").define("enchantableFlintAndSteel", true);
        this.enchantableShields = builder.comment("Allows any Shield to be enchanted at an Enchanting Table.").define("enchantableShields", true);
        this.enableQuickChargeFive = builder.comment("Enables Quick Charge enchantment to go up to level 5 (WARNING: This is not good for your crossbow's health!)").define("enableQuickChargeFive", true);
        builder.pop();

        builder.push("worldGeneration");
        this.generateFlowerPatches = builder.comment("Allow Variants' flower patches to generate? (Currently only Glow Black Tulips)").define("generateFlowerPatches", true);
        this.generateQuartzOre = builder.comment("Allow overworld quartz ore to generate?").define("generateOverworldQuartzOre", true);
        this.generateEndQuartzOre = builder.comment("Allow end quartz ore to generate?").define("generateEndQuartzOre", true);
        this.generateSoulLavaSprings = builder.comment("Allow soul lava springs ore to generate in the Nether?").define("generateSoulLavaSprings", true);
        builder.pop();

        builder.push("items");
        this.populateWoolArmorColorInTabs = builder.comment("Should the item tabs populate all (dye) colors of wool armor?").define("populateWoolArmorColorInTabs", true);
        builder.pop();
    }
}
