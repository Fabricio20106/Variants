package com.junethewoods.variants.common;

import com.junethewoods.variants.compat.Compats;
import com.junethewoods.variants.gen.OreGeneration;
import com.junethewoods.variants.init.BlockInit;
import com.junethewoods.variants.init.StuffInit;
import com.junethewoods.variants.init.WeaponryInit;
import net.minecraft.util.ResourceLocation;
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

    public static Variants instance;

    public static final String mod_id = "variants";

    public Variants() {
        instance = this;
        final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::doClientStuff);
        MinecraftForge.EVENT_BUS.register(this);

        StuffInit.items.register(modEventBus);
        WeaponryInit.tools.register(modEventBus);
        BlockInit.blocks.register(modEventBus);
        Compats.registerF10Compats();
        Compats.registerCompats();
    }

    /**public static boolean isInstalled() {
        return ModList.get() != null && ModList.get().getModContainerById("phnixideas").isPresent();
    }*/

    public void setup(final FMLCommonSetupEvent event) {
        OreGeneration.actuallyGenerateOre();
    }

    public void doClientStuff(final FMLClientSetupEvent event) {}

    @SubscribeEvent
    public void onServerStarting(FMLServerStartingEvent event) {}

    public static void main(String[] args) {}
}
