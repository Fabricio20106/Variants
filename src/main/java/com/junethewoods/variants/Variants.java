package com.junethewoods.variants;

import com.google.common.collect.ImmutableMap;
import com.junethewoods.variants.blockentity.VSBlockEntities;
import com.junethewoods.variants.blockentity.renderer.VSBellBlockEntityRenderer;
import com.junethewoods.variants.sound.VSSounds;
import com.junethewoods.variants.util.VSItemModelProperties;
import com.junethewoods.variants.block.VSBlocks;
import com.junethewoods.variants.item.VSItems;
import com.junethewoods.variants.item.VSWeaponry;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.client.renderer.tileentity.BeaconTileEntityRenderer;
import net.minecraft.item.AxeItem;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.String;

@Mod(Variants.MOD_ID)
public class Variants {
    public static final Logger LOGGER = LogManager.getLogger();
    public static final String MOD_ID = "variants";

    public Variants() {
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();
        eventBus.addListener(this::commonSetup);
        eventBus.addListener(this::clientSetup);

        MinecraftForge.EVENT_BUS.register(this);

        VSItems.ITEMS.register(eventBus);
        VSWeaponry.ITEMS.register(eventBus);
        VSBlocks.BLOCKS.register(eventBus);
        VSBlockEntities.BLOCK_ENTITIES.register(eventBus);
        VSSounds.SOUNDS.register(eventBus);
    }

    public static ResourceLocation resourceLoc(String name) {
        return new ResourceLocation(MOD_ID, name);
    }

    public void commonSetup(final FMLCommonSetupEvent event) {
        AxeItem.STRIPABLES = new ImmutableMap.Builder<Block, Block>().putAll(AxeItem.STRIPABLES)
                .put(VSBlocks.PAINTING_LOG.get(), VSBlocks.STRIPPED_PAINTING_LOG.get())
                .put(VSBlocks.PAINTING_WOOD.get(), VSBlocks.STRIPPED_PAINTING_WOOD.get()).build();
    }

    public void clientSetup(final FMLClientSetupEvent event) {
        RenderTypeLookup.setRenderLayer(VSBlocks.GLOW_BLACK_TULIP.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(VSBlocks.POTTED_GLOW_BLACK_TULIP.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(VSBlocks.POTTED_SUGAR_CANE.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(VSBlocks.WARPED_WART.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(VSBlocks.GLOW_BERRY_BUSH.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(VSBlocks.GOLDEN_CARROTS.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(VSBlocks.GOLDEN_BEACON.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(VSBlocks.GOLDEN_CAULDRON.get(), RenderType.translucent());
        RenderTypeLookup.setRenderLayer(VSBlocks.PAINTING_TRAPDOOR.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(VSBlocks.PAINTING_DOOR.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(VSBlocks.WANDERER_DOOR.get(), RenderType.cutout());

        ClientRegistry.bindTileEntityRenderer(VSBlockEntities.VS_BELL.get(), VSBellBlockEntityRenderer::new);
        ClientRegistry.bindTileEntityRenderer(VSBlockEntities.VS_BEACON.get(), BeaconTileEntityRenderer::new);

        VSItemModelProperties.makeBow(VSWeaponry.DEBUG_BOW.get());
    }

    @SubscribeEvent
    public void serverStarting(FMLServerStartingEvent event) {}
}
