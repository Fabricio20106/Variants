package melonystudios.variants.world.biome.provider;

import com.google.common.collect.ImmutableList;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import melonystudios.variants.Variants;
import melonystudios.variants.world.biome.VSBiomes;
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

import javax.annotation.Nonnull;

// Replaces the End Midlands biome with the Enderwood Forest.
public class EnderwoodEndBiomeProvider extends BiomeProvider {
    public static final RegistryKey<Biome> THE_END_SUBSTITUTION = RegistryKey.create(ForgeRegistries.Keys.BIOMES, Variants.INSTANCE.getConfig().substituteTheEndBiomeWith);
    public static final RegistryKey<Biome> ENDERWOOD_FOREST = RegistryKey.create(ForgeRegistries.Keys.BIOMES, VSBiomes.ENDERWOOD_FOREST.getId());

    public static final Codec<EnderwoodEndBiomeProvider> CODEC = RecordCodecBuilder.create((providerInstance) -> providerInstance.group(RegistryLookupCodec.create(Registry.BIOME_REGISTRY).forGetter((biomeProvider) -> biomeProvider.biomes), Codec.LONG.fieldOf(
            "seed").stable().forGetter((biomeProvider) -> biomeProvider.seed)).apply(providerInstance, providerInstance.stable(EnderwoodEndBiomeProvider::new)));
    private final SimplexNoiseGenerator islandNoise;
    private final Registry<Biome> biomes;
    private final long seed;
    private final Biome end;
    private final Biome highlands;
    private final Biome midlands;
    private final Biome islands;
    private final Biome barrens;
    private final Biome enderwoodForest;

    public EnderwoodEndBiomeProvider(Registry<Biome> biomeReg, long seed) {
        this(biomeReg, seed, biomeReg.getOrThrow(THE_END_SUBSTITUTION), biomeReg.getOrThrow(Biomes.END_HIGHLANDS), biomeReg.getOrThrow(ENDERWOOD_FOREST),
                biomeReg.getOrThrow(Biomes.SMALL_END_ISLANDS), biomeReg.getOrThrow(Biomes.END_BARRENS), biomeReg.getOrThrow(ENDERWOOD_FOREST));
    }

    private EnderwoodEndBiomeProvider(Registry<Biome> biomeReg, long seed, Biome mainEnd, Biome highlands, Biome midlands, Biome smallIslands, Biome barrens, Biome enderwoodForest) {
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

    @Override
    @Nonnull
    public Codec<? extends BiomeProvider> codec() {
        return CODEC;
    }

    @Override
    @Nonnull
    @OnlyIn(Dist.CLIENT)
    public BiomeProvider withSeed(long seed) {
        return new EnderwoodEndBiomeProvider(this.biomes, seed, this.end, this.highlands, this.midlands, this.islands, this.barrens, this.enderwoodForest);
    }

    @Override
    @Nonnull
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

    public static float getHeightValue(SimplexNoiseGenerator generator, int x, int z) {
        int i = x / 2;
        int j = z / 2;
        int k = x % 2;
        int l = z % 2;
        float f = 100 - MathHelper.sqrt((float) (x * x + z * z)) * 8;
        f = MathHelper.clamp(f, -100, 80);

        for (int i1 = -12; i1 <= 12; ++i1) {
            for (int j1 = -12; j1 <= 12; ++j1) {
                long k1 = i + i1;
                long l1 = j + j1;
                if (k1 * k1 + l1 * l1 > 4096L && generator.getValue((double) k1, (double) l1) < (double) -0.9F) {
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
