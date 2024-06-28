package com.junethewoods.variants.util;

import com.junethewoods.variants.Variants;
import com.junethewoods.variants.item.custom.poisoning.PoisoningType;
import com.junethewoods.variants.item.custom.stew.StewBehavior;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.RegistryBuilder;

public class VSRegistries {
    public static final IForgeRegistry<PoisoningType> POISONING_TYPE = new RegistryBuilder<PoisoningType>().setType(PoisoningType.class).tagFolder("poisoning_type").setName(Variants.resourceLoc("poisoning_type")).create();
    public static final IForgeRegistry<StewBehavior> STEW_BEHAVIOR = new RegistryBuilder<StewBehavior>().setType(StewBehavior.class).tagFolder("stew_behavior").setName(Variants.resourceLoc("stew_behavior")).create();

    public static void init() {}
}
