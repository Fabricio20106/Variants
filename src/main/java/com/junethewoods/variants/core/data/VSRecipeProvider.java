package com.junethewoods.variants.core.data;

import com.junethewoods.variants.core.Variants;
import com.junethewoods.variants.core.init.VSWeaponry;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraftforge.common.Tags;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Consumer;

public class VSRecipeProvider extends RecipeProvider {
    public VSRecipeProvider(DataGenerator generator) {
        super(generator);
    }

    @Override
    protected void buildCraftingRecipes(Consumer<FinishedRecipe> consumer) {
        // Stone Tools
        buildDoubleToolRecipe("deepslate", "deepslate", consumer, "_using_deepslate");
        buildToolRecipe("cobbled_deepslate", "deepslate", consumer);
        buildToolRecipe("sandstone", "sandstone", consumer);
        buildDoubleToolRecipe("smooth_sandstone", "sandstone", consumer, "_using_smooth_sandstone");
        buildToolRecipe("red_sandstone", "red_sandstone", consumer);
        buildDoubleToolRecipe("smooth_red_sandstone", "red_sandstone", consumer, "_using_smooth_red_sandstone");
        buildToolRecipe("calcite", "calcite", consumer);
        buildToolRecipe("tuff", "tuff", consumer);
        buildToolRecipe("bedrock", "bedrock", consumer);
        buildToolRecipe("obsidian", "obsidian", consumer);
        buildToolRecipe("crying_obsidian", "crying_obsidian", consumer);
        buildToolRecipe("netherrack", "netherrack", consumer);
        buildToolRecipe("basalt", "basalt", consumer);
        buildDoubleToolRecipe("smooth_basalt", "basalt", consumer, "_using_smooth_basalt");
        buildToolRecipe("blackstone", "blackstone", consumer);
        buildToolRecipe("magma_block", "magma", consumer);

        // Wooden Tools
        buildToolRecipe("oak_planks", "oak", consumer);
        buildToolRecipe("spruce_planks", "spruce", consumer);
        buildToolRecipe("birch_planks", "birch", consumer);
        buildToolRecipe("jungle_planks", "jungle", consumer);
        buildToolRecipe("acacia_planks", "acacia", consumer);
        buildToolRecipe("dark_oak_planks", "dark_oak", consumer);
        buildToolRecipe("mangrove_planks", "mangrove", consumer);
        //buildToolRecipe("bamboo_planks", "bamboo", consumer);
        ShapedRecipeBuilder.shaped(VSWeaponry.BAMBOO_SHOVEL.get()).define('H', Items.BAMBOO).define('S', Items.BAMBOO).pattern("H").pattern("S").pattern("S").unlockedBy("has_material", has(
                Items.BAMBOO)).save(consumer);
        buildToolRecipe("cherry_planks", "cherry", consumer);
        buildToolRecipe("variants:painting_planks", "painting", consumer);
        buildToolRecipe("crimson_planks", "crimson", consumer);
        buildToolRecipe("warped_planks", "warped", consumer);
        buildToolRecipe("variants:ender_planks", "ender", consumer);

        // Mineral Tools
        buildToolRecipe("amethyst_shard", "amethyst", consumer);
        buildToolRecipe("copper_ingot", "copper", consumer);
        buildToolRecipe("prismarine_crystals", "prismarine_crystal", consumer);
        buildToolRecipe("variants:elder_prismarine_crystals", "elder_prismarine_crystal", consumer);
        buildToolRecipe("redstone", "redstone", consumer);
        buildToolRecipe("coal", "coal", consumer);
//        buildToolRecipe("variants:empty_slot_ingot", "empty_slot", consumer);

        buildArmorRecipe("copper_ingot", "copper", false, consumer);
        buildArmorRecipe("phantom_membrane", "phantom_membrane", true, consumer);
        buildArmorRecipe("rabbit_hide", "rabbit_hide", true, consumer);
        buildArmorRecipe("white_wool", "wool", true, consumer);
//        buildArmorRecipe("variants:empty_slot_ingot", "empty_armor_slot", false, consumer);
    }

