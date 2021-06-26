package com.fabricio.variants.compat.registries;

import com.fabricio.variants.Variants;
import com.fabricio.variants.compat.registries.edits.EditsStuffInit;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import java.util.logging.LogManager;
import java.util.logging.Logger;

public class Registries {
    public static final Logger logger = LogManager.getLogManager().getLogger(Variants.mod_id);

    public Registries() {
        final IEventBus iEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::doClientStuff);
        MinecraftForge.EVENT_BUS.register(this);
        register();

        EditsStuffInit.items.register(iEventBus);
    }

    public static void register() {}

    public void setup(final FMLCommonSetupEvent event) {}

    public void doClientStuff(final FMLClientSetupEvent event) {}

    @SubscribeEvent
    public void onServerStarting(FMLServerStartingEvent event) {}
}
