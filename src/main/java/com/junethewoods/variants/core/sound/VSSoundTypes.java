package com.junethewoods.variants.core.sound;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraftforge.common.util.ForgeSoundType;

import java.util.function.Supplier;

public class VSSoundTypes extends ForgeSoundType {
    public static final VSSoundTypes QUARTZ_LADDER = new VSSoundTypes(1.0F, 1.0F, () -> SoundEvents.LADDER_BREAK, () -> SoundEvents.LADDER_STEP,
            () -> SoundEvents.STONE_PLACE, () -> SoundEvents.STONE_HIT, () -> SoundEvents.LADDER_FALL);

    public VSSoundTypes(float volume, float pitch, Supplier<SoundEvent> breakSound, Supplier<SoundEvent> stepSound, Supplier<SoundEvent> placeSound, Supplier<SoundEvent> hitSound, Supplier<SoundEvent> fallSound) {
        super(volume, pitch, breakSound, stepSound, placeSound, hitSound, fallSound);
    }
}
