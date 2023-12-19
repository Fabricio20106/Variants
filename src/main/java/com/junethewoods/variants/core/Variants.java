package com.junethewoods.variants.core;

import com.junethewoods.variants.core.biome.VSBiomes;
import com.junethewoods.variants.core.init.*;
import com.junethewoods.variants.core.sound.VSSounds;
import com.junethewoods.variants.core.world.ore.VSOrePlacements;
import com.junethewoods.variants.core.world.vegetation.VSVegetationPlacements;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.IEventBus;
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
        VSBiomes.BIOMES.register(eventBus);
        VSSounds.registerSounds();
    }

    public void commonSetup(final FMLCommonSetupEvent event) {
        loadPlacementClasses();
        VSVanillaCompatibility.register();
    }

    public static ResourceLocation resourceLoc(String name) {
        return new ResourceLocation(Variants.MOD_ID, name);
    }

    public static void loadPlacementClasses() {
        MinecraftForge.EVENT_BUS.addListener(EventPriority.HIGH, VSVegetationPlacements::onBiomeLoad);
        MinecraftForge.EVENT_BUS.addListener(EventPriority.HIGH, VSOrePlacements::onBiomeLoad);
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
        ItemBlockRenderTypes.setRenderLayer(VSBlocks.POTTED_ENDER_ROOTS.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(VSBlocks.POTTED_ENDER_FUNGUS.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(VSFluids.SOUL_LAVA.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(VSFluids.FLOWING_SOUL_LAVA.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(VSFluids.MUSHROOM_STEW.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(VSFluids.FLOWING_MUSHROOM_STEW.get(), RenderType.translucent());

        ItemProperties.register(VSWeaponry.DEBUG_BOW.get(), new ResourceLocation("pull"), (stack, world, livEntity, what) -> {
            if (livEntity == null) {
                return 0.0F;
            } else {
                return livEntity.getUseItem() != stack ? 0 : (float) (stack.getUseDuration() - livEntity.getUseItemRemainingTicks()) / 20;
            }
        });
        ItemProperties.register(VSWeaponry.DEBUG_BOW.get(), new ResourceLocation("pulling"), (stack, world, livEntity, what) ->
                livEntity != null && livEntity.isUsingItem() && livEntity.getUseItem() == stack ? 1 : 0);
    }
}
