package com.junethewoods.variants.core;

import com.junethewoods.variants.common.block.CauldronBlockColor;
import com.junethewoods.variants.common.block.VariantBlockColor;
import com.junethewoods.variants.common.item.LeavesItemColor;
import com.junethewoods.variants.common.item.WoolSweatchestColor;
import com.junethewoods.variants.core.entity.VariantBoatRenderer;
import com.junethewoods.variants.core.gen.VariantGeneration;
import com.junethewoods.variants.core.init.*;
import com.junethewoods.variants.core.sound.VariantSounds;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(Variants.mod_id)
public class Variants {
    public static final Logger logger = LogManager.getLogger();

    public static Variants instance = new Variants();

    public static final String mod_id = "variants";

    public Variants() {
        instance = this;
        final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        modEventBus.addListener(this::setup);
        modEventBus.addListener(this::doClientStuff);
        MinecraftForge.EVENT_BUS.register(this);
        modEventBus.addListener(this::registerBlockColors);
        modEventBus.addListener(this::registerItemColors);

        StuffInit.items.register(modEventBus);
        WeaponryInit.tools.register(modEventBus);
        BowlInit.items.register(modEventBus);
        BlockInit.blocks.register(modEventBus);
        FluidInit.fluids.register(modEventBus);
        EntityInit.entities.register(modEventBus);
        VariantSounds.registerSounds();
        CompatRegistry.registerF10Compats();
        CompatRegistry.registerCompats();
    }

    public static boolean isPhoenixIdeasInstalled() {
        return ModList.get() != null && ModList.get().getModContainerById("phnixideas").isPresent();
    }

    public static ResourceLocation resourceLoc(String name) {
        return new ResourceLocation(mod_id, name);
    }

    public void setup(final FMLCommonSetupEvent event) {
        VariantGeneration.actuallyGenerateStuff();
    }

    public void doClientStuff(final FMLClientSetupEvent event) {
        RenderTypeLookup.setRenderLayer(BlockInit.painting_sapling.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(BlockInit.golden_carrots.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(BlockInit.potted_sunny_flower.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(BlockInit.potted_glow_black_tulip.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(BlockInit.potted_golden_carrots.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(BlockInit.glow_berry_bush.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(BlockInit.glow_black_tulip.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(BlockInit.sunny_flower.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(BlockInit.painting_trapdoor.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(BlockInit.painting_door.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(BlockInit.warped_wart.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(BlockInit.painting_leaves.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(BlockInit.diamond_bell.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(BlockInit.potted_grass.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(BlockInit.potted_torch.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(BlockInit.potted_soul_torch.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(BlockInit.potted_sugar_cane.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(BlockInit.azure_bluet_leaves.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(BlockInit.quartz_beacon.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(BlockInit.gold_beacon.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(BlockInit.gold_cauldron.get(), RenderType.getTranslucent());
        RenderTypeLookup.setRenderLayer(BlockInit.quartz_cauldron.get(), RenderType.getTranslucent());
        RenderTypeLookup.setRenderLayer(BlockInit.ender_sprouts.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(BlockInit.ender_fungus.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(BlockInit.ender_roots.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(BlockInit.ender_trapdoor.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(BlockInit.ender_door.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(BlockInit.potted_ender_fungus.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(BlockInit.potted_ender_roots.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(BlockInit.potted_warped_wart.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(BlockInit.quartz_chain.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(BlockInit.quartz_glass.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(BlockInit.quartz_glass_pane.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(BlockInit.quartz_ladder.get(), RenderType.getTranslucent());
        RenderTypeLookup.setRenderLayer(BlockInit.quartz_bars.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(BlockInit.diamond_chain.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(BlockInit.emerald_chain.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(BlockInit.soul_brewing_stand.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(FluidInit.soul_lava.get(), RenderType.getTranslucent());

        RenderingRegistry.registerEntityRenderingHandler(EntityInit.variant_boat.get(), VariantBoatRenderer::new);
    }

    @SubscribeEvent
    public void registerBlockColors(ColorHandlerEvent.Block event){
        event.getBlockColors().register(new VariantBlockColor(), BlockInit.painting_leaves.get());
        event.getBlockColors().register(new CauldronBlockColor(), BlockInit.gold_cauldron.get(), BlockInit.quartz_cauldron.get());
    }

    @SubscribeEvent
    public void registerItemColors(ColorHandlerEvent.Item event){
        event.getItemColors().register(new LeavesItemColor(), BlockInit.painting_leaves.get());
        event.getItemColors().register(new WoolSweatchestColor(), WeaponryInit.wool_sweatchest.get());
    }

    @SubscribeEvent
    public void onServerStarting(FMLServerStartingEvent event) {}

    public static void main(String[] args) {}
}
