package melonystudios.variants.sound;

import melonystudios.variants.Variants;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class VSSounds {
    public static final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, Variants.MOD_ID);

    // Music
    public static final RegistryObject<SoundEvent> MUSIC_DISC_DOG = SOUNDS.register("music_disc.dog", () -> new SoundEvent(Variants.variants("music_disc.dog")));
    public static final RegistryObject<SoundEvent> PAINTINGWOOD_FOREST_MUSIC = SOUNDS.register("music.overworld.paintingwood_forest", () -> new SoundEvent(Variants.variants("music.overworld.paintingwood_forest")));
    public static final RegistryObject<SoundEvent> AZURE_FIELDS_MUSIC = SOUNDS.register("music.overworld.azure_fields", () -> new SoundEvent(Variants.variants("music.overworld.azure_fields")));

    // Entities
    public static final RegistryObject<SoundEvent> FISH_AMBIENT = SOUNDS.register("entity.fish.ambient", () -> new SoundEvent(Variants.variants("entity.fish.ambient")));
    public static final RegistryObject<SoundEvent> FISH_FLOP = SOUNDS.register("entity.fish.flop", () -> new SoundEvent(Variants.variants("entity.fish.flop")));
    public static final RegistryObject<SoundEvent> FISH_HURT = SOUNDS.register("entity.fish.hurt", () -> new SoundEvent(Variants.variants("entity.fish.hurt")));
    public static final RegistryObject<SoundEvent> FISH_DEATH = SOUNDS.register("entity.fish.death", () -> new SoundEvent(Variants.variants("entity.fish.death")));
    public static final RegistryObject<SoundEvent> DRAGON_BREATH_BOTTLE_THROW = SOUNDS.register("entity.dragon_breath_bottle.throw", () -> new SoundEvent(Variants.variants("entity.dragon_breath_bottle.throw")));
    public static final RegistryObject<SoundEvent> THROWN_BOTTLE_THROW = SOUNDS.register("entity.thrown_bottle.throw", () -> new SoundEvent(Variants.variants("entity.thrown_bottle.throw")));
    public static final RegistryObject<SoundEvent> THROWN_BOTTLE_SHATTER = SOUNDS.register("entity.thrown_bottle.shatter", () -> new SoundEvent(Variants.variants("entity.thrown_bottle.shatter")));

    // Items
    public static final RegistryObject<SoundEvent> SPYGLASS_USE = SOUNDS.register("item.spyglass.use", () -> new SoundEvent(Variants.variants("item.spyglass.use")));
    public static final RegistryObject<SoundEvent> SPYGLASS_STOP_USING = SOUNDS.register("item.spyglass.stop_using", () -> new SoundEvent(Variants.variants("item.spyglass.stop_using")));
    public static final RegistryObject<SoundEvent> DYE_STAIN = SOUNDS.register("item.dye.stain", () -> new SoundEvent(Variants.variants("item.dye.stain")));
    public static final RegistryObject<SoundEvent> INK_SAC_SPLOTCH = SOUNDS.register("item.ink_sac.splotch", () -> new SoundEvent(Variants.variants("item.ink_sac.splotch")));

    // Blocks
    public static final RegistryObject<SoundEvent> GLASS_SHATTER = SOUNDS.register("block.glass.shatter", () -> new SoundEvent(Variants.variants("block.glass.shatter")));

    // Consume Behaviors
    public static final RegistryObject<SoundEvent> PLAY_SOUND_BEHAVIOR_DEFAULT = SOUNDS.register("consume_behavior.play_sound.default", () -> new SoundEvent(Variants.variants("consume_behavior.play_sound.default")));

    // Unused as of now because the game crashes when using these (don't know why, Minecraft is weird).
    public static final RegistryObject<SoundEvent> ARMOR_EQUIP_EMPTY_SLOT = SOUNDS.register("item.armor.equip_empty_slot", () -> new SoundEvent(Variants.variants("item.armor.equip_empty_slot")));
    public static final RegistryObject<SoundEvent> ARMOR_EQUIP_EMERALD = SOUNDS.register("item.armor.equip_emerald", () -> new SoundEvent(Variants.variants("item.armor.equip_emerald")));
    public static final RegistryObject<SoundEvent> ARMOR_EQUIP_QUARTZ = SOUNDS.register("item.armor.equip_quartz", () -> new SoundEvent(Variants.variants("item.armor.equip_quartz")));
    public static final RegistryObject<SoundEvent> ARMOR_EQUIP_COPPER = SOUNDS.register("item.armor.equip_copper", () -> new SoundEvent(Variants.variants("item.armor.equip_copper")));
    public static final RegistryObject<SoundEvent> ARMOR_EQUIP_PHANTOM_MEMBRANE = SOUNDS.register("item.armor.equip_phantom_membrane", () -> new SoundEvent(Variants.variants("item.armor.equip_phantom_membrane")));
    public static final RegistryObject<SoundEvent> ARMOR_EQUIP_RABBIT_HIDE = SOUNDS.register("item.armor.equip_rabbit_hide", () -> new SoundEvent(Variants.variants("item.armor.equip_rabbit_hide")));
    public static final RegistryObject<SoundEvent> ARMOR_EQUIP_WOOL = SOUNDS.register("item.armor.equip_wool", () -> new SoundEvent(Variants.variants("item.armor.equip_wool")));
}
