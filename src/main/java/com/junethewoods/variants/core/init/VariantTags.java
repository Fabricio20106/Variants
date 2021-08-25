package com.junethewoods.variants.core.init;

import com.junethewoods.variants.core.Variants;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ITag;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.ResourceLocation;

public final class VariantTags {
    public static final class Items {
        public static final ITag.INamedTag<Item> shulker_shells = mod("shulker_shells");
        public static final ITag.INamedTag<Item> pots = mod("pots");
        public static final ITag.INamedTag<Item> books = mod("books");
        public static final ITag.INamedTag<Item> potted_plants = mod("potted_plants");
        public static final ITag.INamedTag<Item> potted_stuff = mod("potted_stuff");
        public static final ITag.INamedTag<Item> painting_logs = mod("painting_logs");
        public static final ITag.INamedTag<Item> ender_logs = mod("ender_logs");

        private static ITag.INamedTag<Item> forge(String path) {
            return ItemTags.makeWrapperTag(new ResourceLocation("forge", path).toString());
        }

        private static ITag.INamedTag<Item> mod(String path) {
            return ItemTags.makeWrapperTag(new ResourceLocation(Variants.mod_id, path).toString());
        }

        private Items() {}
    }
    public static final class Blocks {
        public static final ITag.INamedTag<Block> potted_stuff = mod("potted_stuff");
        public static final ITag.INamedTag<Block> painting_logs = mod("painting_logs");
        public static final ITag.INamedTag<Block> ender_logs = mod("ender_logs");

        private static ITag.INamedTag<Block> mod(String path) {
            return BlockTags.makeWrapperTag(new ResourceLocation(Variants.mod_id, path).toString());
        }

        private Blocks() {}
    }
    private VariantTags() {}
}
