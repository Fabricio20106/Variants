package com.fabricio.variants;

import com.fabricio.variants.compat.Compats;
import com.fabricio.variants.init.BlockInit;
import com.fabricio.variants.init.StuffInit;
import com.fabricio.variants.init.WeaponryInit;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.lang.String;

@Mod(Variants.mod_id)
public class Variants {
    public static final Logger logger = LogManager.getLogger();

    public static final String mod_id = "variants";

    public Variants() {
        final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::doClientStuff);
        MinecraftForge.EVENT_BUS.register(this);

        StuffInit.items.register(modEventBus);
        WeaponryInit.tools.register(modEventBus);
        BlockInit.blocks.register(modEventBus);
        Compats.registerCompats();
    }

    /**public static boolean isInstalled() {
        return ModList.get() != null && ModList.get().getModContainerById("phnixideas").isPresent();
    }*/

    public void setup(final FMLCommonSetupEvent event) {}

    public void doClientStuff(final FMLClientSetupEvent event) {}

    @SubscribeEvent
    public void onServerStarting(FMLServerStartingEvent event) {}
}
