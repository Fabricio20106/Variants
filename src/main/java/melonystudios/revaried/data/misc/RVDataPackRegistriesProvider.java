package melonystudios.revaried.data.misc;

import melonystudios.revaried.Revaried;
import melonystudios.revaried.misc.RVJukeboxSongs;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.DatapackBuiltinEntriesProvider;
import org.jetbrains.annotations.NotNull;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class RVDataPackRegistriesProvider extends DatapackBuiltinEntriesProvider {
    public static final RegistrySetBuilder BUILDER = new RegistrySetBuilder()
            // Other
            .add(Registries.JUKEBOX_SONG, RVJukeboxSongs::bootstrap);
            // World Generation
//            .add(Registries.BIOME, RVBiomes::bootstrap)
//            .add(Registries.CONFIGURED_FEATURE, RVConfiguredFeatures::bootstrap)
//            .add(Registries.PLACED_FEATURE, RVPlacedFeatures::bootstrap)
//            .add(NeoForgeRegistries.Keys.BIOME_MODIFIERS, RVBiomeModifiers::bootstrap);

    public RVDataPackRegistriesProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, BUILDER, Set.of(Revaried.MOD_ID));
    }

    @Override
    @NotNull
    public String getName() {
        return "Revaried - Data Pack Registries";
    }
}
