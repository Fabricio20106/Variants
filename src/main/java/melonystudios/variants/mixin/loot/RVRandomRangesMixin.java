package melonystudios.variants.mixin.loot;

import melonystudios.variants.Variants;
import melonystudios.variants.loot.rand.BowlIDValueRange;
import net.minecraft.loot.IRandomRange;
import net.minecraft.loot.RandomRanges;
import net.minecraft.util.ResourceLocation;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import java.util.Map;

@Mixin(RandomRanges.class)
public class RVRandomRangesMixin {
    @Shadow
    @Final
    private static Map<ResourceLocation, Class<? extends IRandomRange>> GENERATORS;

    static {
        GENERATORS.put(Variants.variants("texture_id"), BowlIDValueRange.class);
    }
}
