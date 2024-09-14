package melonystudios.variants.stew;

import com.google.common.collect.Lists;
import melonystudios.variants.Variants;
import melonystudios.variants.stew.custom.*;
import melonystudios.variants.util.VSRegistries;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;

public class VSStewBehaviors {
    public static final DeferredRegister<StewBehavior> BEHAVIORS = DeferredRegister.create(VSRegistries.CONSUME_BEHAVIOR, Variants.MOD_ID);

    public static final RegistryObject<StewBehavior> DEFAULT = BEHAVIORS.register("default", DefaultStewBehavior::new);
    public static final RegistryObject<StewBehavior> ADD_EXPERIENCE = BEHAVIORS.register("add_experience", AddExperienceBehavior::new);
    public static final RegistryObject<StewBehavior> APPLY_MOB_EFFECTS = BEHAVIORS.register("apply_mob_effects", () -> new ApplyMobEffectsBehavior(Lists.newArrayList()));
    public static final RegistryObject<StewBehavior> CLEAR_MOB_EFFECTS = BEHAVIORS.register("clear_mob_effects", ClearMobEffectsBehavior::new);
    public static final RegistryObject<StewBehavior> DAMAGE_ENTITY = BEHAVIORS.register("damage_entity", DamageEntityBehavior::new);
    public static final RegistryObject<StewBehavior> EXPLODE = BEHAVIORS.register("explode", ExplodeBehavior::new);
    public static final RegistryObject<StewBehavior> IGNITE = BEHAVIORS.register("ignite", IgniteBehavior::new);
    public static final RegistryObject<StewBehavior> MULTI_BEHAVIOR = BEHAVIORS.register("multi_behavior", MultiBehavior::new);
    public static final RegistryObject<StewBehavior> PLAY_SOUND = BEHAVIORS.register("play_sound", PlaySoundBehavior::new);
    public static final RegistryObject<StewBehavior> REMOVE_EFFECTS = BEHAVIORS.register("remove_effects", RemoveEffectsBehavior::new);
    public static final RegistryObject<StewBehavior> TELEPORT_ENTITY = BEHAVIORS.register("teleport_entity", TeleportEntityBehavior::new);
}
