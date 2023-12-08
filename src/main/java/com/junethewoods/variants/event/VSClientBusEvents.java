package com.junethewoods.variants.event;

import com.junethewoods.variants.Variants;
import com.junethewoods.variants.blockentity.VSBlockEntities;
import com.junethewoods.variants.entity.client.VSModelLayers;
import net.minecraft.client.model.BoatModel;
import net.minecraft.client.model.ChestBoatModel;
import net.minecraft.client.model.CodModel;
import net.minecraft.client.renderer.blockentity.BeaconRenderer;
import net.minecraft.client.renderer.blockentity.BedRenderer;
import net.minecraft.client.renderer.blockentity.BellRenderer;
import net.minecraft.client.renderer.blockentity.SignRenderer;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;

@Mod.EventBusSubscriber(modid = Variants.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class VSClientBusEvents {
//    @SubscribeEvent
//    public static void stitchTextures(final TextureAtlasStitchedEvent event) {
//        if (event.getAtlas().location().getPath().contains("blocks")) {
//            event.addSprite(Variants.resourceLoc("entity/bell/diamond_bell_body"));
//        }
//        if (event.getAtlas().location().getPath().contains("beds")) {
//            event.addSprite(Variants.resourceLoc("entity/bed/glow_black"));
//        }
//    }

    @SubscribeEvent
    public static void registerLayer(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(VSModelLayers.FISH_LAYER, CodModel::createBodyLayer);

        event.registerLayerDefinition(VSModelLayers.CRIMSON_BOAT_LAYER, BoatModel::createBodyModel);
        event.registerLayerDefinition(VSModelLayers.WARPED_BOAT_LAYER, BoatModel::createBodyModel);
        event.registerLayerDefinition(VSModelLayers.PAINTING_BOAT_LAYER, BoatModel::createBodyModel);
        event.registerLayerDefinition(VSModelLayers.CRIMSON_CHEST_BOAT_LAYER, ChestBoatModel::createBodyModel);
        event.registerLayerDefinition(VSModelLayers.WARPED_CHEST_BOAT_LAYER, ChestBoatModel::createBodyModel);
        event.registerLayerDefinition(VSModelLayers.PAINTING_CHEST_BOAT_LAYER, ChestBoatModel::createBodyModel);
    }

    @SubscribeEvent
    public static void registerBER(EntityRenderersEvent.RegisterRenderers event) {
        event.registerBlockEntityRenderer(VSBlockEntities.VS_SIGN.get(), SignRenderer::new);
        event.registerBlockEntityRenderer(VSBlockEntities.VS_BELL.get(), BellRenderer::new);
        event.registerBlockEntityRenderer(VSBlockEntities.VS_BED.get(), BedRenderer::new);
        event.registerBlockEntityRenderer(VSBlockEntities.VS_BEACON.get(), BeaconRenderer::new);
    }
}
