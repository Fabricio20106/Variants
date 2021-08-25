package com.junethewoods.variants.core.datagen;

import com.junethewoods.variants.core.Variants;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.GatherDataEvent;

@Mod.EventBusSubscriber(modid = Variants.mod_id, bus = Mod.EventBusSubscriber.Bus.MOD)
public final class VariantGenerators {

    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator dataGenerator = event.getGenerator();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();

        dataGenerator.addProvider(new BlockStateGenerator(dataGenerator, existingFileHelper));
        dataGenerator.addProvider(new ItemModelGenerator(dataGenerator, existingFileHelper));
        dataGenerator.addProvider(new BowlItemModels(dataGenerator, existingFileHelper));
    }
}
