package com.junethewoods.variants.core.sound;

import com.junethewoods.variants.core.Variants;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.ForgeRegistry;

public class VSSounds {
    public static SoundEvent MUSIC_DISC_DOG, MOOG_CITY;

    public static void registerSounds() {
        MUSIC_DISC_DOG = registerSound("music_disc.music_disc_dog");
        MOOG_CITY = registerSound("moog_city");
    }

    /**
     * Creates a sound event using {@link ForgeRegistries}.
     *
     * @param name     A {@link ResourceLocation} with the path to the sound
     * @return A {@link ForgeRegistry} with mod id "variants" and a path to the sound
     */
    private static SoundEvent registerSound(String name) {
        ResourceLocation location = Variants.resourceLoc(name);
        SoundEvent event = new SoundEvent(location);
        event.setRegistryName(name);
        ForgeRegistries.SOUND_EVENTS.register(event);
        return event;
    }
}
