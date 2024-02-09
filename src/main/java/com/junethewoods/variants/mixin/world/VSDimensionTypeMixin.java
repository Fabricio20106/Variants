package com.junethewoods.variants.mixin.world;

import com.junethewoods.variants.world.biome.provider.VSEndBiomeProvider;
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
public class VSDimensionTypeMixin {
    @Inject(method = "defaultEndGenerator", at = @At("HEAD"), cancellable = true)
    private static void defaultEndGenerator(Registry<Biome> biomeReg, Registry<DimensionSettings> dimensionReg, long seed, CallbackInfoReturnable<ChunkGenerator> cir) {
        cir.setReturnValue(new NoiseChunkGenerator(new VSEndBiomeProvider(biomeReg, seed), seed, () -> dimensionReg.getOrThrow(DimensionSettings.END)));
    }
}
