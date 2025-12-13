package melonystudios.variants.mixin.world;

import melonystudios.variants.world.biome.provider.EnderwoodEndBiomeProvider;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.DimensionType;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.DimensionSettings;
import net.minecraft.world.gen.NoiseChunkGenerator;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(DimensionType.class)
public class RVDimensionTypeMixin {
    @Inject(method = "defaultEndGenerator", at = @At("HEAD"), cancellable = true)
    private static void substituteEndGenerator(Registry<Biome> biomeRegistry, Registry<DimensionSettings> dimensionReg, long seed, CallbackInfoReturnable<ChunkGenerator> callback) {
        callback.setReturnValue(new NoiseChunkGenerator(new EnderwoodEndBiomeProvider(biomeRegistry, seed), seed, () -> dimensionReg.getOrThrow(DimensionSettings.END)));
    }
}
