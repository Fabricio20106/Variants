package com.junethewoods.variants.core.sound;

import com.junethewoods.variants.core.Variants;
import net.minecraft.client.audio.BackgroundMusicSelector;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.registries.ForgeRegistries;

public class VariantSounds {
    public static final BackgroundMusicSelector moog_city = new BackgroundMusicSelector(VariantSounds.moog_city_track, 20, 600 ,true);;
    public static SoundEvent dog;
    public static SoundEvent moog_city_track;

    public static void registerSounds() {
        dog = registerSound("music_disc.dog");
        moog_city_track = registerSound("moog_city");
    }

    private static SoundEvent registerSound(String name) {
        Variants.logger.info("Sounds Registered!");
        ResourceLocation location = new ResourceLocation(Variants.mod_id, name);
        SoundEvent event = new SoundEvent(location);
        event.setRegistryName(name);
        ForgeRegistries.SOUND_EVENTS.register(event);
        return event;
    }
}
