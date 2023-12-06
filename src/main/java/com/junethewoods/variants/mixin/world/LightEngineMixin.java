package com.junethewoods.variants.mixin.world;

import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.lighting.LightEngine;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LightEngine.class)
public class LightEngineMixin {
    /*@Inject(method = "getLightColor(Lnet/minecraft/world/IBlockDisplayReader;Lnet/minecraft/block/BlockState;Lnet/minecraft/util/math/BlockPos;)I", at = @At("HEAD"), cancellable = true)
    private static void getLightColor(IBlockDisplayReader reader, BlockState state, BlockPos pos, CallbackInfoReturnable<Integer> cir) {
        cir.cancel();
        if (state.is(Blocks.SOUL_TORCH)) {
            cir.setReturnValue(0xFFFFFF);
        }
        if (state.emissiveRendering(reader, pos)) {
            cir.setReturnValue(15728880);
        } else {
            int skyLight = reader.getBrightness(LightType.SKY, pos);
            int blockLight = reader.getBrightness(LightType.BLOCK, pos);
            int locationLight = state.getLightValue(reader, pos);
            if (blockLight < locationLight) {
                blockLight = locationLight;
            }

            cir.setReturnValue(skyLight << 20 | blockLight << 4);
        }
    }*/
//
//    @Inject(method = "getLightValue", at = @At("HEAD"), cancellable = true)
//    private static void getLightValue(BlockPos pos, CallbackInfoReturnable<Integer> cir) {
//        BlockState state =
//        if (pos.)
//    }

}
