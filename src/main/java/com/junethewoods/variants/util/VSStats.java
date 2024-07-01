package com.junethewoods.variants.util;

import com.junethewoods.variants.Variants;
import net.minecraft.stats.IStatFormatter;
import net.minecraft.stats.Stats;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;

public class VSStats {
    public static final ResourceLocation KNOWLEDGE_BOOKS_USED = register("knowledge_books_used");
    public static final ResourceLocation SPAWNERS_PICKED_UP = register("spawners_picked_up");

    private static ResourceLocation register(String name) {
        ResourceLocation registryName = Variants.resourceLoc(name);
        Registry.register(Registry.CUSTOM_STAT, name, registryName);
        Stats.CUSTOM.get(registryName, IStatFormatter.DEFAULT);
        return registryName;
    }

    public static void init() {}
}
