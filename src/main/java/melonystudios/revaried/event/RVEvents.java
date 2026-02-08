package melonystudios.revaried.event;

import melonystudios.revaried.Revaried;
import melonystudios.revaried.block.RVBlocks;
import melonystudios.revaried.data.loot.RVLootTableProvider;
import melonystudios.revaried.data.misc.RVDataMapProvider;
import melonystudios.revaried.data.misc.RVDataPackRegistriesProvider;
import melonystudios.revaried.data.misc.RVParticleDescriptionProvider;
import melonystudios.revaried.data.misc.RVRecipeProvider;
import melonystudios.revaried.data.model.RVBlockStateProvider;
import melonystudios.revaried.data.model.RVItemModelProvider;
import melonystudios.revaried.data.tag.RVBlockTagsProvider;
import melonystudios.revaried.data.tag.RVFluidTagsProvider;
import melonystudios.revaried.data.tag.RVItemTagsProvider;
import melonystudios.revaried.fluid.RVFluids;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import net.neoforged.neoforge.fluids.FluidType;
import net.neoforged.neoforge.fluids.RegisterCauldronFluidContentEvent;

import java.util.concurrent.CompletableFuture;

@EventBusSubscriber(modid = Revaried.MOD_ID)
public class RVEvents {
    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        ExistingFileHelper fileHelper = event.getExistingFileHelper();
        PackOutput output = generator.getPackOutput();
        CompletableFuture<HolderLookup.Provider> registries = event.getLookupProvider();

        if (event.includeClient()) {
            // Models
            generator.addProvider(true, new RVBlockStateProvider(output, fileHelper));
            generator.addProvider(true, new RVItemModelProvider(output, fileHelper));
            generator.addProvider(true, new RVParticleDescriptionProvider(output, fileHelper));
        }

        if (event.includeServer()) {
            // Tags
            RVBlockTagsProvider blockTags = new RVBlockTagsProvider(output, registries, fileHelper);
            generator.addProvider(true, blockTags);
            generator.addProvider(true, new RVItemTagsProvider(output, registries, blockTags.contentsGetter(), fileHelper));
            generator.addProvider(true, new RVFluidTagsProvider(output, registries, fileHelper));

            // Miscellaneous
            generator.addProvider(true, new RVDataMapProvider(output, registries));
            generator.addProvider(true, new RVDataPackRegistriesProvider(output, registries));
            generator.addProvider(true, new RVLootTableProvider(output, registries));
            generator.addProvider(true, new RVRecipeProvider(output, registries));
        }
    }

    @SubscribeEvent
    public static void registerCauldrons(RegisterCauldronFluidContentEvent event) {
        event.register(RVBlocks.SOUL_LAVA_CAULDRON.get(), RVFluids.SOUL_LAVA.get(), FluidType.BUCKET_VOLUME, null);
    }
}
