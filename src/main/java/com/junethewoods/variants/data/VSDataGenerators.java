package com.junethewoods.variants.data;

import com.junethewoods.variants.Variants;
import com.junethewoods.variants.data.models.VSBlockStateProvider;
import com.junethewoods.variants.data.models.VSItemModelProvider;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.GatherDataEvent;

@Mod.EventBusSubscriber(modid = Variants.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class VSDataGenerators {
    @SubscribeEvent
    public static void gatherData(final GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        ExistingFileHelper fileHelper = event.getExistingFileHelper();

        generator.addProvider(new VSBlockStateProvider(generator, fileHelper));
        generator.addProvider(new VSItemModelProvider(generator, fileHelper));
    }
}
