package com.junethewoods.variants.data.reports;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.junethewoods.variants.Variants;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.JsonOps;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.DirectoryCache;
import net.minecraft.data.IDataProvider;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.biome.Biome;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.Nonnull;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Supplier;

public class VSBiomeReportsProvider implements IDataProvider {
    public static final Logger LOGGER = LogManager.getLogger();
    public static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private final DataGenerator generator;

    public VSBiomeReportsProvider(DataGenerator generator) {
        this.generator = generator;
    }

    @Override
    @Nonnull
    public String getName() {
        return "Variants - Biome Reports";
    }

    private static Path createPath(Path path, ResourceLocation biomeLoc) {
        return path.resolve("reports/biomes/" + biomeLoc.getPath() + ".json");
    }

    @Override
    public void run(DirectoryCache directoryCache) {
        Path path = this.generator.getOutputFolder();

        for (Map.Entry<RegistryKey<Biome>, Biome> entry : WorldGenRegistries.BIOME.entrySet()) {
            // boolean isVSBiome = WorldGenRegistries.BIOME.entrySet().stream().filter((a) -> a.getValue().getRegistryName().getNamespace().equals(Variants.MOD_ID));
            boolean isVSBiome = Objects.requireNonNull(entry.getValue().getRegistryName()).getNamespace().equals(Variants.MOD_ID);
            if (isVSBiome) {
                Path path1 = createPath(path, entry.getKey().location());
                Biome biome = entry.getValue();
                Function<Supplier<Biome>, DataResult<JsonElement>> function = JsonOps.INSTANCE.withEncoder(Biome.CODEC);

                try {
                    Optional<JsonElement> opJsonElement = function.apply(() -> biome).result();
                    if (opJsonElement.isPresent()) {
                        IDataProvider.save(GSON, directoryCache, opJsonElement.get(), path1);
                    } else {
                        LOGGER.error("Variants: Could not serialize Variants biome {}", path1);
                    }
                } catch (IOException exception) {
                    LOGGER.error("Variants: Could not save Variants biome {}", path1, exception);
                }
            }
        }
    }
}
