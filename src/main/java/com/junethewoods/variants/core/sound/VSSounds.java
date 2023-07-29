package com.junethewoods.variants.core.sound;

import com.junethewoods.variants.core.Variants;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.ForgeRegistry;

public class VSSounds {
    public static SoundEvent MUSIC_DISC_DOG;
    public static SoundEvent MOOG_CITY;

    public static void registerSounds() {
        MUSIC_DISC_DOG = registerSound("music_disc.music_disc_dog");
        MOOG_CITY = registerSound("moog_city");
    }

    private static SoundEvent registerSound(String name) {
        ResourceLocation location = Variants.resourceLoc(name);
        SoundEvent event = new SoundEvent(location);
        event.setRegistryName(name);
        ForgeRegistries.SOUND_EVENTS.register(event);
        return event;
    }
}
