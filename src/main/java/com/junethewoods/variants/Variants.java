package com.junethewoods.variants;

import com.google.common.collect.ImmutableMap;
import com.junethewoods.variants.block.VSBlocks;
import com.junethewoods.variants.blockentity.VSBlockEntities;
import com.junethewoods.variants.blockentity.renderer.VSBedBlockEntityRenderer;
import com.junethewoods.variants.blockentity.renderer.VSBellBlockEntityRenderer;
import com.junethewoods.variants.config.VSConfigs;
import com.junethewoods.variants.crafting.VSRecipeTypes;
import com.junethewoods.variants.effect.VSEffects;
import com.junethewoods.variants.entity.VSEntities;
import com.junethewoods.variants.entity.renderer.DragonBreathBottleRenderer;
import com.junethewoods.variants.entity.renderer.FishRenderer;
import com.junethewoods.variants.entity.renderer.SmallSoulFireballRenderer;
import com.junethewoods.variants.entity.renderer.VSBoatRenderer;
import com.junethewoods.variants.fluid.VSFluids;
import com.junethewoods.variants.item.VSItems;
import com.junethewoods.variants.item.VSWeaponry;
import com.junethewoods.variants.sound.VSSounds;
import com.junethewoods.variants.util.VSClientHelpers;
import com.junethewoods.variants.util.VSWoodTypes;
import com.junethewoods.variants.world.biome.VSBiomes;
import com.junethewoods.variants.world.biome.provider.VSEndBiomeProvider;
import com.junethewoods.variants.world.carver.VSConfiguredCarvers;
import com.junethewoods.variants.world.carver.VSWorldCarvers;
import com.junethewoods.variants.world.feature.VSConfiguredFeatures;
import com.junethewoods.variants.world.surface.VSSurfaceBuilders;
import net.minecraft.block.Block;
import net.minecraft.block.WoodType;
import net.minecraft.client.renderer.Atlases;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.client.renderer.tileentity.BeaconTileEntityRenderer;
import net.minecraft.client.renderer.tileentity.SignTileEntityRenderer;
import net.minecraft.entity.EntitySpawnPlacementRegistry;
import net.minecraft.entity.passive.fish.AbstractFishEntity;
import net.minecraft.item.AxeItem;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.Heightmap;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
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

        VSItems.ITEMS.register(eventBus);
        VSWeaponry.ITEMS.register(eventBus);
        VSBlocks.BLOCKS.register(eventBus);
        VSFluids.FLUIDS.register(eventBus);
        VSEntities.ENTITIES.register(eventBus);
        VSBlockEntities.BLOCK_ENTITIES.register(eventBus);
        VSEffects.EFFECTS.register(eventBus);
        VSSounds.SOUNDS.register(eventBus);
        VSWorldCarvers.CARVERS.register(eventBus);
        VSBiomes.BIOMES.register(eventBus);
        VSRecipeTypes.RECIPE_TYPES.register(eventBus);

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

        EntitySpawnPlacementRegistry.register(VSEntities.FISH.get(), EntitySpawnPlacementRegistry.PlacementType.IN_WATER, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, AbstractFishEntity::checkFishSpawnRules);

        VSConfiguredFeatures.init();
        VSConfiguredCarvers.init();
        VSSurfaceBuilders.init();
        Registry.register(Registry.BIOME_SOURCE, Variants.resourceLoc("enderwood_end"), VSEndBiomeProvider.CODEC);

        VSClientHelpers.compostables();
        VSClientHelpers.tillables();
        VSClientHelpers.flammables();
        VSClientHelpers.addBed(VSBlocks.GLOW_BLACK_BED.get());

        WoodType.register(VSWoodTypes.PAINTING);
        WoodType.register(VSWoodTypes.ENDERWOOD);
    }

    public void clientSetup(final FMLClientSetupEvent event) {
        VSClientHelpers.makeBow(VSWeaponry.DEBUG_BOW.get());
        VSClientHelpers.makeShield(VSWeaponry.EMPTY_ARMOR_SLOT_SHIELD.get());
        VSClientHelpers.makeCustomWoolSweater(VSWeaponry.WOOL_SWEATER.get());
        VSClientHelpers.makeExpoStew(VSItems.EXPONENTIAL_MUSHROOM_STEW.get());
        VSClientHelpers.makeExpoStew(VSItems.EXPONENTIAL_BEETROOT_SOUP.get());
        VSClientHelpers.makeExpoStew(VSItems.EXPONENTIAL_RABBIT_STEW.get());
        VSClientHelpers.makeExpoStew(VSItems.EXPONENTIAL_SUSPICIOUS_STEW.get());
        VSClientHelpers.makeExpoStew(VSItems.EXPONENTIAL_FUNGI_STEW.get());
        VSClientHelpers.makeExpoStew(VSItems.EXPONENTIAL_END_FUNGI_STEW.get());
        VSClientHelpers.makeExpoStew(VSItems.EXPONENTIAL_ALJAN_FUNGI_STEW.get());
        VSClientHelpers.makeExpoStew(VSItems.EXPONENTIAL_WATER_BOWL.get());
        VSClientHelpers.makeExpoStew(VSItems.EXPONENTIAL_MILK_BOWL.get());
        VSClientHelpers.makeExpoStew(VSItems.EXPONENTIAL_LAVA_BOWL.get());
        VSClientHelpers.makeExpoStew(VSItems.EXPONENTIAL_SOUL_LAVA_BOWL.get());
        VSClientHelpers.makeExpoStew(VSItems.EXPONENTIAL_POWDER_SNOW_BOWL.get());
        setRenderTypesForBlocks();

        Atlases.addWoodType(VSWoodTypes.PAINTING);
        Atlases.addWoodType(VSWoodTypes.ENDERWOOD);

        RenderingRegistry.registerEntityRenderingHandler(VSEntities.FISH.get(), FishRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(VSEntities.VS_BOAT.get(), VSBoatRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(VSEntities.DRAGON_BREATH_BOTTLE.get(), DragonBreathBottleRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(VSEntities.SMALL_SOUL_FIREBALL.get(), SmallSoulFireballRenderer::new);

        ClientRegistry.bindTileEntityRenderer(VSBlockEntities.VS_BELL.get(), VSBellBlockEntityRenderer::new);
        ClientRegistry.bindTileEntityRenderer(VSBlockEntities.VS_BEACON.get(), BeaconTileEntityRenderer::new);
        ClientRegistry.bindTileEntityRenderer(VSBlockEntities.VS_BED.get(), VSBedBlockEntityRenderer::new);
        ClientRegistry.bindTileEntityRenderer(VSBlockEntities.VS_SIGN.get(), SignTileEntityRenderer::new);
    }

    @SubscribeEvent
    public void serverStarting(FMLServerStartingEvent event) {}

    public static void setRenderTypesForBlocks() {
        RenderTypeLookup.setRenderLayer(VSBlocks.POTTED_GLOW_BLACK_TULIP.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(VSBlocks.POTTED_SUNNY_FLOWER.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(VSBlocks.POTTED_SUGAR_CANE.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(VSBlocks.POTTED_PAINTING_SAPLING.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(VSBlocks.GOLDEN_CARROTS.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(VSBlocks.GLOW_BLACK_TULIP.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(VSBlocks.SUNNY_FLOWER.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(VSBlocks.GLOW_BERRY_BUSH.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(VSBlocks.PAINTING_SAPLING.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(VSBlocks.WARPED_WART.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(VSBlocks.GOLDEN_BEACON.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(VSBlocks.GOLDEN_CAULDRON.get(), RenderType.translucent());
        RenderTypeLookup.setRenderLayer(VSBlocks.QUARTZ_BEACON.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(VSBlocks.QUARTZ_CAULDRON.get(), RenderType.translucent());
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
        RenderTypeLookup.setRenderLayer(VSBlocks.SOUL_BREWING_STAND.get(), RenderType.cutout());
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
        RenderTypeLookup.setRenderLayer(VSBlocks.POTTED_GRASS.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(VSBlocks.POTTED_GOLDEN_CARROTS.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(VSBlocks.POTTED_NETHER_WART.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(VSBlocks.POTTED_WARPED_WART.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(VSBlocks.POTTED_ENDER_WART.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(VSBlocks.POTTED_TORCH.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(VSBlocks.POTTED_SOUL_TORCH.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(VSBlocks.POTTED_REDSTONE_TORCH.get(), RenderType.cutout());

        RenderTypeLookup.setRenderLayer(VSBlocks.GLOW_BLACK_STAINED_GLASS.get(), RenderType.translucent());
        RenderTypeLookup.setRenderLayer(VSBlocks.GLOW_BLACK_STAINED_GLASS_PANE.get(), RenderType.translucent());

        RenderTypeLookup.setRenderLayer(VSFluids.MUSHROOM_STEW.get(), RenderType.translucent());
        RenderTypeLookup.setRenderLayer(VSFluids.FLOWING_MUSHROOM_STEW.get(), RenderType.translucent());
    }
}
