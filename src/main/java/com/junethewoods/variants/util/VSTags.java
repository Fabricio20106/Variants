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

        public static final ITag.INamedTag<Item> GLASS_GLOW_BLACK = forge("glass/glow_black");
        public static final ITag.INamedTag<Item> GLASS_PANES_GLOW_BLACK = forge("glass_panes/glow_black");

        public static final ITag.INamedTag<Item> CROPS_GOLDEN_CARROT = forge("crops/golden_carrot");
        public static final ITag.INamedTag<Item> CROPS_WARPED_WART = forge("crops/warped_wart");
        public static final ITag.INamedTag<Item> CROPS_ENDER_WART = forge("crops/ender_wart");

        public static final ITag.INamedTag<Item> INGOTS_EXPOSED_COPPER = forge("ingots/exposed_copper");
        public static final ITag.INamedTag<Item> INGOTS_WEATHERED_COPPER = forge("ingots/weathered_copper");
        public static final ITag.INamedTag<Item> INGOTS_OXIDIZED_COPPER = forge("ingots/oxidized_copper");
        public static final ITag.INamedTag<Item> INGOTS_RED_NETHER_BRICK = forge("ingots/red_nether_brick");

        public static final ITag.INamedTag<Item> NUGGETS_QUARTZ = forge("nuggets/quartz");
        public static final ITag.INamedTag<Item> NUGGETS_PURPLE_IRON = forge("nuggets/purple_iron");

        public static final ITag.INamedTag<Item> RAW_MATERIALS = forge("raw_materials");
        public static final ITag.INamedTag<Item> RAW_MATERIALS_NETHERITE_SCRAP = forge("raw_materials/netherite_scrap");

        public static final ITag.INamedTag<Item> FISHING_WOODEN_RODS = forge("rods/obtainable_through_fishing");
        public static final ITag.INamedTag<Item> RODS_DEBUG_WOODEN = forge("rods/debug_wooden");
        public static final ITag.INamedTag<Item> RODS_NETHERITE = forge("rods/netherite");
        public static final ITag.INamedTag<Item> RODS_SOUL_BLAZE = forge("rods/soul_blaze");

        public static final ITag.INamedTag<Item> DYES_GLOW_BLACK = forge("dyes/glow_black");

        public static final ITag.INamedTag<Item> DUSTS_GUNPOWDER = forge("dusts/gunpowder");
        public static final ITag.INamedTag<Item> DUSTS_SUGAR = forge("dusts/sugar");
        public static final ITag.INamedTag<Item> DUSTS_BLAZE = forge("dusts/blaze");
        public static final ITag.INamedTag<Item> DUSTS_SOUL_BLAZE = forge("dusts/soul_blaze");
        public static final ITag.INamedTag<Item> DUSTS_ELDER_PRISMARINE = forge("dusts/elder_prismarine");

        public static final ITag.INamedTag<Item> GEMS_ELDER_PRISMARINE = forge("gems/elder_prismarine");

        public static final ITag.INamedTag<Item> ARMORS_BOOTS = forge("armors/boots");
        public static final ITag.INamedTag<Item> TOOLS_HOES = forge("tools/hoes");
        public static final ITag.INamedTag<Item> BONE_MEALS = forge("bone_meals");

        // Variants' Tags
        public static final ITag.INamedTag<Item> CRAFTING_MATERIALS = mod("crafting_materials");
        public static final ITag.INamedTag<Item> CM_DIORITE = mod("crafting_materials/diorite");
        public static final ITag.INamedTag<Item> CM_GRANITE = mod("crafting_materials/granite");
        public static final ITag.INamedTag<Item> CM_ANDESITE = mod("crafting_materials/andesite");
        public static final ITag.INamedTag<Item> CM_MAGMA_BLOCK = mod("crafting_materials/magma_block");
        public static final ITag.INamedTag<Item> CM_END_STONE = mod("crafting_materials/end_stone");

        public static final ITag.INamedTag<Item> PAINTING_DOORS = mod("painting_doors");
        public static final ITag.INamedTag<Item> PAINTING_TRAPDOORS = mod("painting_trapdoors");
        public static final ITag.INamedTag<Item> PAINTING_LOGS = mod("painting_logs");
        public static final ITag.INamedTag<Item> ENDERWOOD_STEMS = mod("enderwood_stems");
        public static final ITag.INamedTag<Item> CAULDRONS = mod("cauldrons");
        public static final ITag.INamedTag<Item> BEACONS = mod("beacons");
        public static final ITag.INamedTag<Item> SHULKER_SHELLS = mod("shulker_shells");
        public static final ITag.INamedTag<Item> POTS = mod("pots");
        public static final ITag.INamedTag<Item> SPYGLASSES = mod("spyglasses");
        public static final ITag.INamedTag<Item> FLOWER_POTS = mod("flower_pots");

        public static final ITag.INamedTag<Item> BOWL_FOODS = mod("bowl_foods");
        public static final ITag.INamedTag<Item> BOWL_FOODS_MUSHROOM = mod("bowl_foods/mushroom");
        public static final ITag.INamedTag<Item> BOWL_FOODS_BEETROOT = mod("bowl_foods/beetroot");
        public static final ITag.INamedTag<Item> BOWL_FOODS_RABBIT = mod("bowl_foods/rabbit");
        public static final ITag.INamedTag<Item> BOWL_FOODS_FUNGI = mod("bowl_foods/fungi");
        public static final ITag.INamedTag<Item> BOWL_FOODS_END_FUNGI = mod("bowl_foods/end_fungi");
        public static final ITag.INamedTag<Item> BOWL_FOODS_ALJAN_FUNGI = mod("bowl_foods/aljan_fungi");

        // Melony (convention) Tags
        public static final ITag.INamedTag<Item> BOOKS = melony("books");
        public static final ITag.INamedTag<Item> BOOKSHELVES = melony("bookshelves");
        public static final ITag.INamedTag<Item> SHIELDS = melony("shields");
        public static final ITag.INamedTag<Item> BOWLS = melony("bowls");
        public static final ITag.INamedTag<Item> FISHING_BOWLS = melony("bowls/obtainable_through_fishing");
        public static final ITag.INamedTag<Item> WOODEN_BOWLS = melony("bowls/wooden");
        public static final ITag.INamedTag<Item> CATLIKE_TAME_ITEMS = melony("catlike_tame_items");

        public static ITag.INamedTag<Item> forge(String name) {
            return ItemTags.bind(new ResourceLocation("forge", name).toString());
        }

        public static ITag.INamedTag<Item> melony(String name) {
            return ItemTags.bind(new ResourceLocation("melony", name).toString());
        }

        public static ITag.INamedTag<Item> mod(String name) {
            return ItemTags.bind(Variants.resourceLoc(name).toString());
        }
    }

    public static class Blocks {
        // Forge Tags
        public static final ITag.INamedTag<Block> STORAGE_BLOCKS_NETHERITE_SCRAP = forge("storage_blocks/netherite_scrap");

        public static final ITag.INamedTag<Block> GLASS_GLOW_BLACK = forge("glass/glow_black");
        public static final ITag.INamedTag<Block> GLASS_PANES_GLOW_BLACK = forge("glass_panes/glow_black");

        // Variants' Tags
        public static final ITag.INamedTag<Block> PAINTING_DOORS = mod("painting_doors");
        public static final ITag.INamedTag<Block> PAINTING_TRAPDOORS = mod("painting_trapdoors");
        public static final ITag.INamedTag<Block> PAINTING_LOGS = mod("painting_logs");
        public static final ITag.INamedTag<Block> ENDERWOOD_STEMS = mod("enderwood_stems");
        public static final ITag.INamedTag<Block> BOOKSHELVES = mod("bookshelves");
        public static final ITag.INamedTag<Block> CAULDRONS = mod("cauldrons");
        public static final ITag.INamedTag<Block> BEACONS = mod("beacons");
        public static final ITag.INamedTag<Block> NETHER_WART_PLACEABLE_ON = mod("nether_wart_plantable_on");
        public static final ITag.INamedTag<Block> ENDER_WART_PLANTABLE_ON = mod("ender_wart_plantable_on");
        public static final ITag.INamedTag<Block> GOLDEN_CARROTS_PLANTABLE_ON = mod("golden_carrots_plantable_on");
        public static final ITag.INamedTag<Block> END_PLANTS_PLANTABLE_ON = mod("end_plants_plantable_on");
        public static final ITag.INamedTag<Block> WARPING_VINES_CAN_PLACE_ON = mod("warping_vines_feature_can_place_on");
        public static final ITag.INamedTag<Block> CHORUS_FLOWER_PLANTABLE_ON = mod("chorus_flower_plantable_on");

        // Melony (convention) Tags
        public static final ITag.INamedTag<Block> DEEPSLATE_REPLACEABLES = melony("deepslate_replaceables");
        public static final ITag.INamedTag<Block> FARMLAND = melony("farmland");
        public static final ITag.INamedTag<Block> CONDUIT_FRAME_BLOCKS = melony("conduit_frame_blocks");

        public static ITag.INamedTag<Block> forge(String name) {
            return BlockTags.bind(new ResourceLocation("forge", name).toString());
        }

        public static ITag.INamedTag<Block> melony(String name) {
            return BlockTags.bind(new ResourceLocation("melony", name).toString());
        }

        public static ITag.INamedTag<Block> mod(String name) {
            return BlockTags.bind(Variants.resourceLoc(name).toString());
        }
    }
}
