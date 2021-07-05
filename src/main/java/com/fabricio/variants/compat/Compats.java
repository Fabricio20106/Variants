package com.fabricio.variants.compat;

import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

public class Compats {
    public static void registerCompats() {
        final IEventBus iEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        if (ModList.get().isLoaded("edits")) {
            CompatInit.editsCompat.register(iEventBus);
        }
        if (ModList.get().isLoaded("others")) {
            CompatInit.othersCompat.register(iEventBus);
        }
    }
}
