package com.junethewoods.variants.util;

import com.junethewoods.variants.Variants;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class VSTags {
    public static class Items {
        // Forge Tags
        public static final TagKey<Item> STORAGE_BLOCKS_NETHERITE_SCRAP = forge("storage_blocks/netherite_scrap");

        public static final TagKey<Item> ORES_IN_GROUND_END_STONE = forge("ores_in_ground/end_stone");

        public static final TagKey<Item> GLASS_GLOW_BLACK = forge("glass/glow_black");
        public static final TagKey<Item> GLASS_PANES_GLOW_BLACK = forge("glass_panes/glow_black");

        public static final TagKey<Item> INGOTS_EXPOSED_COPPER = forge("ingots/exposed_copper");
        public static final TagKey<Item> INGOTS_WEATHERED_COPPER = forge("ingots/weathered_copper");
        public static final TagKey<Item> INGOTS_OXIDIZED_COPPER = forge("ingots/oxidized_copper");
        public static final TagKey<Item> INGOTS_RED_NETHER_BRICK = forge("ingots/red_nether_brick");

        public static final TagKey<Item> NUGGETS_QUARTZ = forge("nuggets/quartz");
        public static final TagKey<Item> NUGGETS_PURPLE_IRON = forge("nuggets/purple_iron");

        public static final TagKey<Item> RAW_MATERIALS_NETHERITE_SCRAP = forge("raw_materials/netherite_scrap");

        public static final TagKey<Item> FISHING_WOODEN_RODS = forge("rods/obtainable_through_fishing");
        public static final TagKey<Item> RODS_DEBUG_WOODEN = forge("rods/debug_wooden");
        public static final TagKey<Item> RODS_NETHERITE = forge("rods/netherite");
        public static final TagKey<Item> RODS_SOUL_BLAZE = forge("rods/soul_blaze");

        public static final TagKey<Item> DYES_GLOW_BLACK = forge("dyes/glow_black");

        public static final TagKey<Item> DUSTS_GUNPOWDER = forge("dusts/gunpowder");
        public static final TagKey<Item> DUSTS_SUGAR = forge("dusts/sugar");
        public static final TagKey<Item> DUSTS_BLAZE = forge("dusts/blaze");
        public static final TagKey<Item> DUSTS_SOUL_BLAZE = forge("dusts/soul_blaze");
        public static final TagKey<Item> DUSTS_ELDER_PRISMARINE = forge("dusts/elder_prismarine");

        public static final TagKey<Item> GEMS_ELDER_PRISMARINE = forge("gems/elder_prismarine");

        public static final TagKey<Item> BONE_MEALS = forge("bone_meals");

        // Variants' Tags
        public static final TagKey<Item> CRAFTING_MATERIALS = mod("crafting_materials");
        public static final TagKey<Item> CM_DIORITE = mod("crafting_materials/diorite");
        public static final TagKey<Item> CM_GRANITE = mod("crafting_materials/granite");
        public static final TagKey<Item> CM_ANDESITE = mod("crafting_materials/andesite");
        public static final TagKey<Item> CM_MAGMA_BLOCK = mod("crafting_materials/magma_block");
        public static final TagKey<Item> CM_END_STONE = mod("crafting_materials/end_stone");

        public static final TagKey<Item> PAINTING_DOORS = mod("painting_doors");
        public static final TagKey<Item> PAINTING_TRAPDOORS = mod("painting_trapdoors");
        public static final TagKey<Item> PAINTING_LOGS = mod("painting_logs");
        public static final TagKey<Item> ENDERWOOD_STEMS = mod("enderwood_stems");
        public static final TagKey<Item> CAULDRONS = mod("cauldrons");
        public static final TagKey<Item> BEACONS = mod("beacons");
        public static final TagKey<Item> SHULKER_SHELLS = mod("shulker_shells");
        public static final TagKey<Item> BOOKS = mod("books");
        public static final TagKey<Item> BOOKSHELVES = mod("bookshelves");
        public static final TagKey<Item> POTS = mod("pots");
        public static final TagKey<Item> SHIELDS = mod("shields");
        public static final TagKey<Item> SPYGLASSES = mod("spyglasses");
        public static final TagKey<Item> BOWLS = mod("bowls");
        public static final TagKey<Item> FISHING_BOWLS = mod("bowls/obtainable_through_fishing");
        public static final TagKey<Item> WOODEN_BOWLS = mod("bowls/wooden");
        public static final TagKey<Item> CATLIKE_TAME_ITEMS = mod("catlike_tame_items");

        public static TagKey<Item> forge(String name) {
            return ItemTags.create(new ResourceLocation("forge", name));
        }

        public static TagKey<Item> mod(String name) {
            return ItemTags.create(Variants.resourceLoc(name));
        }
    }

    public static class Blocks {
        // Forge Tags
        public static final TagKey<Block> STORAGE_BLOCKS_NETHERITE_SCRAP = forge("storage_blocks/netherite_scrap");

        public static final TagKey<Block> GLASS_GLOW_BLACK = forge("glass/glow_black");
        public static final TagKey<Block> GLASS_PANES_GLOW_BLACK = forge("glass_panes/glow_black");

        public static final TagKey<Block> DEEPSLATE_REPLACEABLES = forge("deepslate_replaceables");

        // Variants' Tags
        public static final TagKey<Block> PAINTING_DOORS = mod("painting_doors");
        public static final TagKey<Block> PAINTING_TRAPDOORS = mod("painting_trapdoors");
        public static final TagKey<Block> PAINTING_LOGS = mod("painting_logs");
        public static final TagKey<Block> ENDERWOOD_STEMS = mod("enderwood_stems");
        public static final TagKey<Block> BOOKSHELVES = mod("bookshelves");
        public static final TagKey<Block> CAULDRONS = mod("cauldrons");
        public static final TagKey<Block> BEACONS = mod("beacons");
        public static final TagKey<Block> FARMLAND = mod("farmland");
        public static final TagKey<Block> CONDUIT_FRAME_BLOCKS = mod("conduit_frame_blocks");
        public static final TagKey<Block> WARPED_WART_PLANTABLE_ON = mod("warped_wart_plantable_on");
        public static final TagKey<Block> ENDER_WART_PLANTABLE_ON = mod("ender_wart_plantable_on");
        public static final TagKey<Block> GOLDEN_CARROTS_PLANTABLE_ON = mod("golden_carrots_plantable_on");
        public static final TagKey<Block> END_PLANTS_PLANTABLE_ON = mod("end_plants_plantable_on");

        public static TagKey<Block> forge(String name) {
            return BlockTags.create(new ResourceLocation("forge", name));
        }

        public static TagKey<Block> mod(String name) {
            return BlockTags.create(Variants.resourceLoc(name));
        }
    }
}
