package com.junethewoods.variants.item.custom.stew.behavior;

import com.junethewoods.variants.Variants;
import com.junethewoods.variants.item.custom.stew.custom.DefaultStewBehavior;
import com.junethewoods.variants.util.VSRegistries;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;

public class PoisoningBehaviors {
    public static final DeferredRegister<PoisoningBehavior> BEHAVIORS = DeferredRegister.create(VSRegistries.POISONING_BEHAVIOR, Variants.MOD_ID);

    public static final RegistryObject<PoisoningBehavior> DEFAULT = BEHAVIORS.register("default", DefaultStewBehavior::new);
}
