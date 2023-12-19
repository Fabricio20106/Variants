package com.junethewoods.variants.core.init;

import com.junethewoods.variants.core.Variants;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public final class VSTags {
    public static final class Items {
        public static final TagKey<Item> INGOTS_EMPTY_SLOT = forge("ingots/empty_slot");

        private static TagKey<Item> forge(String path) {
            return ItemTags.create(new ResourceLocation("forge", path));
        }

        private static TagKey<Item> mod(String path) {
            return ItemTags.create(Variants.resourceLoc(path));
        }
    }

    public static final class Blocks {
        public static final TagKey<Block> CRAFTING_TABLES = mod("crafting_tables");

        private static TagKey<Block> mod(String path) {
            return BlockTags.create(Variants.resourceLoc(path));
        }
    }
}
