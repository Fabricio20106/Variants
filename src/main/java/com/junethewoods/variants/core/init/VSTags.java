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
        public static final TagKey<Item> SHULKER_SHELLS = mod("shulker_shells");
        public static final TagKey<Item> POTS = mod("pots");
        public static final TagKey<Item> BOOKS = mod("books");
        public static final TagKey<Item> POTTED_PLANTS = mod("potted_plants");
        public static final TagKey<Item> POTTED_STUFF = mod("potted_stuff");
        public static final TagKey<Item> PAINTING_LOGS = mod("painting_logs");
        public static final TagKey<Item> ENDER_LOGS = mod("ender_logs");

        public static final TagKey<Item> INGOTS_EMPTY_SLOT = forge("ingots/empty_slot");

        private static TagKey<Item> forge(String path) {
            return ItemTags.create(new ResourceLocation("forge", path));
        }

        private static TagKey<Item> mod(String path) {
            return ItemTags.create(Variants.resourceLoc(path));
        }
    }

    public static final class Blocks {
        public static final TagKey<Block> POTTED_STUFF = mod("potted_stuff");
        public static final TagKey<Block> PAINTING_LOGS = mod("painting_logs");
        public static final TagKey<Block> ENDER_LOGS = mod("ender_logs");

        private static TagKey<Block> mod(String path) {
            return BlockTags.create(Variants.resourceLoc(path));
        }
    }
}
