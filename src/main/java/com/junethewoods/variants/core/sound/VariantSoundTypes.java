package com.junethewoods.variants.core.sound;

import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraftforge.common.util.ForgeSoundType;

import java.util.function.Supplier;

public class VariantSoundTypes extends ForgeSoundType {
    public static final VariantSoundTypes quartz_ladder = new VariantSoundTypes(1.0F, 1.0F, () -> SoundEvents.BLOCK_LADDER_BREAK, () -> SoundEvents.BLOCK_LADDER_STEP,
            () -> SoundEvents.BLOCK_STEM_PLACE, () -> SoundEvents.BLOCK_STONE_HIT, () -> SoundEvents.BLOCK_LADDER_FALL);

    public VariantSoundTypes(float volumeIn, float pitchIn, Supplier<SoundEvent> breakSoundIn, Supplier<SoundEvent> stepSoundIn, Supplier<SoundEvent> placeSoundIn, Supplier<SoundEvent> hitSoundIn, Supplier<SoundEvent> fallSoundIn) {
        super(volumeIn, pitchIn, breakSoundIn, stepSoundIn, placeSoundIn, hitSoundIn, fallSoundIn);
    }
}
