package com.junethewoods.variants.util.tag;

import com.junethewoods.variants.Variants;
import com.junethewoods.variants.item.custom.stew.StewBehavior;
import net.minecraft.tags.ITag;
import net.minecraft.tags.ITagCollection;
import net.minecraft.tags.TagRegistryManager;
import net.minecraftforge.common.ForgeTagHandler;

public class StewBehaviorTags {
    public static ITag.INamedTag<StewBehavior> mod(String name) {
        return ForgeTagHandler.makeWrapperTag(Variants.resourceLoc("stew_behavior"), Variants.resourceLoc(name));
    }

    public static ITagCollection<StewBehavior> getAllTags() {
        return (ITagCollection<StewBehavior>) TagRegistryManager.get(Variants.resourceLoc("stew_behavior")).getAllTags();
    }
}
