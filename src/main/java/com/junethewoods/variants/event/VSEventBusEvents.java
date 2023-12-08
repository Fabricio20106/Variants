package com.junethewoods.variants.event;

import com.junethewoods.variants.Variants;
import com.junethewoods.variants.data.models.VSBlockStateProvider;
import com.junethewoods.variants.data.models.VSItemModelProvider;
import com.junethewoods.variants.data.tags.VSBlockTagsProvider;
import com.junethewoods.variants.data.tags.VSFluidTagsProvider;
import com.junethewoods.variants.data.tags.VSItemTagsProvider;
import com.junethewoods.variants.entity.VSEntities;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.world.entity.animal.AbstractSchoolingFish;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;

import java.util.concurrent.CompletableFuture;

@Mod.EventBusSubscriber(modid = Variants.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class VSEventBusEvents {
    @SubscribeEvent
    public static void gatherData(final GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        PackOutput output = generator.getPackOutput();
        ExistingFileHelper fileHelper = event.getExistingFileHelper();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();

        generator.addProvider(event.includeClient(), new VSBlockStateProvider(output, fileHelper));
        generator.addProvider(event.includeClient(), new VSItemModelProvider(output, fileHelper));

        VSBlockTagsProvider vsBlockStateProvider = new VSBlockTagsProvider(output, lookupProvider, fileHelper);
        generator.addProvider(event.includeServer(), vsBlockStateProvider);
        generator.addProvider(event.includeServer(), new VSItemTagsProvider(output, lookupProvider, vsBlockStateProvider.contentsGetter(), fileHelper));
        generator.addProvider(event.includeServer(), new VSFluidTagsProvider(output, lookupProvider, fileHelper));
    }

    @SubscribeEvent
    public static void createEntityAttributes(EntityAttributeCreationEvent event) {
        event.put(VSEntities.FISH.get(), AbstractSchoolingFish.createAttributes().build());
    }
}
