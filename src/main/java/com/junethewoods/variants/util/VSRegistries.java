package com.junethewoods.variants.registry;

import com.junethewoods.variants.Variants;
import com.junethewoods.variants.registry.poisoningtype.PoisoningType;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.RegistryBuilder;

public class VSRegistries {
    public static final IForgeRegistry<PoisoningType> POISONING_TYPES = new RegistryBuilder<PoisoningType>().setType(PoisoningType.class).setName(Variants.resourceLoc("poisoning_types")).create();

    public static void init() {}
}
