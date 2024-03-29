package com.junethewoods.variants.util;

import com.junethewoods.variants.Variants;
import net.minecraft.stats.IStatFormatter;
import net.minecraft.stats.Stats;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;

public class VSStats {
    public static final ResourceLocation KNOWLEDGE_BOOKS_USED = register("knowledge_books_used", IStatFormatter.DEFAULT);

    private static ResourceLocation register(String name, IStatFormatter statFormatter) {
        ResourceLocation registryName = Variants.resourceLoc(name);
        Registry.register(Registry.CUSTOM_STAT, name, registryName);
        Stats.CUSTOM.get(registryName, statFormatter);
        return registryName;
    }

    public static void init() {}
}
