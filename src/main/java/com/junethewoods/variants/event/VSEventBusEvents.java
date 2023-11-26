package com.junethewoods.variants.event;

import com.junethewoods.variants.Variants;
import com.junethewoods.variants.data.models.VSBlockStateProvider;
import com.junethewoods.variants.data.models.VSItemModelProvider;
import com.junethewoods.variants.data.tags.VSBlockTagsProvider;
import com.junethewoods.variants.data.tags.VSFluidTagsProvider;
import com.junethewoods.variants.data.tags.VSItemTagsProvider;
import com.junethewoods.variants.entity.VSEntities;
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
    }

    @SubscribeEvent
    public static void createEntityAttributes(EntityAttributeCreationEvent event) {
        event.put(VSEntities.FISH.get(), AbstractFishEntity.createAttributes().build());
    }
}
