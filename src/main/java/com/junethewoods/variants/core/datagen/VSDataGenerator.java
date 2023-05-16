package com.junethewoods.variants.core.datagen;

import com.junethewoods.variants.core.Variants;
import com.junethewoods.variants.core.datagen.models.VSBlockStateGenerator;
import com.junethewoods.variants.core.datagen.models.VSBowlItemModelGenerator;
import com.junethewoods.variants.core.datagen.models.VSItemModelGenerator;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.forge.event.lifecycle.GatherDataEvent;

@Mod.EventBusSubscriber(modid = Variants.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public final class VSDataGenerator {
    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        ExistingFileHelper fileHelper = event.getExistingFileHelper();

        generator.addProvider(new VSBlockStateGenerator(generator, fileHelper));
        generator.addProvider(new VSItemModelGenerator(generator, fileHelper));
        generator.addProvider(new VSBowlItemModelGenerator(generator, fileHelper));
        generator.addProvider(new VSRecipeProvider(generator));
    }
}
