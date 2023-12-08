package com.junethewoods.variants;

import com.junethewoods.variants.block.VSBlocks;
import com.junethewoods.variants.blockentity.VSBlockEntities;
import com.junethewoods.variants.config.VSConfigs;
import com.junethewoods.variants.effect.VSEffects;
import com.junethewoods.variants.entity.VSEntities;
import com.junethewoods.variants.entity.client.FishRenderer;
import com.junethewoods.variants.entity.client.VSBoatRenderer;
import com.junethewoods.variants.item.VSItems;
import com.junethewoods.variants.item.VSWeaponry;
import com.junethewoods.variants.sound.VSSounds;
import com.junethewoods.variants.util.VSClientHelpers;
import com.junethewoods.variants.util.VSWoodTypes;
import com.junethewoods.variants.util.tab.VSCreativeTabs;
import com.mojang.logging.LogUtils;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModLoadingContext;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.server.ServerStartingEvent;
import org.slf4j.Logger;

@Mod(Variants.MOD_ID)
public class Variants {
    public static final Logger LOGGER = LogUtils.getLogger();
    public static final String MOD_ID = "variants";

    public Variants(IEventBus eventBus) {
        eventBus.addListener(this::commonSetup);
        eventBus.addListener(this::clientSetup);

        NeoForge.EVENT_BUS.register(this);

        VSItems.ITEMS.register(eventBus);
        VSWeaponry.ITEMS.register(eventBus);
        VSBlocks.BLOCKS.register(eventBus);
//        VSFluids.FLUIDS.register(eventBus);
        VSEntities.ENTITIES.register(eventBus);
        VSBlockEntities.BLOCK_ENTITIES.register(eventBus);
        VSEffects.EFFECTS.register(eventBus);
        VSSounds.SOUNDS.register(eventBus);
        VSCreativeTabs.CREATIVE_TABS.register(eventBus);

        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, VSConfigs.SPEC, "jtw-mods/variants-common.toml");
    }

    public static ResourceLocation resourceLoc(String name) {
        return new ResourceLocation(MOD_ID, name);
    }

    public void commonSetup(final FMLCommonSetupEvent event) {
//        AxeItem.STRIPPABLES = new ImmutableMap.Builder<Block, Block>().putAll(AxeItem.STRIPPABLES)
//                .put(VSBlocks.PAINTING_LOG.get(), VSBlocks.STRIPPED_PAINTING_LOG.get())
//                .put(VSBlocks.PAINTING_WOOD.get(), VSBlocks.STRIPPED_PAINTING_WOOD.get())
//                .put(VSBlocks.ENDERWOOD_STEM.get(), VSBlocks.STRIPPED_ENDERWOOD_STEM.get())
//                .put(VSBlocks.ENDERWOOD_HYPHAE.get(), VSBlocks.STRIPPED_ENDERWOOD_HYPHAE.get()).build();

//        EntitySpawnPlacementRegistry.register(VSEntities.FISH.get(), EntitySpawnPlacementRegistry.PlacementType.IN_WATER, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, AbstractFishEntity::checkFishSpawnRules);

//        VSClientHelpers.compostables();
//        VSClientHelpers.tillables();
//        VSClientHelpers.flammables();
//        VSClientHelpers.addBed(VSBlocks.GLOW_BLACK_BED.get());

        WoodType.register(VSWoodTypes.PAINTING);
        WoodType.register(VSWoodTypes.ENDERWOOD);
    }

    public void clientSetup(final FMLClientSetupEvent event) {
        VSClientHelpers.makeBow(VSWeaponry.DEBUG_BOW.get());
        VSClientHelpers.makeShield(VSWeaponry.EMPTY_ARMOR_SLOT_SHIELD.get());
        VSClientHelpers.makeCustomWoolSweater(VSWeaponry.WOOL_SWEATER.get());

        Sheets.addWoodType(VSWoodTypes.PAINTING);
        Sheets.addWoodType(VSWoodTypes.ENDERWOOD);

        EntityRenderers.register(VSEntities.FISH.get(), FishRenderer::new);
        EntityRenderers.register(VSEntities.VS_BOAT.get(), context -> new VSBoatRenderer(context, false));
        EntityRenderers.register(VSEntities.VS_CHEST_BOAT.get(), context -> new VSBoatRenderer(context, true));
    }

    @SubscribeEvent
    public void serverStarting(ServerStartingEvent event) {}
}
