package com.junethewoods.variants.sound;

import com.junethewoods.variants.Variants;
import net.minecraft.core.registries.Registries;
import net.minecraft.sounds.SoundEvent;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class VSSounds {
    public static final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(Registries.SOUND_EVENT, Variants.MOD_ID);

    public static final Supplier<SoundEvent> MUSIC_DISC_DOG = SOUNDS.register("music_disc.dog", () -> SoundEvent.createVariableRangeEvent(Variants.resourceLoc("music_disc.dog")));
    public static final Supplier<SoundEvent> SPYGLASS_USE = SOUNDS.register("item.spyglass.use", () -> SoundEvent.createVariableRangeEvent(Variants.resourceLoc("item.spyglass.use")));
    public static final Supplier<SoundEvent> SPYGLASS_STOP_USING = SOUNDS.register("item.spyglass.stop_using", () -> SoundEvent.createVariableRangeEvent(Variants.resourceLoc("item.spyglass.stop_using")));
}
