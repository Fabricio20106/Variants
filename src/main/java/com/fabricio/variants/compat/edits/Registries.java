package com.fabricio.variants.compat.edits;

import com.fabricio.variants.Variants;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import java.util.logging.LogManager;
import java.util.logging.Logger;

public class Registries {
    public static final Logger logger = LogManager.getLogManager().getLogger(Variants.mod_id);

    public Registries() {
        final IEventBus iEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        MinecraftForge.EVENT_BUS.register(this);

        EditsStuffInit.items.register(iEventBus);
    }
}
