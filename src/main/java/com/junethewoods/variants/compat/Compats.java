package com.junethewoods.variants.compat;

import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

public class Compats {
    public static void registerF10Compats() {
        final IEventBus iEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        if (ModList.get().isLoaded("edits")) {
            F10CompatInit.editsCompat.register(iEventBus);
        }
        if (ModList.get().isLoaded("others")) {
            F10CompatInit.othersCompat.register(iEventBus);
        }
        if (ModList.get().isLoaded("backmath")) {
            F10CompatInit.backMathCompat.register(iEventBus);
        }
    }

    public static void registerCompats() {}
}
