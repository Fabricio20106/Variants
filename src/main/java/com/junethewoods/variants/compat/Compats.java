package com.junethewoods.variants.compat;

import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

public class Compats {
    public static void registerF10Compats() {
        final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        final ModList list = ModList.get();
        if (list.isLoaded("edits")) F10CompatItems.editsCompat.register(modEventBus);
        if (list.isLoaded("others")) F10CompatItems.othersCompat.register(modEventBus);
        if (list.isLoaded("backmath")) F10CompatItems.backMathCompat.register(modEventBus);
        if (list.isLoaded("fabricio2010pack")) {
            F10CompatBlocks.fabricio2010packCompat.register(modEventBus);
            F10CompatItems.fabricio2010packCompat.register(modEventBus);
        }
    }

    public static void registerCompats() {
        ModList list = ModList.get();
    }
}
