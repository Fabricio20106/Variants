package melonystudios.variants.event;

import melonystudios.variants.Variants;
import melonystudios.variants.data.bowltype.VSBowlTypesProvider;
import melonystudios.variants.data.models.VSBlockStateProvider;
import melonystudios.variants.data.models.VSItemModelProvider;
import melonystudios.variants.data.recipes.VSExpoStewsRecipeProvider;
import melonystudios.variants.data.reports.VSBiomeReportsProvider;
import melonystudios.variants.data.reports.VSDamageSourceReportsProvider;
import melonystudios.variants.data.sound.VSSoundDefinitionsProvider;
import melonystudios.variants.data.sound.VSVanillaSoundDefinitionsProvider;
import melonystudios.variants.data.tags.*;
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

        // Models
        generator.addProvider(new VSBlockStateProvider(generator, fileHelper));
        generator.addProvider(new VSItemModelProvider(generator, fileHelper));

        // Tags
        VSBlockTagsProvider blockTagsProvider = new VSBlockTagsProvider(generator, fileHelper);
        generator.addProvider(blockTagsProvider);
        generator.addProvider(new VSItemTagsProvider(generator, blockTagsProvider, fileHelper));
        generator.addProvider(new VSFluidTagsProvider(generator, fileHelper));
        generator.addProvider(new VSEntityTypeTagsProvider(generator, fileHelper));
        generator.addProvider(new VSStewBehaviorTagsProvider(generator, fileHelper));

        // Reports
        generator.addProvider(new VSBiomeReportsProvider(generator));
        generator.addProvider(new VSDamageSourceReportsProvider(generator));

        // Miscellaneous
        generator.addProvider(new VSExpoStewsRecipeProvider(generator));
        generator.addProvider(new VSBowlTypesProvider(generator));
        generator.addProvider(new VSSoundDefinitionsProvider(generator, fileHelper));
        generator.addProvider(new VSVanillaSoundDefinitionsProvider(generator, fileHelper));
    }

    @SubscribeEvent
    public static void createEntityAttributes(EntityAttributeCreationEvent event) {
        event.put(VSEntities.FISH.get(), AbstractFishEntity.createAttributes().build());
    }
}
