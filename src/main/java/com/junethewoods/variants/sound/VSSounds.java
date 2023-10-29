package com.junethewoods.variants.sound;

import com.junethewoods.variants.Variants;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class VSSounds {
    public static final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, Variants.MOD_ID);

    public static final RegistryObject<SoundEvent> MUSIC_DISC_DOG = SOUNDS.register("music_disc.dog", () -> new SoundEvent(Variants.resourceLoc("music_disc.dog")));
}