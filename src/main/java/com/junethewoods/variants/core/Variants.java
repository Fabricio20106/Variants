package com.junethewoods.variants.core;

import com.junethewoods.variants.common.block.blockcolor.CauldronBlockColor;
import com.junethewoods.variants.common.block.blockcolor.PaintingLeavesBlockColor;
import com.junethewoods.variants.common.item.LeavesItemColor;
import com.junethewoods.variants.common.item.WoolSweatchestColor;
import com.junethewoods.variants.core.biome.VSBiomes;
import com.junethewoods.variants.core.init.*;
import com.junethewoods.variants.core.sound.VSSounds;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(Variants.MOD_ID)
public class Variants {
    public static final Logger LOGGER = LogManager.getLogger(Variants.MOD_ID);
    public static final String MOD_ID = "variants";
    public static boolean debugLogs = false;
    // variants:pornhey

    public Variants() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        modEventBus.addListener(this::commonSetup);
        modEventBus.addListener(this::clientSetup);
        modEventBus.addListener(this::registerBlockColors);
        modEventBus.addListener(this::registerItemColors);

        MinecraftForge.EVENT_BUS.register(this);
        VSEntities.ENTITIES.register(modEventBus);
        VSBlocks.BLOCKS.register(modEventBus);
        VSFluids.FLUIDS.register(modEventBus);
        VSItems.ITEMS.register(modEventBus);
        VSWeaponry.ITEMS.register(modEventBus);
        VSBiomes.BIOMES.register(modEventBus);
        VSSounds.registerSounds();
    }

    public void commonSetup(final FMLCommonSetupEvent event) {
        //VariantFeatures.actuallyGenerateStuff();
        //VanillaCompat.register();
        //RegistryKey<Biome> paintingWoodenForestKey = RegistryKey.create(ForgeRegistries.Keys.BIOMES, BiomeInit.painting_wooded_forest.getId());
        //RegistryKey<Biome> azureFieldsKey = RegistryKey.create(ForgeRegistries.Keys.BIOMES, BiomeInit.azure_fields.getId());
        //BiomeManager.addBiome(BiomeManager.BiomeType.COOL, new BiomeManager.BiomeEntry(paintingWoodenForestKey, 10));
        //BiomeManager.addBiome(BiomeManager.BiomeType.COOL, new BiomeManager.BiomeEntry(azureFieldsKey, 15));
        //GlobalEntityTypeAttributes.put(EntityInit.old_cod.get(), AbstractSchoolingFish.createAttributes().build());
        //GlobalEntityTypeAttributes.put(EntityInit.pornhey.get(), PornheyEntity.createAttributes().build());
    }

    /**
     * Makes a {@link ResourceLocation} with mod id "variants"
     *
     * @param name     The name for the thing in the {@link ResourceLocation}
     * @return A {@link ResourceLocation} with mod id "variants" plus the name
     */
    public static ResourceLocation resourceLoc(String name) {
        return new ResourceLocation(Variants.MOD_ID, name);
    }

    public void clientSetup(final FMLClientSetupEvent event) {
        ItemBlockRenderTypes.setRenderLayer(VSBlocks.PAINTING_SAPLING.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(VSBlocks.GOLDEN_CARROTS.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(VSBlocks.POTTED_SUNNY_FLOWER.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(VSBlocks.POTTED_GLOW_BLACK_TULIP.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(VSBlocks.POTTED_GOLDEN_CARROTS.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(VSBlocks.GLOW_BERRY_BUSH.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(VSBlocks.GLOW_BLACK_TULIP.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(VSBlocks.SUNNY_FLOWER.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(VSBlocks.PAINTING_TRAPDOOR.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(VSBlocks.PAINTING_DOOR.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(VSBlocks.WARPED_WART.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(VSBlocks.PAINTING_LEAVES.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(VSBlocks.DIAMOND_BELL.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(VSBlocks.POTTED_GRASS.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(VSBlocks.POTTED_TORCH.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(VSBlocks.POTTED_SOUL_TORCH.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(VSBlocks.POTTED_SUGAR_CANE.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(VSBlocks.AZURE_BLUET_LEAVES.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(VSBlocks.QUARTZ_BEACON.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(VSBlocks.GOLD_BEACON.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(VSBlocks.GOLD_CAULDRON.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(VSBlocks.QUARTZ_CAULDRON.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(VSBlocks.ENDER_SPROUTS.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(VSBlocks.ENDER_FUNGUS.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(VSBlocks.ENDER_ROOTS.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(VSBlocks.ENDER_TRAPDOOR.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(VSBlocks.ENDER_DOOR.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(VSBlocks.POTTED_ENDER_FUNGUS.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(VSBlocks.POTTED_ENDER_ROOTS.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(VSBlocks.POTTED_WARPED_WART.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(VSBlocks.QUARTZ_CHAIN.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(VSBlocks.QUARTZ_GLASS.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(VSBlocks.QUARTZ_GLASS_PANE.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(VSBlocks.QUARTZ_LADDER.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(VSBlocks.QUARTZ_BARS.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(VSBlocks.DIAMOND_CHAIN.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(VSBlocks.EMERALD_CHAIN.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(VSBlocks.SOUL_BREWING_STAND.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(VSBlocks.OAK_TRAPDOOR_DOOR.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(VSBlocks.CRIMSON_TRAPDOOR_DOOR.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(VSBlocks.DARK_OAK_TRAPDOOR_DOOR.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(VSBlocks.ACACIA_TRAPDOOR_DOOR.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(VSBlocks.WARPED_POTATOES.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(VSBlocks.WILD_WARPED_POTATOES.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(VSBlocks.CRIMSON_WHEAT.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(VSBlocks.WILD_CRIMSON_WHEAT.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(VSBlocks.WILD_SOUL_CARROTS.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(VSBlocks.SOUL_CARROTS.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(VSBlocks.WARPING_VINES.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(VSBlocks.WARPING_VINES_PLANT.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(VSFluids.SOUL_LAVA.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(VSFluids.FLOWING_SOUL_LAVA.get(), RenderType.translucent());
        VSBlockColors.initiateModColoring();

        //RenderingRegistry.registerEntityRenderingHandler(EntityInit.old_cod.get(), OldCodRenderer::new);
        //RenderingRegistry.registerEntityRenderingHandler(EntityInit.pornhey.get(), PornheyRenderer::new);

        ItemProperties.register(VSWeaponry.DEBUG_BOW.get(), new ResourceLocation("pull"), (stack, world, livingEntity, idk) -> {
            if (livingEntity == null) {
                return 0.0F;
            } else {
                return livingEntity.getUseItem() != stack ? 0.0F : (float) (stack.getUseDuration() - livingEntity.getUseItemRemainingTicks()) / 20.0F;
            }
        });
        ItemProperties.register(VSWeaponry.DEBUG_BOW.get(), new ResourceLocation("pulling"), (stack, world, livingEntity, idk) ->
                livingEntity != null && livingEntity.isUsingItem() && livingEntity.getUseItem() == stack ? 1.0F : 0.0F);
    }

    @SubscribeEvent
    public void registerBlockColors(ColorHandlerEvent.Block event) {
        event.getBlockColors().register(new PaintingLeavesBlockColor(), VSBlocks.PAINTING_LEAVES.get());
        event.getBlockColors().register(new CauldronBlockColor(), VSBlocks.GOLD_CAULDRON.get(), VSBlocks.QUARTZ_CAULDRON.get());
    }

    @SubscribeEvent
    public void registerItemColors(ColorHandlerEvent.Item event) {
        event.getItemColors().register(new LeavesItemColor(), VSBlocks.PAINTING_LEAVES.get());
        event.getItemColors().register(new WoolSweatchestColor(), VSWeaponry.WOOL_SWEATCHEST.get());
    }

    public static class CreativeTabs {
        public static final CreativeModeTab variant_building_blocks = new CreativeModeTab("variant.building_blocks") {
            @Override public ItemStack makeIcon() {
                return new ItemStack(VSItems.ELDER_PRISMARINE_BRICKS.get());
            }
        };
        public static final CreativeModeTab variant_decorations = new CreativeModeTab("variant.decorations") {
            @Override public ItemStack makeIcon() {
                return new ItemStack(VSItems.sunny_flower.get());
            }
        };
        public static final CreativeModeTab variant_redstone = new CreativeModeTab("variant.redstone") {
            @Override public ItemStack makeIcon() {
                return new ItemStack(VSItems.ELDER_PRISMARINE_BRICKS.get());
            }
        };
        public static final CreativeModeTab variant_transportation = new CreativeModeTab("variant.transportation") {
            @Override public ItemStack makeIcon() {
                return new ItemStack(VSItems.powered_railbed.get());
            }
        };
        public static final CreativeModeTab variant_misc = new CreativeModeTab("variant.misc") {
            @Override public ItemStack makeIcon() {
                return new ItemStack(VSItems.soul_lava_bucket.get());
            }
        };
        public static final CreativeModeTab variant_food = new CreativeModeTab("variant.food") {
            @Override public ItemStack makeIcon() {
                return new ItemStack(VSItems.fungi_stew.get());
            }
        };
        public static final CreativeModeTab variant_tools = new CreativeModeTab("variant.tools") {
            @Override public ItemStack makeIcon() {
                return new ItemStack(VSWeaponry.diorite_axe.get());
            }
        };
        public static final CreativeModeTab variant_combat = new CreativeModeTab("variant.combat") {
            @Override public ItemStack makeIcon() {
                return new ItemStack(VSWeaponry.end_stone_sword.get());
            }
        };
        public static final CreativeModeTab variant_brewing = new CreativeModeTab("variant.brewing") {
            @Override public ItemStack makeIcon() {
                return new ItemStack(VSItems.gold_cauldron.get());
            }
        };
    }
}
