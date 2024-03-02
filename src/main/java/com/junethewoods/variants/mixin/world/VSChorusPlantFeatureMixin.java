package com.junethewoods.variants.mixin.world;

import com.junethewoods.variants.util.VSTags;
import net.minecraft.block.ChorusFlowerBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.ChorusPlantFeature;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Random;

@Mixin(ChorusPlantFeature.class)
public class VSChorusPlantFeatureMixin {
    @Inject(method = "place(Lnet/minecraft/world/ISeedReader;Lnet/minecraft/world/gen/ChunkGenerator;Ljava/util/Random;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/world/gen/feature/NoFeatureConfig;)Z", at = @At("HEAD"), cancellable = true)
    public void place(ISeedReader world, ChunkGenerator generator, Random rand, BlockPos pos, NoFeatureConfig config, CallbackInfoReturnable<Boolean> cir) {
        if (world.isEmptyBlock(pos) && world.getBlockState(pos.below()).is(VSTags.Blocks.CHORUS_FLOWER_PLANTABLE_ON)) {
            ChorusFlowerBlock.generatePlant(world, pos, rand, 8);
            cir.setReturnValue(true);
        } else {
            cir.setReturnValue(false);
        }
    }
}
