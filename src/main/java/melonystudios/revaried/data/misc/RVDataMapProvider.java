package melonystudios.revaried.data.misc;

import melonystudios.revaried.Revaried;
import melonystudios.revaried.item.RVItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.DataMapProvider;
import net.neoforged.neoforge.registries.datamaps.builtin.FurnaceFuel;
import net.neoforged.neoforge.registries.datamaps.builtin.NeoForgeDataMaps;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.CompletableFuture;

public class RVDataMapProvider extends DataMapProvider {
    public RVDataMapProvider(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> registries) {
        super(packOutput, registries);
    }

    @Override
    @NotNull
    public String getName() {
        return Revaried.generatorName("Data Maps");
    }

    @Override
    protected void gather(HolderLookup.Provider registries) {
        // NeoForge data maps
        this.builder(NeoForgeDataMaps.FURNACE_FUELS).add(RVItems.SOUL_LAVA_BUCKET.get().builtInRegistryHolder(), new FurnaceFuel(40000), false);
    }
}
