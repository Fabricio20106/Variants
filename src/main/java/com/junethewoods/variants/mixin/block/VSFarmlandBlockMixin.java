package com.junethewoods.variants.mixin.block;

import com.junethewoods.variants.util.VSTags;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.FarmlandBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorldReader;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(FarmlandBlock.class)
public class VSFarmlandBlockMixin extends Block {
    public VSFarmlandBlockMixin(Properties properties) {
        super(properties);
    }

    @Inject(method = "canSurvive", at = @At("HEAD"), cancellable = true)
    public void canSurvive(BlockState state, IWorldReader world, BlockPos pos, CallbackInfoReturnable<Boolean> cir) {
        BlockState aboveState = world.getBlockState(pos.above());
        cir.setReturnValue(!aboveState.getMaterial().isSolid() || aboveState.is(VSTags.Blocks.FARMLAND_TRANSPARENT));
    }
}
