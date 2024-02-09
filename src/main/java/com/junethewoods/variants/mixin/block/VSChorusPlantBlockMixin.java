package com.junethewoods.variants.mixin.block;

import com.junethewoods.variants.util.VSTags;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ChorusPlantBlock;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorldReader;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ChorusPlantBlock.class)
public class VSChorusPlantBlockMixin extends Block {
    public VSChorusPlantBlockMixin(Properties properties) {
        super(properties);
    }

    @Inject(method = "canSurvive", at = @At("HEAD"), cancellable = true)
    public void canSurvive(BlockState state, IWorldReader world, BlockPos pos, CallbackInfoReturnable<Boolean> cir) {
        BlockState belowState = world.getBlockState(pos.below());
        boolean isAirAboveAndBelow = !world.getBlockState(pos.above()).isAir() && !belowState.isAir();

        for(Direction direction : Direction.Plane.HORIZONTAL) {
            BlockPos relativePos = pos.relative(direction);
            Block relativeBlock = world.getBlockState(relativePos).getBlock();
            if (relativeBlock == this) {
                if (isAirAboveAndBelow) {
                    cir.setReturnValue(false);
                }

                Block relativeBlockBelow = world.getBlockState(relativePos.below()).getBlock();
                if (relativeBlockBelow == this || relativeBlockBelow.is(VSTags.Blocks.CHORUS_FLOWER_PLANTABLE_ON)) {
                    cir.setReturnValue(true);
                }
            }
        }

        Block belowBlock = belowState.getBlock();
        cir.setReturnValue(belowBlock == this || belowBlock.is(VSTags.Blocks.CHORUS_FLOWER_PLANTABLE_ON));
    }
}
