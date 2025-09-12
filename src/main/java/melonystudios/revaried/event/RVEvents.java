package melonystudios.revaried.event;

import melonystudios.revaried.Revaried;
import melonystudios.revaried.data.misc.RVDataPackRegistriesProvider;
import melonystudios.revaried.data.misc.RVRecipeProvider;
import melonystudios.revaried.data.model.RVItemModelProvider;
import melonystudios.revaried.data.tag.RVBlockTagsProvider;
import melonystudios.revaried.data.tag.RVItemTagsProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;

import java.util.concurrent.CompletableFuture;

@EventBusSubscriber(modid = Revaried.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class RVEvents {
    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        ExistingFileHelper fileHelper = event.getExistingFileHelper();
        PackOutput output = generator.getPackOutput();
        CompletableFuture<HolderLookup.Provider> registries = event.getLookupProvider();

        if (event.includeClient()) {
            // Models
            generator.addProvider(true, new RVItemModelProvider(output, fileHelper));
        }

        if (event.includeServer()) {
            // Tags
            RVBlockTagsProvider blockTags = new RVBlockTagsProvider(output, registries, fileHelper);
            generator.addProvider(true, blockTags);
            generator.addProvider(true, new RVItemTagsProvider(output, registries, blockTags.contentsGetter(), fileHelper));

            // Miscellaneous
            generator.addProvider(true, new RVDataPackRegistriesProvider(output, registries));
            generator.addProvider(true, new RVRecipeProvider(output, registries));
        }
    }
}
