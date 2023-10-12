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
        public static final ITag.INamedTag<Item> STORAGE_BLOCKS_RAW_DEBRIS = forge("storage_blocks/raw_debris");

        public static final ITag.INamedTag<Item> INGOTS_EXPOSED_COPPER = forge("ingots/exposed_copper");
        public static final ITag.INamedTag<Item> INGOTS_WEATHERED_COPPER = forge("ingots/weathered_copper");
        public static final ITag.INamedTag<Item> INGOTS_OXIDIZED_COPPER = forge("ingots/oxidized_copper");

        public static final ITag.INamedTag<Item> NUGGETS_PURPLE_IRON = forge("nuggets/purple_iron");

        public static final ITag.INamedTag<Item> RAW_MATERIALS = forge("raw_materials");
        public static final ITag.INamedTag<Item> RAW_MATERIALS_RAW_DEBRIS = forge("raw_materials/debris");

        public static final ITag.INamedTag<Item> RODS_DEBUG_WOODEN = forge("rods/debug_wooden");
        public static final ITag.INamedTag<Item> RODS_NETHERITE = forge("rods/netherite");

        // Variants' Tags
        public static final ITag.INamedTag<Item> CRAFTING_MATERIALS = mod("crafting_materials");
        public static final ITag.INamedTag<Item> CM_DIORITE = mod("crafting_materials/diorite");
        public static final ITag.INamedTag<Item> CM_GRANITE = mod("crafting_materials/granite");
        public static final ITag.INamedTag<Item> CM_ANDESITE = mod("crafting_materials/andesite");

        public static final ITag.INamedTag<Item> PAINTING_DOORS = mod("painting_doors");

        public static ITag.INamedTag<Item> forge(String name) {
            return ItemTags.bind(new ResourceLocation("forge", name).toString());
        }

        public static ITag.INamedTag<Item> mod(String name) {
            return ItemTags.bind(Variants.resourceLoc(name).toString());
        }
    }

    public static class Blocks {
        public static final ITag.INamedTag<Block> STORAGE_BLOCKS_RAW_DEBRIS = forge("storage_blocks/raw_debris");

        public static ITag.INamedTag<Block> forge(String name) {
            return BlockTags.bind(new ResourceLocation("forge", name).toString());
        }
    }
}
