package melonystudios.variants.data.report;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.JsonOps;
import melonystudios.variants.Variants;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.DirectoryCache;
import net.minecraft.data.IDataProvider;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.biome.Biome;

import javax.annotation.Nonnull;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Supplier;

public class VSBiomeReportsProvider implements IDataProvider {
    public static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private final DataGenerator generator;

    public VSBiomeReportsProvider(DataGenerator generator) {
        this.generator = generator;
    }

    @Override
    @Nonnull
    public String getName() {
        return Variants.generatorName("Biome Reports");
    }

    private static Path createPath(Path path, ResourceLocation biomeLoc) {
        return path.resolve("reports/worldgen/biome/" + biomeLoc.getPath() + ".json");
    }

    @Override
    public void run(DirectoryCache cache) {
        Path outputFolder = this.generator.getOutputFolder();

        for (Map.Entry<RegistryKey<Biome>, Biome> entry : WorldGenRegistries.BIOME.entrySet()) {
            boolean isRevariedBiome = Objects.requireNonNull(entry.getValue().getRegistryName()).getNamespace().equals(Variants.MOD_ID);
            if (isRevariedBiome) {
                Path filePath = createPath(outputFolder, entry.getKey().location());
                Biome biome = entry.getValue();
                Function<Supplier<Biome>, DataResult<JsonElement>> function = JsonOps.INSTANCE.withEncoder(Biome.CODEC);

                try {
                    Optional<JsonElement> element = function.apply(() -> biome).result();
                    if (element.isPresent()) {
                        IDataProvider.save(GSON, cache, element.get(), filePath);
                    } else {
                        Variants.LOGGER.error(new TranslationTextComponent("error." + Variants.MOD_ID + ".biome_reports.serialization", filePath).getString());
                    }
                } catch (IOException exception) {
                    Variants.LOGGER.error(new TranslationTextComponent("error." + Variants.MOD_ID + ".biome_reports.saving", filePath).getString(), exception);
                }
            }
        }
    }
}
