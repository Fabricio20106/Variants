package com.junethewoods.variants.init;

import com.junethewoods.variants.common.Variants;
import net.minecraft.item.Item;
import net.minecraft.tags.ITag;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.ResourceLocation;

public final class VariantTags {
    public static final class Items {
        public static final ITag.INamedTag<Item> shulker_shells = mod("shulker_shells");

        private static ITag.INamedTag<Item> forge(String path) {
            return ItemTags.makeWrapperTag(new ResourceLocation("forge", path).toString());
        }

        private static ITag.INamedTag<Item> mod(String path) {
            return ItemTags.makeWrapperTag(new ResourceLocation(Variants.mod_id, path).toString());
        }

        private Items() {}
    }
    private VariantTags() {}
}
