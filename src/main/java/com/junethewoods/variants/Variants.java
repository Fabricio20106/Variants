package com.junethewoods.variants;

import com.google.common.collect.ImmutableMap;
import com.junethewoods.variants.block.VSBlocks;
import com.junethewoods.variants.fluid.VSFluids;
import com.junethewoods.variants.blockentity.VSBlockEntities;
import com.junethewoods.variants.blockentity.renderer.VSBedBlockEntityRenderer;
import com.junethewoods.variants.blockentity.renderer.VSBellBlockEntityRenderer;
import com.junethewoods.variants.config.VSConfigs;
import com.junethewoods.variants.effect.VSEffects;
import com.junethewoods.variants.item.VSItems;
import com.junethewoods.variants.item.VSWeaponry;
import com.junethewoods.variants.sound.VSSounds;
import com.junethewoods.variants.util.VSClientHelpers;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.client.renderer.tileentity.BeaconTileEntityRenderer;
import net.minecraft.item.AxeItem;
import net.minecraft.item.Items;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static com.junethewoods.variants.util.VSClientHelpers.compostable;

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
        VSFluids.FLUIDS.register(eventBus);
        VSBlockEntities.BLOCK_ENTITIES.register(eventBus);
        VSEffects.EFFECTS.register(eventBus);
        VSSounds.SOUNDS.register(eventBus);

        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, VSConfigs.COMMON_SPEC, "jtw-mods/variants-common.toml");
    }

    public static ResourceLocation resourceLoc(String name) {
        return new ResourceLocation(MOD_ID, name);
    }

    public void commonSetup(final FMLCommonSetupEvent event) {
        AxeItem.STRIPABLES = new ImmutableMap.Builder<Block, Block>().putAll(AxeItem.STRIPABLES)
                .put(VSBlocks.PAINTING_LOG.get(), VSBlocks.STRIPPED_PAINTING_LOG.get())
                .put(VSBlocks.PAINTING_WOOD.get(), VSBlocks.STRIPPED_PAINTING_WOOD.get())
                .put(VSBlocks.ENDERWOOD_STEM.get(), VSBlocks.STRIPPED_ENDERWOOD_STEM.get())
                .put(VSBlocks.ENDERWOOD_HYPHAE.get(), VSBlocks.STRIPPED_ENDERWOOD_HYPHAE.get()).build();

        compostable(0.3f, VSItems.PAINTING_SAPLING.get());
        compostable(0.3f, VSItems.PAINTING_LEAVES.get());
        compostable(0.3f, VSItems.GLOW_BERRY_BUSH.get());
        compostable(0.5f, VSItems.END_SPROUTS.get());
        compostable(0.5f, VSItems.WARPING_VINES.get());
        compostable(0.65f, VSItems.WARPED_WART.get());
        compostable(0.65f, VSItems.ENDER_WART.get());
        compostable(0.65f, VSItems.GLOW_BLACK_TULIP.get());
        compostable(0.65f, VSItems.GOLDEN_CARROTS.get());
        compostable(0.65f, VSItems.ENDER_ROOTS.get());
        compostable(0.65f, VSItems.ENDER_FUNGUS.get());
        compostable(0.65f, Items.GOLDEN_CARROT);
        compostable(0.85f, VSItems.ENDER_WART_BLOCK.get());
    }

    public void clientSetup(final FMLClientSetupEvent event) {
        VSClientHelpers.makeBow(VSWeaponry.DEBUG_BOW.get());
        VSClientHelpers.makeShield(VSWeaponry.EMPTY_ARMOR_SLOT_SHIELD.get());
        setRenderTypesForBlocks();

        ClientRegistry.bindTileEntityRenderer(VSBlockEntities.VS_BELL.get(), VSBellBlockEntityRenderer::new);
        ClientRegistry.bindTileEntityRenderer(VSBlockEntities.VS_BEACON.get(), BeaconTileEntityRenderer::new);
        ClientRegistry.bindTileEntityRenderer(VSBlockEntities.VS_BED.get(), VSBedBlockEntityRenderer::new);
    }

    @SubscribeEvent
    public void serverStarting(FMLServerStartingEvent event) {}

    public static void setRenderTypesForBlocks() {
        RenderTypeLookup.setRenderLayer(VSBlocks.POTTED_GLOW_BLACK_TULIP.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(VSBlocks.POTTED_SUGAR_CANE.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(VSBlocks.POTTED_PAINTING_SAPLING.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(VSBlocks.GOLDEN_CARROTS.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(VSBlocks.GLOW_BLACK_TULIP.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(VSBlocks.GLOW_BERRY_BUSH.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(VSBlocks.PAINTING_SAPLING.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(VSBlocks.WARPED_WART.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(VSBlocks.GOLDEN_BEACON.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(VSBlocks.GOLDEN_CAULDRON.get(), RenderType.translucent());
        RenderTypeLookup.setRenderLayer(VSBlocks.PAINTING_TRAPDOOR.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(VSBlocks.PAINTING_DOOR.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(VSBlocks.PAINTING_DOOR_WANDERER.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(VSBlocks.PAINTING_DOOR_GRAHAM.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(VSBlocks.PAINTING_DOOR_FIRST.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(VSBlocks.QUARTZ_GLASS.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(VSBlocks.QUARTZ_GLASS_PANE.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(VSBlocks.QUARTZ_CHAIN.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(VSBlocks.GOLDEN_CHAIN.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(VSBlocks.DIAMOND_CHAIN.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(VSBlocks.EMERALD_CHAIN.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(VSBlocks.QUARTZ_BARS.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(VSBlocks.ENDERWOOD_TRAPDOOR.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(VSBlocks.ENDERWOOD_DOOR.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(VSBlocks.ENDER_ROOTS.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(VSBlocks.POTTED_ENDER_ROOTS.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(VSBlocks.END_SPROUTS.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(VSBlocks.ENDER_FUNGUS.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(VSBlocks.POTTED_ENDER_FUNGUS.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(VSBlocks.WARPING_VINES.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(VSBlocks.WARPING_VINES_PLANT.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(VSBlocks.ENDER_WART.get(), RenderType.cutout());

        RenderTypeLookup.setRenderLayer(VSBlocks.GLOW_BLACK_STAINED_GLASS.get(), RenderType.translucent());
        RenderTypeLookup.setRenderLayer(VSBlocks.GLOW_BLACK_STAINED_GLASS_PANE.get(), RenderType.translucent());
    }
}
