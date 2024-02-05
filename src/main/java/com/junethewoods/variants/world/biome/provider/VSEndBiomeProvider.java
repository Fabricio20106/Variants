package com.junethewoods.variants.world.biome.provider;

import com.google.common.collect.ImmutableList;
import com.junethewoods.variants.world.biome.VSBiomes;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.SharedSeedRandom;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryLookupCodec;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biomes;
import net.minecraft.world.biome.provider.BiomeProvider;
import net.minecraft.world.gen.SimplexNoiseGenerator;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.registries.ForgeRegistries;

// Replaces the End Midlands biome with the Enderwood Forest.
public class VSEndBiomeProvider extends BiomeProvider {
    public static final RegistryKey<Biome> ENDERWOOD_FOREST = RegistryKey.create(ForgeRegistries.Keys.BIOMES, VSBiomes.ENDERWOOD_FOREST.getId());

    public static final Codec<VSEndBiomeProvider> CODEC = RecordCodecBuilder.create((providerInstance) -> providerInstance.group(RegistryLookupCodec.create(Registry.BIOME_REGISTRY).forGetter((biomeProvider) -> biomeProvider.biomes), Codec.LONG.fieldOf(
            "seed").stable().forGetter((biomeProvider) -> biomeProvider.seed)).apply(providerInstance, providerInstance.stable(VSEndBiomeProvider::new)));
    private final SimplexNoiseGenerator islandNoise;
    private final Registry<Biome> biomes;
    private final long seed;
    private final Biome end;
    private final Biome highlands;
    private final Biome midlands;
    private final Biome islands;
    private final Biome barrens;
    private final Biome enderwoodForest;

    public VSEndBiomeProvider(Registry<Biome> biomeReg, long seed) {
        this(biomeReg, seed, biomeReg.getOrThrow(Biomes.THE_END), biomeReg.getOrThrow(Biomes.END_HIGHLANDS), biomeReg.getOrThrow(ENDERWOOD_FOREST), biomeReg.getOrThrow(Biomes.SMALL_END_ISLANDS), biomeReg.getOrThrow(Biomes.END_BARRENS),
                biomeReg.getOrThrow(ENDERWOOD_FOREST));
    }

    private VSEndBiomeProvider(Registry<Biome> biomeReg, long seed, Biome mainEnd, Biome highlands, Biome midlands, Biome smallIslands, Biome barrens, Biome enderwoodForest) {
        super(ImmutableList.of(mainEnd, highlands, midlands, smallIslands, barrens, enderwoodForest));
        this.biomes = biomeReg;
        this.seed = seed;
        this.end = mainEnd;
        this.highlands = highlands;
        this.midlands = midlands;
        this.islands = smallIslands;
        this.barrens = barrens;
        this.enderwoodForest = enderwoodForest;
        SharedSeedRandom seedRandom = new SharedSeedRandom(seed);
        seedRandom.consumeCount(17292);
        this.islandNoise = new SimplexNoiseGenerator(seedRandom);
    }

    protected Codec<? extends BiomeProvider> codec() {
        return CODEC;
    }

    @OnlyIn(Dist.CLIENT)
    public BiomeProvider withSeed(long seed) {
        return new VSEndBiomeProvider(this.biomes, seed, this.end, this.highlands, this.midlands, this.islands, this.barrens, this.enderwoodForest);
    }

    public Biome getNoiseBiome(int x, int y, int z) {
        int i = x >> 2;
        int j = z >> 2;
        if ((long) i * (long) i + (long) j * (long) j <= 4096L) {
            return this.end;
        } else {
            float heightValue = getHeightValue(this.islandNoise, i * 2 + 1, j * 2 + 1);
            if (heightValue > 40) {
                return this.highlands;
            } else if (heightValue >= 0) {
                return this.midlands;
            } else if (heightValue < -20) {
                return this.islands;
            } else {
                return this.barrens;
            }
        }
    }

    public static float getHeightValue(SimplexNoiseGenerator generator, int p_235317_1_, int p_235317_2_) {
        int i = p_235317_1_ / 2;
        int j = p_235317_2_ / 2;
        int k = p_235317_1_ % 2;
        int l = p_235317_2_ % 2;
        float f = 100 - MathHelper.sqrt((float)(p_235317_1_ * p_235317_1_ + p_235317_2_ * p_235317_2_)) * 8;
        f = MathHelper.clamp(f, -100, 80);

        for(int i1 = -12; i1 <= 12; ++i1) {
            for(int j1 = -12; j1 <= 12; ++j1) {
                long k1 = i + i1;
                long l1 = j + j1;
                if (k1 * k1 + l1 * l1 > 4096L && generator.getValue((double) k1, (double )l1) < (double) -0.9F) {
                    float f1 = (MathHelper.abs((float) k1) * 3439 + MathHelper.abs((float) l1) * 147) % 13 + 9;
                    float f2 = (float) (k - i1 * 2);
                    float f3 = (float) (l - j1 * 2);
                    float f4 = 100 - MathHelper.sqrt(f2 * f2 + f3 * f3) * f1;
                    f4 = MathHelper.clamp(f4, -100, 80);
                    f = Math.max(f, f4);
                }
            }
        }

        return f;
    }
}
