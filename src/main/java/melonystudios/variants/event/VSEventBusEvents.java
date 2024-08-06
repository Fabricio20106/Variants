package melonystudios.variants.event;

import melonystudios.variants.Variants;
import melonystudios.variants.data.models.VSBlockStateProvider;
import melonystudios.variants.data.models.VSItemModelProvider;
import melonystudios.variants.data.recipe.VSExpoStewsRecipeProvider;
import melonystudios.variants.data.reports.VSBiomeReportsProvider;
import melonystudios.variants.data.reports.VSDamageSourceReportsProvider;
import melonystudios.variants.data.tags.VSBlockTagsProvider;
import melonystudios.variants.data.tags.VSEntityTypeTagsProvider;
import melonystudios.variants.data.tags.VSFluidTagsProvider;
import melonystudios.variants.data.tags.VSItemTagsProvider;
import melonystudios.variants.entity.VSEntities;
import net.minecraft.data.DataGenerator;
import net.minecraft.entity.passive.fish.AbstractFishEntity;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.GatherDataEvent;

@Mod.EventBusSubscriber(modid = Variants.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class VSEventBusEvents {
    @SubscribeEvent
    public static void gatherData(final GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        ExistingFileHelper fileHelper = event.getExistingFileHelper();

        generator.addProvider(new VSBlockStateProvider(generator, fileHelper));
        generator.addProvider(new VSItemModelProvider(generator, fileHelper));

        VSBlockTagsProvider vsBlockStateProvider = new VSBlockTagsProvider(generator, fileHelper);
        generator.addProvider(vsBlockStateProvider);
        generator.addProvider(new VSItemTagsProvider(generator, vsBlockStateProvider, fileHelper));
        generator.addProvider(new VSFluidTagsProvider(generator, fileHelper));
        generator.addProvider(new VSEntityTypeTagsProvider(generator, fileHelper));
        generator.addProvider(new VSExpoStewsRecipeProvider(generator));

        generator.addProvider(new VSBiomeReportsProvider(generator));
        generator.addProvider(new VSDamageSourceReportsProvider(generator));
    }

    @SubscribeEvent
    public static void createEntityAttributes(EntityAttributeCreationEvent event) {
        event.put(VSEntities.FISH.get(), AbstractFishEntity.createAttributes().build());
    }
}
