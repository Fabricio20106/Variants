package com.junethewoods.variants.util;

import com.junethewoods.variants.Variants;
import com.junethewoods.variants.item.custom.poisoning.PoisoningType;
import com.junethewoods.variants.item.custom.stew.StewBehavior;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.registry.Registry;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.RegistryBuilder;

public class VSRegistries {
    public static final IForgeRegistry<PoisoningType> POISONING_TYPE = new RegistryBuilder<PoisoningType>().setType(PoisoningType.class).setName(Variants.resourceLoc("poisoning_type")).create();
    public static final IForgeRegistry<StewBehavior> STEW_BEHAVIOR = new RegistryBuilder<StewBehavior>().setType(StewBehavior.class).setName(Variants.resourceLoc("stew_behavior")).create();

    public static final RegistryKey<Registry<StewBehavior>> STEW_BEHAVIOR_REG = RegistryKey.createRegistryKey(Variants.resourceLoc("stew_behavior"));

    public static void init() {}
}
