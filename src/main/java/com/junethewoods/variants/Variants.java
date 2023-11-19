package com.junethewoods.variants;

import com.google.common.collect.ImmutableMap;
import com.junethewoods.variants.block.VSBlocks;
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
import net.minecraft.block.ComposterBlock;
import net.minecraft.client.renderer.tileentity.BeaconTileEntityRenderer;
import net.minecraft.item.AxeItem;
import net.minecraft.item.Item;
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

@Mod(Variants.MOD_ID)
public class Variants {
    public static final Logger LOGGER = LogManager.getLogger();
    public static final String MOD_ID = "variants";

    public Variants() {
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();
        eventBus.addListener(this::commonSetup);
        eventBus.addListener(this::clientSetup);

        MinecraftForge.EVENT_BUS.register(this);

        VSEffects.EFFECTS.register(eventBus);
        VSItems.ITEMS.register(eventBus);
        VSWeaponry.ITEMS.register(eventBus);
        VSBlocks.BLOCKS.register(eventBus);
        VSBlockEntities.BLOCK_ENTITIES.register(eventBus);
        VSSounds.SOUNDS.register(eventBus);

        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, VSConfigs.COMMON_SPEC, "jtw-mods/variants-common.toml");
    }

    public static ResourceLocation resourceLoc(String name) {
        return new ResourceLocation(MOD_ID, name);
    }

    public void commonSetup(final FMLCommonSetupEvent event) {
        AxeItem.STRIPABLES = new ImmutableMap.Builder<Block, Block>().putAll(AxeItem.STRIPABLES)
                .put(VSBlocks.PAINTING_LOG.get(), VSBlocks.STRIPPED_PAINTING_LOG.get())
                .put(VSBlocks.PAINTING_WOOD.get(), VSBlocks.STRIPPED_PAINTING_WOOD.get()).build();

        compostable(0.3f, VSItems.PAINTING_SAPLING.get());
        compostable(0.3f, VSItems.PAINTING_LEAVES.get());
        compostable(0.3f, VSItems.GLOW_BERRY_BUSH.get());
        compostable(0.65f, VSItems.WARPED_WART.get());
        compostable(0.65f, VSItems.GLOW_BLACK_TULIP.get());
        compostable(0.65f, VSItems.GOLDEN_CARROTS.get());
        compostable(0.65f, Items.GOLDEN_CARROT);
    }

    public void clientSetup(final FMLClientSetupEvent event) {
        VSClientHelpers.setRenderTypesForBlocks();
        VSClientHelpers.makeBow(VSWeaponry.DEBUG_BOW.get());
        VSClientHelpers.makeShield(VSWeaponry.EMPTY_ARMOR_SLOT_SHIELD.get());

        ClientRegistry.bindTileEntityRenderer(VSBlockEntities.VS_BELL.get(), VSBellBlockEntityRenderer::new);
        ClientRegistry.bindTileEntityRenderer(VSBlockEntities.VS_BEACON.get(), BeaconTileEntityRenderer::new);
        ClientRegistry.bindTileEntityRenderer(VSBlockEntities.VS_BED.get(), VSBedBlockEntityRenderer::new);
    }

    @SubscribeEvent
    public void serverStarting(FMLServerStartingEvent event) {}

    private static void compostable(float chance, Item item) {
        ComposterBlock.COMPOSTABLES.put(item, chance);
    }
}