    protected static void buildToolRecipe(String materialName, String toolName, Consumer<FinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(ForgeRegistries.ITEMS.getValue(Variants.resourceLoc(toolName + "_sword"))).define('H', ForgeRegistries.ITEMS.getValue(new ResourceLocation(
                materialName))).define('S', Tags.Items.RODS_WOODEN).pattern("H").pattern("H").pattern("S").unlockedBy("has_material", has(
                        ForgeRegistries.ITEMS.getValue(new ResourceLocation(materialName)))).save(consumer);

        ShapedRecipeBuilder.shaped(ForgeRegistries.ITEMS.getValue(Variants.resourceLoc(toolName + "_pickaxe"))).define('H', ForgeRegistries.ITEMS.getValue(new ResourceLocation(
                materialName))).define('S', Tags.Items.RODS_WOODEN).pattern("HHH").pattern(" S ").pattern(" S ").unlockedBy("has_material", has(
                ForgeRegistries.ITEMS.getValue(new ResourceLocation(materialName)))).save(consumer);

        ShapedRecipeBuilder.shaped(ForgeRegistries.ITEMS.getValue(Variants.resourceLoc(toolName + "_shovel"))).define('H', ForgeRegistries.ITEMS.getValue(new ResourceLocation(
                materialName))).define('S', Tags.Items.RODS_WOODEN).pattern("H").pattern("S").pattern("S").unlockedBy("has_material", has(
                ForgeRegistries.ITEMS.getValue(new ResourceLocation(materialName)))).save(consumer);

        ShapedRecipeBuilder.shaped(ForgeRegistries.ITEMS.getValue(Variants.resourceLoc(toolName + "_axe"))).define('H', ForgeRegistries.ITEMS.getValue(new ResourceLocation(
                materialName))).define('S', Tags.Items.RODS_WOODEN).pattern("HH").pattern("HS").pattern(" S").unlockedBy("has_material", has(
                ForgeRegistries.ITEMS.getValue(new ResourceLocation(materialName)))).save(consumer);

        ShapedRecipeBuilder.shaped(ForgeRegistries.ITEMS.getValue(Variants.resourceLoc(toolName + "_hoe"))).define('H', ForgeRegistries.ITEMS.getValue(new ResourceLocation(
                materialName))).define('S', Tags.Items.RODS_WOODEN).pattern("HH").pattern(" S").pattern(" S").unlockedBy("has_material", has(
                ForgeRegistries.ITEMS.getValue(new ResourceLocation(materialName)))).save(consumer);
    }

