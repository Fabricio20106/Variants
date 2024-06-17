package com.junethewoods.variants.item.custom.stew;

import com.junethewoods.variants.Variants;
import com.junethewoods.variants.item.custom.stew.custom.*;
import com.junethewoods.variants.util.VSRegistries;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;

public class VSStewBehaviors {
    public static final DeferredRegister<StewBehavior> BEHAVIORS = DeferredRegister.create(VSRegistries.STEW_BEHAVIOR, Variants.MOD_ID);

    public static final RegistryObject<StewBehavior> DEFAULT = BEHAVIORS.register("default", DefaultStewBehavior::new);
    public static final RegistryObject<StewBehavior> APPLY_MOB_EFFECTS = BEHAVIORS.register("apply_mob_effects", ApplyMobEffectsBehavior::new);
    public static final RegistryObject<StewBehavior> CLEAR_MOB_EFFECTS = BEHAVIORS.register("clear_mob_effects", ClearMobEffectsBehavior::new);
    public static final RegistryObject<StewBehavior> DAMAGE_ENTITY = BEHAVIORS.register("damage_entity", DamageEntityBehavior::new);
    public static final RegistryObject<StewBehavior> EXPLODE = BEHAVIORS.register("explode", ExplodeBehavior::new);
    public static final RegistryObject<StewBehavior> IGNITE = BEHAVIORS.register("ignite", IgniteBehavior::new);
    public static final RegistryObject<StewBehavior> PLAY_SOUND = BEHAVIORS.register("play_sound", PlaySoundBehavior::new);
}
