package com.junethewoods.variants.sound;

import com.junethewoods.variants.Variants;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class VSSounds {
    public static final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, Variants.MOD_ID);

    public static final RegistryObject<SoundEvent> MUSIC_DISC_DOG = SOUNDS.register("music_disc.dog", () -> new SoundEvent(Variants.resourceLoc("music_disc.dog")));

    // Entities
    public static final RegistryObject<SoundEvent> FISH_AMBIENT = SOUNDS.register("entity.fish.ambient", () -> new SoundEvent(Variants.resourceLoc("entity.fish.ambient")));
    public static final RegistryObject<SoundEvent> FISH_FLOP = SOUNDS.register("entity.fish.flop", () -> new SoundEvent(Variants.resourceLoc("entity.fish.flop")));
    public static final RegistryObject<SoundEvent> FISH_HURT = SOUNDS.register("entity.fish.hurt", () -> new SoundEvent(Variants.resourceLoc("entity.fish.hurt")));
    public static final RegistryObject<SoundEvent> FISH_DEATH = SOUNDS.register("entity.fish.death", () -> new SoundEvent(Variants.resourceLoc("entity.fish.death")));
    public static final RegistryObject<SoundEvent> DRAGON_BREATH_BOTTLE_THROW = SOUNDS.register("entity.dragon_breath_bottle.throw", () -> new SoundEvent(Variants.resourceLoc("entity.dragon_breath_bottle.throw")));

    // Items
    public static final RegistryObject<SoundEvent> SPYGLASS_USE = SOUNDS.register("item.spyglass.use", () -> new SoundEvent(Variants.resourceLoc("item.spyglass.use")));
    public static final RegistryObject<SoundEvent> SPYGLASS_STOP_USING = SOUNDS.register("item.spyglass.stop_using", () -> new SoundEvent(Variants.resourceLoc("item.spyglass.stop_using")));

    // Unused as of now because the game crashes when using these (don't know why, coding is weird).
    public static final RegistryObject<SoundEvent> ARMOR_EQUIP_EMPTY_SLOT = SOUNDS.register("item.armor.equip_empty_slot", () -> new SoundEvent(Variants.resourceLoc("item.armor.equip_empty_slot")));
    public static final RegistryObject<SoundEvent> ARMOR_EQUIP_EMERALD = SOUNDS.register("item.armor.equip_emerald", () -> new SoundEvent(Variants.resourceLoc("item.armor.equip_emerald")));
    public static final RegistryObject<SoundEvent> ARMOR_EQUIP_QUARTZ = SOUNDS.register("item.armor.equip_quartz", () -> new SoundEvent(Variants.resourceLoc("item.armor.equip_quartz")));
    public static final RegistryObject<SoundEvent> ARMOR_EQUIP_COPPER = SOUNDS.register("item.armor.equip_copper", () -> new SoundEvent(Variants.resourceLoc("item.armor.equip_copper")));
    public static final RegistryObject<SoundEvent> ARMOR_EQUIP_PHANTOM_MEMBRANE = SOUNDS.register("item.armor.equip_phantom_membrane", () -> new SoundEvent(Variants.resourceLoc("item.armor.equip_phantom_membrane")));
    public static final RegistryObject<SoundEvent> ARMOR_EQUIP_RABBIT_HIDE = SOUNDS.register("item.armor.equip_rabbit_hide", () -> new SoundEvent(Variants.resourceLoc("item.armor.equip_rabbit_hide")));
    public static final RegistryObject<SoundEvent> ARMOR_EQUIP_WOOL = SOUNDS.register("item.armor.equip_wool", () -> new SoundEvent(Variants.resourceLoc("item.armor.equip_wool")));
}