    protected static void buildDoubleToolRecipe(String materialName, String toolName, Consumer<FinishedRecipe> consumer, String saveAs) {
        ShapedRecipeBuilder.shaped(ForgeRegistries.ITEMS.getValue(Variants.resourceLoc(toolName + "_sword"))).define('H', ForgeRegistries.ITEMS.getValue(new ResourceLocation(
                materialName))).define('S', Tags.Items.RODS_WOODEN).pattern("H").pattern("H").pattern("S").unlockedBy("has_material", has(
                ForgeRegistries.ITEMS.getValue(new ResourceLocation(materialName)))).save(consumer, Variants.MOD_ID + ":" + materialName + "_sword" + saveAs);

        ShapedRecipeBuilder.shaped(ForgeRegistries.ITEMS.getValue(Variants.resourceLoc(toolName + "_pickaxe"))).define('H', ForgeRegistries.ITEMS.getValue(new ResourceLocation(
                materialName))).define('S', Tags.Items.RODS_WOODEN).pattern("HHH").pattern(" S ").pattern(" S ").unlockedBy("has_material", has(
                ForgeRegistries.ITEMS.getValue(new ResourceLocation(materialName)))).save(consumer, Variants.MOD_ID + ":" + materialName + "_pickaxe" + saveAs);

        ShapedRecipeBuilder.shaped(ForgeRegistries.ITEMS.getValue(Variants.resourceLoc(toolName + "_shovel"))).define('H', ForgeRegistries.ITEMS.getValue(new ResourceLocation(
                materialName))).define('S', Tags.Items.RODS_WOODEN).pattern("H").pattern("S").pattern("S").unlockedBy("has_material", has(
                ForgeRegistries.ITEMS.getValue(new ResourceLocation(materialName)))).save(consumer, Variants.MOD_ID + ":" + materialName + "_shovel" + saveAs);

        ShapedRecipeBuilder.shaped(ForgeRegistries.ITEMS.getValue(Variants.resourceLoc(toolName + "_axe"))).define('H', ForgeRegistries.ITEMS.getValue(new ResourceLocation(
                materialName))).define('S', Tags.Items.RODS_WOODEN).pattern("HH").pattern("HS").pattern(" S").unlockedBy("has_material", has(
                ForgeRegistries.ITEMS.getValue(new ResourceLocation(materialName)))).save(consumer, Variants.MOD_ID + ":" + materialName + "_axe" + saveAs);

        ShapedRecipeBuilder.shaped(ForgeRegistries.ITEMS.getValue(Variants.resourceLoc(toolName + "_hoe"))).define('H', ForgeRegistries.ITEMS.getValue(new ResourceLocation(
                materialName))).define('S', Tags.Items.RODS_WOODEN).pattern("HH").pattern(" S").pattern(" S").unlockedBy("has_material", has(
                ForgeRegistries.ITEMS.getValue(new ResourceLocation(materialName)))).save(consumer, Variants.MOD_ID + ":" + materialName + "_hoe" + saveAs);
    }

    protected static void buildArmorRecipe(String materialName, String armorName, boolean isSweatchest, Consumer<FinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(ForgeRegistries.ITEMS.getValue(Variants.resourceLoc(armorName + "_helmet"))).define('H', ForgeRegistries.ITEMS.getValue(new ResourceLocation(
                materialName))).pattern("HHH").pattern("H H").unlockedBy("has_material", has(
                ForgeRegistries.ITEMS.getValue(new ResourceLocation(materialName)))).save(consumer);

        if (isSweatchest) {
            ShapedRecipeBuilder.shaped(ForgeRegistries.ITEMS.getValue(Variants.resourceLoc(armorName + "_sweatchest"))).define('H', ForgeRegistries.ITEMS.getValue(new ResourceLocation(
                    materialName))).pattern("H H").pattern("HHH").pattern("HHH").unlockedBy("has_material", has(
                    ForgeRegistries.ITEMS.getValue(new ResourceLocation(materialName)))).save(consumer);
        } else {
            ShapedRecipeBuilder.shaped(ForgeRegistries.ITEMS.getValue(Variants.resourceLoc(armorName + "_chestplate"))).define('H', ForgeRegistries.ITEMS.getValue(new ResourceLocation(
                    materialName))).pattern("H H").pattern("HHH").pattern("HHH").unlockedBy("has_material", has(
                    ForgeRegistries.ITEMS.getValue(new ResourceLocation(materialName)))).save(consumer);
        }

        ShapedRecipeBuilder.shaped(ForgeRegistries.ITEMS.getValue(Variants.resourceLoc(armorName + "_leggings"))).define('H', ForgeRegistries.ITEMS.getValue(new ResourceLocation(
                materialName))).pattern("HHH").pattern("H H").pattern("H H").unlockedBy("has_material", has(
                ForgeRegistries.ITEMS.getValue(new ResourceLocation(materialName)))).save(consumer);

        ShapedRecipeBuilder.shaped(ForgeRegistries.ITEMS.getValue(Variants.resourceLoc(armorName + "_boots"))).define('H', ForgeRegistries.ITEMS.getValue(new ResourceLocation(
                materialName))).pattern("H H").pattern("H H").unlockedBy("has_material", has(
                ForgeRegistries.ITEMS.getValue(new ResourceLocation(materialName)))).save(consumer);
    }
}
