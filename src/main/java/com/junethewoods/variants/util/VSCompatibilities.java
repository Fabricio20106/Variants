package com.junethewoods.variants.util;

import com.junethewoods.variants.item.VSCompatItems;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

public class VSCompatibilities {
    public static void registerCompatibilities() {
        final IEventBus iEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        if (ModList.get().isLoaded("edits")) {
            VSCompatItems.ITEMS_EDITS.register(iEventBus);
        }
        if (ModList.get().isLoaded("others")) {
            VSCompatItems.ITEMS_OTHERS.register(iEventBus);
        }
        if (ModList.get().isLoaded("backmath")) {
            VSCompatItems.ITEMS_BACK_MATH.register(iEventBus);
        }
    }
}