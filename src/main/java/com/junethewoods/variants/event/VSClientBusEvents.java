package com.junethewoods.variants.event;

import com.junethewoods.variants.Variants;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Variants.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class VSClientBusEvents {
    @SubscribeEvent
    public static void stitchTextures(final TextureStitchEvent.Pre event) {
        if (event.getMap().location().getPath().contains("blocks")) {
            event.addSprite(Variants.resourceLoc("entity/bell/diamond_bell_body"));
        }
        if (event.getMap().location().getPath().contains("beds")) {
            event.addSprite(Variants.resourceLoc("entity/bed/glow_black"));
        }
    }
}
