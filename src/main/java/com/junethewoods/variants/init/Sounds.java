package com.junethewoods.variants.init;

import com.junethewoods.variants.common.Variants;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.registries.ForgeRegistries;

public class Sounds {
    public static SoundEvent dog;

    public static void registerSounds() {
        dog = registerSound("music_disc.dog");
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
