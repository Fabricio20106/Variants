package melonystudios.variants.sound;

import net.minecraft.block.SoundType;
import net.minecraft.util.SoundEvents;
import net.minecraftforge.common.util.ForgeSoundType;

public class VSSoundTypes {
    public static final SoundType QUARTZ_LADDER = new ForgeSoundType(1, 1, () -> SoundEvents.STONE_BREAK, () -> SoundEvents.LADDER_STEP, () -> SoundEvents.STONE_PLACE, () -> SoundEvents.STONE_HIT, () -> SoundEvents.LADDER_FALL);
}
