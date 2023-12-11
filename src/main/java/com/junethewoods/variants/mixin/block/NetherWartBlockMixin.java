package com.junethewoods.variants.mixin.block;

import com.junethewoods.variants.util.VSTags;
import net.minecraft.block.BlockState;
import net.minecraft.block.NetherWartBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(NetherWartBlock.class)
public class NetherWartBlockMixin {
    @Inject(method = "mayPlaceOn", at = @At("HEAD"), cancellable = true)
    private void mayPlaceOn(BlockState state, IBlockReader world, BlockPos pos, CallbackInfoReturnable<Boolean> cir) {
        cir.setReturnValue(state.is(VSTags.Blocks.NETHER_WART_PLACEABLE_ON));
    }
}
