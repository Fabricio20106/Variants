package com.junethewoods.variants.util;

import com.junethewoods.variants.Variants;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.WoodType;

public class VSWoodTypes {
    public static final WoodType PAINTING = WoodType.register(new WoodType(Variants.resourceLoc("painting").toString(), BlockSetType.OAK));
    public static final WoodType ENDERWOOD = WoodType.register(new WoodType(Variants.resourceLoc("enderwood").toString(), BlockSetType.CRIMSON, SoundType.NETHER_WOOD, SoundType.NETHER_WOOD_HANGING_SIGN, SoundEvents.NETHER_WOOD_FENCE_GATE_CLOSE, SoundEvents.NETHER_WOOD_FENCE_GATE_OPEN));
}
