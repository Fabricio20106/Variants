package melonystudios.variants.consumable;

import com.google.common.collect.Lists;
import melonystudios.variants.Variants;
import melonystudios.variants.consumable.custom.*;
import melonystudios.variants.util.VSRegistries;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;

public class VSConsumeBehaviors {
    public static final DeferredRegister<ConsumeBehavior> BEHAVIORS = DeferredRegister.create(VSRegistries.CONSUME_BEHAVIOR, Variants.MOD_ID);

    public static final RegistryObject<ConsumeBehavior> DEFAULT = BEHAVIORS.register("default", DefaultConsumeBehavior::new);
    public static final RegistryObject<ConsumeBehavior> ADD_EXPERIENCE = BEHAVIORS.register("add_experience", AddExperienceBehavior::new);
    public static final RegistryObject<ConsumeBehavior> APPLY_MOB_EFFECTS = BEHAVIORS.register("apply_mob_effects", () -> new ApplyMobEffectsBehavior(Lists.newArrayList()));
    public static final RegistryObject<ConsumeBehavior> CLEAR_MOB_EFFECTS = BEHAVIORS.register("clear_mob_effects", ClearMobEffectsBehavior::new);
    public static final RegistryObject<ConsumeBehavior> DAMAGE_ENTITY = BEHAVIORS.register("damage_entity", DamageEntityBehavior::new);
    public static final RegistryObject<ConsumeBehavior> EAT_ITEM = BEHAVIORS.register("eat_item", EatItemBehavior::new);
    public static final RegistryObject<ConsumeBehavior> EXPLODE = BEHAVIORS.register("explode", ExplodeBehavior::new);
    public static final RegistryObject<ConsumeBehavior> IGNITE = BEHAVIORS.register("ignite", IgniteBehavior::new);
    public static final RegistryObject<ConsumeBehavior> MULTI_BEHAVIOR = BEHAVIORS.register("multi_behavior", MultiBehavior::new);
    public static final RegistryObject<ConsumeBehavior> PLAY_SOUND = BEHAVIORS.register("play_sound", PlaySoundBehavior::new);
    public static final RegistryObject<ConsumeBehavior> REMOVE_EFFECTS = BEHAVIORS.register("remove_effects", RemoveEffectsBehavior::new);
    public static final RegistryObject<ConsumeBehavior> TELEPORT_ENTITY = BEHAVIORS.register("teleport_entity", TeleportEntityBehavior::new);
}
