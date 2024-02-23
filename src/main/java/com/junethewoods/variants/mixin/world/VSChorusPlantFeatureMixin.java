package com.junethewoods.variants.mixin.world;

import com.junethewoods.variants.util.VSTags;
import com.mojang.serialization.Codec;
import net.minecraft.block.ChorusFlowerBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.ChorusPlantFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import org.spongepowered.asm.mixin.Mixin;

import java.util.Random;

@Mixin(ChorusPlantFeature.class)
public class VSChorusPlantFeatureMixin extends Feature<NoFeatureConfig> {
    public VSChorusPlantFeatureMixin(Codec<NoFeatureConfig> codec) {
        super(codec);
    }

    public boolean place(ISeedReader world, ChunkGenerator generator, Random rand, BlockPos pos, NoFeatureConfig config) {
        if (world.isEmptyBlock(pos) && world.getBlockState(pos.below()).is(VSTags.Blocks.CHORUS_FLOWER_PLANTABLE_ON)) {
            ChorusFlowerBlock.generatePlant(world, pos, rand, 8);
            return true;
        } else {
            return false;
        }
    }
}
