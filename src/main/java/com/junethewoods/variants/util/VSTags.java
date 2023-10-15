package com.junethewoods.variants.util;

import com.junethewoods.variants.Variants;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ITag;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.ResourceLocation;

public class VSTags {
    public static class Items {
        // Forge Tags
        public static final ITag.INamedTag<Item> STORAGE_BLOCKS_NETHERITE_SCRAP = forge("storage_blocks/netherite_scrap");

        public static final ITag.INamedTag<Item> INGOTS_EXPOSED_COPPER = forge("ingots/exposed_copper");
        public static final ITag.INamedTag<Item> INGOTS_WEATHERED_COPPER = forge("ingots/weathered_copper");
        public static final ITag.INamedTag<Item> INGOTS_OXIDIZED_COPPER = forge("ingots/oxidized_copper");

        public static final ITag.INamedTag<Item> NUGGETS_PURPLE_IRON = forge("nuggets/purple_iron");

        public static final ITag.INamedTag<Item> RAW_MATERIALS = forge("raw_materials");
        public static final ITag.INamedTag<Item> RAW_MATERIALS_NETHERITE_SCRAP = forge("raw_materials/netherite_scrap");

        public static final ITag.INamedTag<Item> RODS_DEBUG_WOODEN = forge("rods/debug_wooden");
        public static final ITag.INamedTag<Item> RODS_NETHERITE = forge("rods/netherite");

        public static final ITag.INamedTag<Item> DYES_GLOW_BLACK = forge("dyes/glow_black");

        // Variants' Tags
        public static final ITag.INamedTag<Item> CRAFTING_MATERIALS = mod("crafting_materials");
        public static final ITag.INamedTag<Item> CM_DIORITE = mod("crafting_materials/diorite");
        public static final ITag.INamedTag<Item> CM_GRANITE = mod("crafting_materials/granite");
        public static final ITag.INamedTag<Item> CM_ANDESITE = mod("crafting_materials/andesite");
        public static final ITag.INamedTag<Item> CM_MAGMA_BLOCK = mod("crafting_materials/magma_block");

        public static final ITag.INamedTag<Item> PAINTING_DOORS = mod("painting_doors");
        public static final ITag.INamedTag<Item> PAINTING_LOGS = mod("painting_logs");
        public static final ITag.INamedTag<Item> SHULKER_SHELLS = mod("shulker_shells");
        public static final ITag.INamedTag<Item> BOOKS = mod("books");
        public static final ITag.INamedTag<Item> BOOKSHELVES = mod("bookshelves");

        public static ITag.INamedTag<Item> forge(String name) {
            return ItemTags.bind(new ResourceLocation("forge", name).toString());
        }

        public static ITag.INamedTag<Item> mod(String name) {
            return ItemTags.bind(Variants.resourceLoc(name).toString());
        }
    }

    public static class Blocks {
        // Forge Tags
        public static final ITag.INamedTag<Block> STORAGE_BLOCKS_NETHERITE_SCRAP = forge("storage_blocks/netherite_scrap");

        // Variants' Tags
        public static final ITag.INamedTag<Block> PAINTING_DOORS = mod("painting_doors");
        public static final ITag.INamedTag<Block> PAINTING_LOGS = mod("painting_logs");
        public static final ITag.INamedTag<Block> BOOKSHELVES = mod("bookshelves");
        public static final ITag.INamedTag<Block> BEACONS = mod("beacons");

        public static ITag.INamedTag<Block> forge(String name) {
            return BlockTags.bind(new ResourceLocation("forge", name).toString());
        }

        public static ITag.INamedTag<Block> mod(String name) {
            return BlockTags.bind(Variants.resourceLoc(name).toString());
        }
    }
}
