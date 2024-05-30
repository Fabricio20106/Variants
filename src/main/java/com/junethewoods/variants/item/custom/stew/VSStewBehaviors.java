package com.junethewoods.variants.item.custom.stew;

import com.junethewoods.variants.Variants;
import com.junethewoods.variants.item.custom.stew.custom.DefaultStewBehavior;
import com.junethewoods.variants.item.custom.stew.custom.EffectStewBehavior;
import com.junethewoods.variants.item.custom.stew.custom.LavaStewBehavior;
import com.junethewoods.variants.item.custom.stew.custom.MilkStewBehavior;
import com.junethewoods.variants.util.VSRegistries;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;

public class VSStewBehaviors {
    public static final DeferredRegister<StewBehavior> BEHAVIORS = DeferredRegister.create(VSRegistries.STEW_BEHAVIOR, Variants.MOD_ID);

    public static final RegistryObject<StewBehavior> DEFAULT = BEHAVIORS.register("default", DefaultStewBehavior::new);
    public static final RegistryObject<StewBehavior> MILK = BEHAVIORS.register("milk", MilkStewBehavior::new);
    public static final RegistryObject<StewBehavior> LAVA = BEHAVIORS.register("lava", LavaStewBehavior::new);
    public static final RegistryObject<StewBehavior> EFFECT = BEHAVIORS.register("effect", EffectStewBehavior::new);
}
