package com.junethewoods.variants.util.tag;

import com.junethewoods.variants.Variants;
import com.junethewoods.variants.item.custom.stew.StewBehavior;
import com.junethewoods.variants.util.VSRegistries;
import net.minecraft.tags.ITag;
import net.minecraft.tags.ITagCollection;
import net.minecraft.tags.TagRegistry;
import net.minecraft.tags.TagRegistryManager;
import net.minecraftforge.common.ForgeTagHandler;

public class StewBehaviorTags {
    protected static final TagRegistry<StewBehavior> HELPER = TagRegistryManager.create(Variants.resourceLoc("stew_behavior"), collectionSupplier -> collectionSupplier.getCustomTypeCollection(VSRegistries.STEW_BEHAVIOR));

    public static ITag.INamedTag<StewBehavior> mod(String name) {
        return ForgeTagHandler.makeWrapperTag(Variants.resourceLoc("stew_behavior"), Variants.resourceLoc(name));
    }

    public static ITagCollection<StewBehavior> getAllTags() {
        return HELPER.getAllTags();
    }
}
